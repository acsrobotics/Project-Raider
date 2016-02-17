package org.usfirst.frc.team4716.robot;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import org.junit.runners.model.InitializationError;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot. -- Alessio
 * ###EVERYTHING PAST THIS POINT IS CURTSY OF MATTHEW###
 * TODO:
 * 	- Replace the joysticks with a LinkedHashMap of the joystick and it's respective controller
 * 	  enum.
 * 	
 * 	- Add methods for assigning functions to buttons
 * 
 *  - Add methods for getting and overriding the type of controller.
 *  
 *  - Add methods for automatic checking if controller states:
 *  	- Make sure that, at most, one controller is mapped to JoystickAuthority.DRIVE_STICK 
 *  	  or JoystickAuthority.OPERATOR_STICK or both.
 *  
 *  - Add compatibility for Xbox and possibly PS3/4 controllers.
 *  
 *  - Implement the use of the ControllerTypes enum.
 *  
 *  - ADD A SECURITY MANAGER: (Might be a custom class / set of classes)
 *  	- Monitor the thread and make sure that no class illegally invokes feild or object 
 *  	  reflection on this class or it's subclasses.
 * 
 * Because the number of joysticks is set to a maximum limit of six in the DriverStation class, 
 * and the entire api for interacting with and detecting the joysticks and their onboard buttons
 * is written in C++ with a JNI, there is no possible way to detect the true number of joysticks 
 * available to the host system at any given point, at least in Java. We can, however, automatically
 * initialize all of the buttons on all of the possible joysticks instead allowing us for greater
 * control over each individual joystick's functions and authorities (Ie. JoystickType, 
 * ButtonFunction) while consuming little to no extra memory, considering that the DriverStation
 * class, inefficiently mind you, already automatically initializes six joystick threads as well 
 * as all of their hardcoded maximum twelve buttons at the robot's initialization. Eventually, the
 * goal of this class will be to scope each port and search for a joystick and upon finding any
 * joysticks, search their configurations for buttons and automatically map those as well, thereby
 * eliminating hardcoded constants for the maximum number of joysticks as well as buttons. Oh and 
 * by the way, this entire class only globally uses 3 variables for extrapolation... 2 for security
 * and 1 for functionality.
 * 
 * @author Matthewacon
 * @see {@linkplain edu.wpi.first.wpilibj.DriverStation}, {@linkplain edu.wpi.first.wpilibj.Joystick}, {@linkplain edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary}
 */
@SuppressWarnings({ "serial", "unchecked", "unused", "rawtypes"})
public abstract class OI {
	
	/**Currently unused.*/
	private Boolean isConstructed = false;
	
	/**The security variable used to assure that operations on the global instance variable, from which
	 * this entire class is built around, will be able to execute safely. 
	 */
	private Boolean isInitialized = false;
	
	/**The LinkedHashMap containing the HasMap of a Joystick and it's type, and an ArrayList of the buttons on the joystick.*/
	private LinkedHashMap<HashMap<Joystick, JoystickAuthority>, ArrayList<JoystickButton>> controllerMap;
	
	/**The enum containing the different types of acceptable controllers.*/
	static enum ControllerTypes {
		Joystick, Xbox;
	}
	
	/**The enum containing the controller states.*/
	static enum JoystickAuthority {
		DRIVE_STICK(0), OPERATOR_STICK(1), TEST_STICK(2);
	    
		/**The enum's enumeration count. This is set at the static initialization when
		* the enum is defined.
		*/
		int enumeration;
		
		/**Default constructor for each enum.
		 */
		JoystickAuthority(int i) {
		 	enumeration = i;
		}
		
		/**Returns the enumeration value for the given enum of this type.
		 * 
		 * @return enumeration - The enumeration count for inclass / subclass arithmetic.
		 */
		public Integer getEnumeration() {
		  return enumeration;
		}
		
		/**There is no setter method for the enumeration variable because the 
		 * default constructor, which sets the value of enumeration, is invoked
		 * both when setting and declaring the enum of this type. This method
		 * only exists as a place holder for a future JSON construction model
		 * from which the controller constants will be parsed and set.
		 */
		private void setEnumeration(Integer i) {
			enumeration = i;
		}
		
		/**A dynamic greedy integer comparator (Hence the "contectual"). Returns the 
		 * value of the enumeration that is equal to the value of "i" if i is lesser 
		 * than or equal to the value of the total number of enumerations minus one 
		 * in this enum. If "i" is greater than the total number of enumerations, 
		 * then it returns the closest value to "i" being the highest value in the 
		 * enumeration array.
		 * 
		 * @param i - The integer to be compared.
		 * @return JoystickType - The value equal to or closest to the value of "i". 
		 * @see {@linkplain JoystickAuthority} 
		 */
		static protected JoystickAuthority contextualStateCompatator(int i) {
			for (JoystickAuthority jt : values()) {
				if (jt.equals(values()[values().length-1])) {
					break;
				}
				if (i > values().length-1 && i == jt.getEnumeration()) {
					return jt;
				} else if (i > values().length-1) {
					break;
				}
			}
			return values()[values().length-1];
		}
	}
	
	public enum JoystickAxis {
		X,Y,Z;
	}
	
	/**The custom Exception thrown when a corrupt map has been detected.
	 * 
	 * @author Matthew
	 *
	 */
	public final class CorruptMapException extends Exception {
		public CorruptMapException() {
			super();
		}
		
		public CorruptMapException(String s) {
			super(s);
		}
	}
	
	/**The very first model of the automated joystick initialization, I am fairly cetrian that, because of
	 * Java's restrictions on anonymous classes and their scoped variables, this will not work, at all...
	 * TODO Test this...
	 */
	@Deprecated
	protected void initialize_ver1() {
		controllerMap = new LinkedHashMap<HashMap<Joystick, JoystickAuthority>, ArrayList<JoystickButton>>() {
		/**The iterative Integer for the "for" loops that set joystick port, its enum state,
		 * and the buttons it has.
		 */
		final int i;
		
		/**This function removes the Final bit modifier from an input object, sets the value
		 * to the new given value, and replaces the Final bit modifier on the original 
		 * Field.
		 * 
		 * @param inputFinal - The final field to be modified.
		 * @param newValue - The new value to be set.
		 * 
		 * @see {@linkplain java.util.reflect.Field}
		 */
		void modFinal(Field inputFinal, Object newValue) {
			//Debug Loop
			for (Field f : this.getClass().getDeclaredFields()) {
				System.out.println("Field name:\t" + f.getName());
				System.out.println("Field modifiers:\t" + f.getModifiers());
				System.out.println("Field type:\t" + f.getType());
			}
			inputFinal.setAccessible(true);
			try {
				Field modifiersField = Field.class.getDeclaredField("i");
				modifiersField.setAccessible(true);
				modifiersField.setInt(inputFinal, inputFinal.getModifiers() & ~Modifier.FINAL);
				inputFinal.set(null, newValue);
				modifiersField.setInt(inputFinal, inputFinal.getModifiers() & Modifier.FINAL);
				modifiersField.setAccessible(false);
				inputFinal.setAccessible(false);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}	
		}
		/**Anonymous initializer*/
		{	
			/**Populating the outer LinkedHashMap with a new anonymous instantiation of a HashMap containing
			* a Joystick as the key and a JoystickType as the value, and a new ArrayList of JoystickButtons
			* pertaining to the Joystick being added at the iteration. 
			*/
			try {
				for (i = 0; i <= DriverStation.kJoystickPorts-1; modFinal(this.getClass().getField("i"), i+1)) {
					put(new HashMap<Joystick, JoystickAuthority>(1) {
						/**Anonymous initializer*/
						{
							put(new Joystick(i), JoystickAuthority.contextualStateCompatator(i));
						}
					}, new ArrayList<JoystickButton>() {
						/**Anonymous initializer*/
						{
							HashMap<Joystick,JoystickAuthority> joystickMap = ((HashMap<Joystick,JoystickAuthority>)(keySet().toArray()[i]));
							Joystick joystickAti = joystickMap.keySet().toArray(new Joystick[0])[joystickMap.keySet().size()-1];
							for (int k = 0; k < 12; k++) {
								add(new JoystickButton(joystickAti, k));
							}
						}
					});
				}
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		};
	}
	
	/**The second Joystick initialization model. When called, this will create the maximum number of
	 * Joysticks available to the host controller, assign them a JoystickType state, and map the
	 * maximum number of buttons for each Joystick. All of these values will be configurable via
	 * public methods in the superclass of this method. This method can only be run once, at the 
	 * construction of its superclass, OI.
	 * 
	 * @see {@linkplain OI}, {@linkplain Joystick}, {@linkplain JoystickAuthority}, {@linkplain JoystickButton}, {@linkplain java.util.Map}, {@linkplain ArrayList}
	 */
	protected void initialize_ver2() throws InitializationError {
		/**I can't remember where I saw the constant for the maximum number of Joystick buttons, but 
		 * I know that it was 12... So until I find it, this will be used as the constant.
		 */
		final Integer maxJoystickButtons = 12;
		
		/**The HashMap containing the maximum number of Joystick instances allowed by the DriverStation
		 * as well as each Joystick's controller state. Defined as final to allow for use in anonymous
		 * extensions of other types.
		 */
		final HashMap<Joystick, JoystickAuthority> joystickMap = new HashMap<Joystick, JoystickAuthority>() {
			{
				for (int i = 0; i < DriverStation.kJoystickPorts; i++) {
					put(new Joystick(i), JoystickAuthority.contextualStateCompatator(i));
				}
			}
		};
		
		final ArrayList<JoystickButton> joystickButtonMap = new ArrayList<JoystickButton>() {
			{
				for (Joystick js : joystickMap.keySet().toArray(new Joystick[0])) {
					for (int j = 0; j < maxJoystickButtons; j++) {
						add(new JoystickButton(js, j));
					}
				}
			}
		};
		
		controllerMap = new LinkedHashMap<HashMap<Joystick, JoystickAuthority>, ArrayList<JoystickButton>>() {
			/**The number of times that 12 positions, in the joystickButtonMap ArrayList, have
			 * been iterated over. 
			 */
			Integer joystickButtonSet = 0;
			{
				if ((joystickButtonMap.size())%12 != 0) {
					throw new InitializationError("The LinkedHashMap containing the Joystick, it's controller state, and it's buttons"
							+ " has become corrupt. Initialization cannot continue.");
				}
				for (int k = 0; k < joystickMap.keySet().size()-1; k++) {
					for (final Joystick js : joystickMap.keySet()) {
						put(new HashMap<Joystick, JoystickAuthority>() {
							{
								put(js, joystickMap.get(js));
							}
						}, new ArrayList<JoystickButton>() {
							{
								for (int l = 0; l < 12; l++) {
									add(joystickButtonMap.get(l+(12*new ArrayList(joystickMap.keySet()).indexOf(js))));
								}
							}
						});
					}
				}
			}
		};
	}
	
	/**Default constructor. Because this OI class is an abstract class, this constructor will be invoked
	 * when the subclass extending or implementing it is constructed.
	 * 
	 * @throws InstantiationException - Throws an InstantiationException if the Joystick initialization fails, with the cause specified by the initializer method.
	 * @see {@linkplain OI#initialize_ver2()}
	 */
	public OI() throws Throwable { //InstantiationException
		try {
			initialize_ver2();
		} catch(InitializationError ie) {
			throw new InstantiationException().initCause(ie);
		}
		isInitialized = true;
	}
	
	/**This method checks the stability and reliability of the controllerMap. It is invoked whenever a method involving
	 * variable reads, modifications or deletions from this class is invoked.
	 * 
	 * @throws InitializationError - Thrown when the stabilityChecker has detected that either the class is unitialized, or not properly constructed.
	 * @throws CorruptMapException - Thrown when the stabilityChecker has concluded that the map is corrupt due to misplaces / misconfigured values.
	 */
	protected void stabilityChecker() throws InitializationError, CorruptMapException {
		/**The StackTraceElement[] containing the stack trace for the current thread.
		 * Used for error detection during initialization or OI method invocation.
		 */
		final StackTraceElement[] stea = Thread.currentThread().getStackTrace();
		
		/**Initialization check.*/
		if (!isInitialized()) {
			LinkedList<Throwable> unInitialized = new LinkedList<Throwable>() {
				{
					/**The NPE thrown if the pointer, containing the instance of OI that 
					 * a method was improperly invoked on, has changed to a null pointer 
					 * or malformed instance of OI. 
					 */
					add(new NullPointerException("The LinkedHashMap 'controllerMap' has not yet been initialized.: " + controllerMap.toString()));
					add(new Throwable("The method '" + (stea[stea.length-3]).getMethodName() + "' in '" + (stea[stea.length-3]).getClassName() + "' tried to invoke the "
							+ OI.class + " " + (stea[stea.length-2]).getMethodName() + " on an uninitialized instance of " + OI.class + "."));
				}
			};
			throw new InitializationError(unInitialized);
		}
		
		/**Checking the keySet hashmap in the controllerMap*/
		//TODO Replace labeled for breaks and conditional throw with descriptive exceptions thrown if the broken condition is met in the inner for loops.
		Boolean mapCorrupt = null;
		controllerMapkeySet: 
			for (HashMap<Joystick, JoystickAuthority> hjjt : controllerMap.keySet()) {
				/**Checking the keySet of the hashmap*/
				joystickkeySet: 
					for (Joystick j : hjjt.keySet()) {
						if (!(j instanceof Joystick) || (j.equals(null))) {
							mapCorrupt = true;
							break controllerMapkeySet;
						} else if (j.equals(hjjt.keySet().toArray(new Joystick[0])[hjjt.keySet().size()-1])) {
							mapCorrupt = false;
							break joystickkeySet;
						}
					}
				/**Checking the value set of the hashmap*/
				joystickTypekeySet: 
					for (JoystickAuthority jt : hjjt.values()) {
						if (!(jt instanceof JoystickAuthority) || jt.equals(null)) {
							mapCorrupt = true;
							break controllerMapkeySet;
						} else if (jt.equals(hjjt.values().toArray(new JoystickAuthority[0])[hjjt.values().size()-1])) {
							mapCorrupt = false;
							break joystickTypekeySet;
						}
					}
			}
		
		/**Checking the value set ArrayList in teh controllerMap*/
		controllerMapValueSet:
			for (ArrayList<JoystickButton> aljb : controllerMap.values()) {
				joystickButtonArrayList:
					for (JoystickButton jb : aljb) {
						if (!(jb instanceof JoystickButton) || jb.equals(null)) {
							mapCorrupt = true;
							break controllerMapValueSet;
						} else if (jb.equals(aljb.get(aljb.size()-1))) {
							mapCorrupt = false;
							break controllerMapValueSet;
						}
					}
			}
		
		/**Condition to throw an exception if the map is corrupt.*/
		if (mapCorrupt) {
			throw new CorruptMapException("The controllerMap in " + OI.class + " is corrupt, and therefore the safe operation of the " + OI.class + " cannot continue.");
		}
	}
	
	/**The getter method for the {@link OI#isInitialized} Boolean.
	 * 
	 * @return - The boolean value that the {@link OI#isInitialized} variable currently holds.
	 * @see {@linkplain Boolean}, {@linkplain OI#isInitialized}
	 */
	public Boolean isInitialized() {
		return isInitialized;
	}
	
	/**The static getter method for the {@link OI#isInitialized} Boolean.
	 * 
	 * @return - The boolean value that the {@link OI#isInitialized} variable holds for a given instance of {@linkplain OI}.
	 * @see {@linkplain Boolean}, {@linkplain OI#isInitialized()}, {@linkplain OI#isInitialized}
	 */
	public static Boolean isInitialized(OI oi) {
		return oi.isInitialized();
	}
	
	/**Returns the Double representing the value of a 
	 * 
	 * @param j
	 * @param a
	 * @return
	 */
	public Double getJoyPos(Joystick j, JoystickAxis a) {
		for (Joystick jstks : getJoysticks()) {
			if (getJoysticks().indexOf(jstks) == getJoysticks().size()-1 && !j.equals(jstks)) {
				/**Non fatal IllegalArgumentException printed to console.
				 */
				new IllegalArgumentException("The given Joystick '" + j.getName() + "' is not initialized on the current controller system.").printStackTrace();
				return null;
			} else if (j.equals(jstks)) {
				break;
			}
		}
		return (a.equals(JoystickAxis.X)) ? (j.getX()) : ((a.equals(JoystickAxis.Y)) ? (j.getY()) : (j.getZ()));
	}
	
	/**
	 * 
	 * @param jst
	 * @param a
	 * @return
	 */
	public List<Double> getJoyPos(final JoystickAuthority jst, final JoystickAxis a) {
		LinkedList<Double> axisList = new LinkedList<Double>() {
			{
				getJoysticksAndAuthorities().forEach((Joystick k, JoystickAuthority v) -> {
					if (jst.equals(v)) {
						add(getJoyPos(k,a));
					}
				}); 
			}
		};
		return axisList;
	}
	
	/**Returns the X coordinate for a given Joystick.
	 * 
	 * @param j - A Joystick initialized and mapped in the instance of OI that this method was invoked on.
	 * @return A Double representing the X coordinate of the given Joystick.
	 */
	public Double getJoyX(Joystick j) {
		return getJoyPos(j, JoystickAxis.X);
	}
	
	/**Returns the X coordinate for the Joystick paired with the given JoystickAuthority. If more than one
	 * Joystick matches that authority, then a list of Joystick X coordinates will be returned in the order
	 * that the Joystick pairs set in the HashMap<Joystick, JoystickAuthority> Map.
	 * 
	 * @param jst - The JoystickAuthority corresponding to the Joystick X coordinate to be returned.
	 * @return A List of Doubles containing the X coordinates of the Joysticks matching the given JoystickAuthority.
	 * 
	 * @see {@linkplain OI#getJoysticksAndAuthorities()}, {@linkplain Joystick}, {@linkplain JoystickAuthority}
	 */
	public List<Double> getJoyX(JoystickAuthority jst) {
		return getJoyPos(jst, JoystickAxis.X);
	}
	
	/**Returns the Y coordinate for a given Joystick.
	 * 
	 * @param j - A Joystick initialized and mapped in the instance of OI that this method was invoked on.
	 * @return A Double representing the Y coordinate of the given Joystick.
	 */
	public Double getJoyY(Joystick j) {
		return getJoyPos(j, JoystickAxis.Y);
	}
	
	/**Returns the Y coordinate for the Joystick paired with the given JoystickAuthority. If more than one
	 * Joystick matches that authority, then a list of Joystick Y coordinates will be returned in the order
	 * that the Joystick pairs set in the HashMap<Joystick, JoystickAuthority> Map.
	 * 
	 * @param jst - The JoystickAuthority corresponding to the Joystick Y coordinate to be returned.
	 * @return A List of Doubles containing the Y coordinates of the Joysticks matching the given JoystickAuthority.
	 * 
	 * @see {@linkplain OI#getJoysticksAndAuthorities()}, {@linkplain Joystick}, {@linkplain JoystickAuthority}
	 */
	public List<Double> getJoyY(JoystickAuthority jst) {
		return getJoyPos(jst, JoystickAxis.Y);
	}
	
	/**Returns the Z coordinate for a given Joystick.
	 * 
	 * @param j - A Joystick initialized and mapped in the instance of OI that this method was invoked on.
	 * @return A Double representing the Z coordinate of the given Joystick.
	 */
	public Double getJoyZ(Joystick j) {
		return getJoyPos(j, JoystickAxis.Z);
	}
	
	/**Returns the Z coordinate for the Joystick paired with the given JoystickAuthority. If more than one
	 * Joystick matches that authority, then a list of Joystick Z coordinates will be returned in the order
	 * that the Joystick pairs set in the HashMap<Joystick, JoystickAuthority> Map.
	 * 
	 * @param jst - The JoystickAuthority corresponding to the Joystick Z coordinate to be returned.
	 * @return A List of Doubles containing the Y coordinates of the Joysticks matching the given JoystickAuthority.
	 * 
	 * @see {@linkplain OI#getJoysticksAndAuthorities()}, {@linkplain Joystick}, {@linkplain JoystickAuthority}
	 */
	public List<Double> getJoyZ(JoystickAuthority jst) {
		return getJoyPos(jst, JoystickAxis.Z);
	}
	
	/**Returns a Double[] (double array) of the coordinate positions of each linear grid for a given joystick.
	 * 
	 * @param j - A Joystick initialized and mapped in the instance of OI that this method was invoked on.
	 * @return A Double[] containing the coordinate positions of the controller's roller joints, in order of the axis types in the JoystickAxis enum.
	 */
	public Double[] getAllJoyAxis(Joystick j) {
		Double[] joystickAxisValues = new Double[JoystickAxis.values().length-1];
		for (int i = 0; i < joystickAxisValues.length; i++) {
			joystickAxisValues[i] = getJoyPos(j, JoystickAxis.values()[i]);
		}
		return joystickAxisValues;
	}
	
	
	public List<Double[]> getAllJoystickAxis(JoystickAuthority jst) {
		return new LinkedList<Double[]>() {
			{
				getJoysticksAndAuthorities().forEach((Joystick j, JoystickAuthority jsa) -> {
					if (jst.equals(jsa)) {
						add(getAllJoyAxis(j));
					}
				});
			}
		};
	}
	
	/**
	 * 
	 * @param j
	 * @param js
	 */
	public void reassignJoystick(Joystick j, JoystickAuthority js) {
		
	}
	
	public void autoReassignJoystick(Joystick j, JoystickAuthority js) {
		
	}
	
	public void swapJoystickAuthorities(Joystick joya, Joystick joyb) {
		
	}
	
	public void swapJoystickAuthorities(JoystickAuthority jaa, JoystickAuthority jab) {
		
	}
	
	
	
	/**Returns an iterable list of Joysticks for this instance of OI.
	 * 
	 * @return {@linkplain ArrayList<Joystick>}
	 * @see {@linkplain OI#getJoysticks(OI)}
	 */
	public ArrayList<Joystick> getJoysticks() {
		return getJoysticks(this);
	}
	
	/**Returns the an iterable ArrayList of Joysticks given an instance of the
	 * OI class.
	 * 
	 * @param oi - An initialized instance of the OI class to retrieve the information from.
	 * @return 
	 */
	public static ArrayList<Joystick> getJoysticks(final OI oi) {
		return new ArrayList<Joystick>() {
			{
				for (HashMap<Joystick, JoystickAuthority> hjjt : oi.getAllJoystickInfo().keySet()) {
					add(hjjt.keySet().toArray(new Joystick[0])[0]);
				}
			}
		};
	}
	
	/**Returns a LinkedHashMap of the Joystick and it's JoystickType identical to the key set
	 * in the controllerMap LinkedHashMap.
	 * 
	 * @return A LinkedHashMap where K = Joystick and V = JoystickType
	 * @see {@linkplain LinekdHashMap}, {@linkplain Joystick}, {@linkplain JoystickAuthority}, {@linkplain Set}
	 */
	public LinkedHashMap<Joystick, JoystickAuthority> getJoysticksAndAuthorities() {
		return new LinkedHashMap<Joystick, JoystickAuthority>() {
			{
				for (HashMap<Joystick, JoystickAuthority> hjjt : controllerMap.keySet()) {
					put(hjjt.keySet().toArray(new Joystick[0])[0], hjjt.values().toArray(new JoystickAuthority[0])[0]);
				}
			}
		};
	}
	
	/**Returns an ArrayList containing the mapped buttons for the given Joystick. Returns null if 
	 * the Joystick is non existent or not available to the current instance of OI. 
	 * 
	 * @param j - The Joystick for which the buttons should be returned. This Joystick must be an existing instance within the controllerMap in the OI class or else the method will throw a non-fatal IllegalArgumentException, and return null.
	 * @return An ArrayList containing the mapped buttons for a given Joystick.
	 * @see {@linkplain Joystick}, {@linkplain JoystickButton}, {@linkplain ArrayList}
	 */
	public ArrayList<JoystickButton> getJoystickButtons(Joystick j) {
		return new ArrayList<JoystickButton>() {
			{
				for (Joystick joy : getJoysticks()) {
					if (joy.equals(j)) {
						getAllJoystickInfo().forEach((HashMap<Joystick, JoystickAuthority> hm, ArrayList<JoystickButton> al) -> {
							if (j.equals(hm.keySet().toArray(new Joystick[0])[0])) {
								al.forEach((JoystickButton jb) -> {
									add(jb);
								});
							}
						});
					}
				}
			}
		};
	}
	
	public LinkedHashMap<Joystick, ArrayList<JoystickButton>> getAllJoysticksAndButtons() {
		return new LinkedHashMap<Joystick, ArrayList<JoystickButton>>() {
			{
				getAllJoystickInfo().forEach((HashMap<Joystick, JoystickAuthority> joyMap, ArrayList<JoystickButton> buttonList) -> {
					joyMap.forEach((Joystick joy, JoystickAuthority joyAuth) -> {
						/**So the buttonList and joyAuth variables in this case are redundant as there is already a method implemented for returning the value
						 * of the ArrayList of JoystickButtons mapped to a Joystick... However, for stack efficiency, in the future, this method should be
						 * redone to only use the available variables within this scope to retrieve the joystick buttons. 
						 * 
						 */
						put(joy, getJoystickButtons(joy));
					});
				});
			}
		};
	}
	
	/**
	 * 
	 * @return {@linkplain LinkedHashMap<HashMap<Joystick, JoystickType>}
	 */
	public LinkedHashMap<HashMap<Joystick, JoystickAuthority>, ArrayList<JoystickButton>> getAllJoystickInfo() {
		return controllerMap;
	}

}

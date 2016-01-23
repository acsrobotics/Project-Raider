package org.usfirst.frc.team4716.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick driveStick = new Joystick(0);
	Joystick operatorStick = new Joystick(1);
	Joystick testStick = new Joystick(5);

	public OI() {
		List<JoystickButton> driveButtons = new ArrayList<JoystickButton>();
		List<JoystickButton> operatorButtons = new ArrayList<JoystickButton>();
		List<JoystickButton> testButtons = new ArrayList<JoystickButton>();
		for (int x = 1; x < 9; x++) {
			driveButtons.add(new JoystickButton(driveStick, x));
			operatorButtons.add(new JoystickButton(operatorStick, x));
			testButtons.add(new JoystickButton(testStick, x));
		}

		// driveButtons.get(1).whenPressed(new ExampleCommand());
	}

	public double getJoyX() {
		return driveStick.getX();
	}

	public double getJoyY() {
		return driveStick.getY();
	}

}

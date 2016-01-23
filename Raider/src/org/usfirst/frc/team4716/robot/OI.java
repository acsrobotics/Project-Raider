package org.usfirst.frc.team4716.robot;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team4716.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	
	Joystick driveStick = new Joystick(0);
	Joystick operatorStick = new Joystick(1);
	Joystick testStick = new Joystick(5);
	
	public OI(){
		List<JoystickButton> driveButtons = new ArrayList<JoystickButton>();
		List<JoystickButton> operatorButtons = new ArrayList<JoystickButton>();
		List<JoystickButton> testButtons = new ArrayList<JoystickButton>();
		for(int x = 1; x < 9; x++){
			driveButtons.add(new JoystickButton(driveStick, x));
			operatorButtons.add(new JoystickButton(operatorStick, x));
			testButtons.add(new JoystickButton(testStick, x));
		}
		
		driveButtons.get(1).whenPressed(new ExampleCommand());
	}
	
	
	

}


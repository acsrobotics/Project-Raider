package org.usfirst.frc.team4716.robot;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team4716.robot.commands.Bucket.BucketMove;
import org.usfirst.frc.team4716.robot.commands.Bucket.IntakeSequence;
import org.usfirst.frc.team4716.robot.commands.Bucket.LaunchEjectPiston;
import org.usfirst.frc.team4716.robot.commands.Bucket.SetIntakeMotor;
import org.usfirst.frc.team4716.robot.commands.Bucket.ShootSequence;
import org.usfirst.frc.team4716.robot.commands.Bucket.ToggleElevator;
import org.usfirst.frc.team4716.robot.commands.DriveTrain.SetPosition;
import org.usfirst.frc.team4716.robot.commands.DriveTrain.ToggleAllWheels;
import org.usfirst.frc.team4716.robot.subsystems.Bucket.Direction;
import org.usfirst.frc.team4716.robot.subsystems.DriveTrain.PositionStatusCode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick driveStick = new Joystick(0); // Logitech Dual Action
	Joystick operatorStick = new Joystick(1);
	Joystick testStick = new Joystick(5);
	
	List<JoystickButton> driveButtons = new ArrayList<JoystickButton>();
	List<JoystickButton> operatorButtons = new ArrayList<JoystickButton>();
	List<JoystickButton> testButtons = new ArrayList<JoystickButton>();

	public OI() {
		for (int x = 1; x < 15; x++) {
			driveButtons.add(new JoystickButton(driveStick, x));
			operatorButtons.add(new JoystickButton(operatorStick, x));
			testButtons.add(new JoystickButton(testStick, x));
		}

		//--------------Drivetrain testing button set--------------//
		driveButtons.get(11).whenPressed(new ToggleAllWheels());
		
		driveButtons.get(1).whenPressed(new SetPosition(PositionStatusCode.FRONT_IN_BACK_OUT));
		driveButtons.get(3).whenPressed(new SetPosition(PositionStatusCode.FRONT_OUT_BACK_IN));
		
//		driveButtons.get(7).whenPressed(new TestLift());
		
		//----------------Bucket testing button set------------------------//
		testButtons.get(0).whenPressed(new ShootSequence());
		testButtons.get(1).whenPressed(new LaunchEjectPiston());
		testButtons.get(2).whenPressed(new ToggleElevator());
		testButtons.get(3).whileHeld(new IntakeSequence());
		testButtons.get(3).whenReleased(new ToggleElevator());
		testButtons.get(4).whenPressed(new ShootSequence());
		testButtons.get(5).whileHeld(new BucketMove(Direction.DOWN));
		testButtons.get(6).whileHeld(new BucketMove(Direction.UP));
		testButtons.get(9).whileHeld(new SetIntakeMotor(Direction.IN));
		testButtons.get(10).whileHeld(new SetIntakeMotor(Direction.OUT));
		
		//-----------------------------------------------------------------//	
	}

	public double getJoyX() {
		if (driveStick.getX() <= 0.1 && driveStick.getX() >= -0.1) {
			return 0;
		}
		else {
			return -driveStick.getX();
		}
	}
	
	public double getJoyRY(){
		if (driveStick.getRawAxis(3) <= 0.1 && driveStick.getRawAxis(3) >= -0.1) {
			return 0;
		}
		else {
			return -driveStick.getRawAxis(3);
		}
	}

	public double getJoyY() {
		if (driveStick.getRawAxis(3) <= 0.1 && driveStick.getRawAxis(3) >= -0.1) {
			return 0;
		}
		else {
			return -driveStick.getRawAxis(3);
		}
	}
}
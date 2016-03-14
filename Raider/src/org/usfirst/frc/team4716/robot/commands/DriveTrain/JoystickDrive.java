package org.usfirst.frc.team4716.robot.commands.DriveTrain;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickDrive extends Command {

    public JoystickDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drivetrain.setSlowSpeed(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("Encoder Position: " + Robot.drivetrain.getEncoder().getPosition());
    	Robot.drivetrain.getUltrasonicLeft();
    	Robot.drivetrain.arcadeDrive(Robot.oi.getJoyY(), Robot.oi.getJoyX());
    	
    	//-----------testing
    	System.out.println("Front sensor: " + Robot.drivetrain.ultraTreatAsRaw.getVoltage());
    	System.out.println("Proximte sensor: " + Robot.bucket.ultraProximateSensor.getVoltage());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

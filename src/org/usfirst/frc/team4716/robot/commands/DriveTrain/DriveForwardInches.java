package org.usfirst.frc.team4716.robot.commands.DriveTrain;

import org.usfirst.frc.team4716.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardInches extends Command {

	int speed;
	double length;
	
    public DriveForwardInches(int speed, double length) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
    	this.length = length;
    	requires(Robot.drivetrain);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.driveStraight(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getUltrasonicDistance() > length ? true:false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

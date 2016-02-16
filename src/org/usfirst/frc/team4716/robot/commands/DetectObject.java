package org.usfirst.frc.team4716.robot.commands;

import org.usfirst.frc.team4716.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DetectObject extends Command {
	
	double distance;

    public DetectObject(double distance) {
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setObjectState(false); 	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.drivetrain.getUltrasonicDistance() < distance){
    		Robot.drivetrain.setObjectState(true);
    	}else{
    		Robot.drivetrain.setObjectState(false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getUltrasonicDistance() < distance ? true:false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

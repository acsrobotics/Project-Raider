package org.usfirst.frc.team4716.robot.commands.Climber;

import org.usfirst.frc.team4716.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtendArmTo extends Command {

	double distance = 0.0;
	
    public ExtendArmTo(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.getArmExtension().enable();
    	Robot.climber.getArmExtension().setSetpoint(distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.climber.getArmExtension().isStopped();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.getArmExtension().disable();
    	Robot.climber.getArmExtension().setArmExtendingSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.getArmExtension().disable();
    	Robot.climber.getArmExtension().setArmExtendingSpeed(0.0);
    }
}

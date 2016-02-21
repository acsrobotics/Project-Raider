package org.usfirst.frc.team4716.robot.commands.Climber;

import org.usfirst.frc.team4716.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftArmTo extends Command {

	double distance = 0.0;
	
    public LiftArmTo(double distance) {
    	this.distance = distance;
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.getArmLift().enable();
    	Robot.climber.getArmLift().setSetpoint(this.distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.climber.getArmLift().isStopped();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.getArmLift().disable();
    	Robot.climber.getArmLift().setArmLiftingSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.getArmLift().disable();
    	Robot.climber.getArmLift().setArmLiftingSpeed(0.0);
    }
}

package org.usfirst.frc.team4716.robot.commands.Climber;

import org.usfirst.frc.team4716.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PullUp extends Command {

	double setPoint;
	
    public PullUp(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	this.setPoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.CANInit();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.setElevator(this.setPoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.climber.getPosition()) > this.setPoint ? true:false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.CANHalt();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.CANHalt();
    }
}

package org.usfirst.frc.team4716.robot.commands.Bucket;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.subsystems.Bucket.Direction;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntakeMotor extends Command {
	
	Direction dir;
	
    public SetIntakeMotor(Direction dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.bucket);
    	this.dir = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.bucket.setIntakeMotor(dir);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.bucket.haltIntakeMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.bucket.haltIntakeMotor();
    }
}

package org.usfirst.frc.team4716.robot.commands.Bucket;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.utils.ConfigClient;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleBucketPistion extends Command {

    public ToggleBucketPistion() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.bucket);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ConfigClient.runtimeConfigUpdate();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.bucket.toggleBucketPosition();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.bucket.isDownLimitHit() || Robot.bucket.isUpLimitHit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.bucket.haltBucket();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.bucket.haltBucket();
    }
}

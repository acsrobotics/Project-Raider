package org.usfirst.frc.team4716.robot.commands.Bucket;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.subsystems.Bucket.Direction;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BucketMove extends Command {

	Direction dir;
	
	
    public BucketMove(Direction dir) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.bucket);
    	this.dir = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.bucket.moveBucket(dir);
//    	Robot.bucket.MOTOR_BUCKET_OPS.set(-0.5);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Lets go");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	return !Robot.bucket.isDownLimitHit();
    	if(this.dir.equals(Direction.UP)){
    		return Robot.bucket.isUpLimitHit() ;
    	}else if(this.dir.equals(Direction.DOWN)){
    		return Robot.bucket.isDownLimitHit();
    	}else{
    		return true;
    	}
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

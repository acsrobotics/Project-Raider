package org.usfirst.frc.team4716.robot.commands.Bucket;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.subsystems.Bucket.Direction;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LaunchEjectPiston extends Command {

    public LaunchEjectPiston() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.bucket);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.bucket.getElevatorState().equals(Direction.DOWN)){
	    	if(Robot.bucket.isPoked()){
	    		System.out.println("Poker out");
	            Robot.bucket.togglePokePosition();
	    	}
	    	
	    	try {
				Thread.sleep(150);
				Robot.bucket.togglePokePosition();;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

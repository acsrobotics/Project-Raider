package org.usfirst.frc.team4716.robot.commands.Auto;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.commands.Bucket.SetBucketPosition;
import org.usfirst.frc.team4716.robot.commands.Bucket.ToggleEjectPiston;
import org.usfirst.frc.team4716.robot.commands.Bucket.ToggleElevator;
import org.usfirst.frc.team4716.robot.subsystems.Bucket.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BucketShoot extends CommandGroup {
    
    public BucketShoot() {
    	addSequential(new SetBucketPosition(Direction.UP));
    	if(!Robot.bucket.getElevatorState().equals(Direction.DOWN)){
    		addSequential(new ToggleElevator());
    	}
    	if(!Robot.bucket.isPoked()){
    		addSequential(new ToggleEjectPiston());
    		addSequential(new ToggleEjectPiston());
    	}
    	if(Robot.bucket.getElevatorState().equals(Direction.DOWN)){
    		addSequential(new ToggleElevator());
    	}
    	addSequential(new SetBucketPosition(Direction.DOWN));
    	
    }
}

package org.usfirst.frc.team4716.robot.commands.Auto;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.commands.Bucket.EnergizeIntakeMotor;
import org.usfirst.frc.team4716.robot.commands.Bucket.SetBucketPosition;
import org.usfirst.frc.team4716.robot.commands.Bucket.ToggleElevator;
import org.usfirst.frc.team4716.robot.subsystems.Bucket.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BucketIntake extends CommandGroup {
    
    public  BucketIntake() {
    	
    	addSequential(new SetBucketPosition(Direction.DOWN));
    	addParallel(new EnergizeIntakeMotor(Direction.IN));
    	if(!Robot.bucket.getElevatorState().equals(Direction.DOWN)){
    		addParallel(new ToggleElevator());
    	}
    }
}

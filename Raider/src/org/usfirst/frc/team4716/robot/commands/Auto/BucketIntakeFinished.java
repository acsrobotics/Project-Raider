package org.usfirst.frc.team4716.robot.commands.Auto;

import org.usfirst.frc.team4716.robot.commands.Bucket.DeEnergizeIntakeMotor;
import org.usfirst.frc.team4716.robot.commands.Bucket.ToggleBucket;
import org.usfirst.frc.team4716.robot.commands.Bucket.ToggleElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BucketIntakeFinished extends CommandGroup {
    
    public  BucketIntakeFinished() {
    	addParallel(new ToggleElevator());
    	addParallel(new DeEnergizeIntakeMotor());
    	addSequential(new ToggleBucket());
    }
}

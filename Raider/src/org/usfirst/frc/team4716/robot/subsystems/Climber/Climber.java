package org.usfirst.frc.team4716.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	CL_Extension ARM_EXTENSION;
	CL_Lift      ARM_LIFT;
	
    public Climber() {
    	ARM_EXTENSION = CL_Extension.getCL_Extension();
    	ARM_LIFT      = CL_Lift.getCL_Lift();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public CL_Extension getArmExtension(){
    	return this.ARM_EXTENSION;
    }
    
    public CL_Lift getArmLift(){
    	return this.ARM_LIFT;
    }
}

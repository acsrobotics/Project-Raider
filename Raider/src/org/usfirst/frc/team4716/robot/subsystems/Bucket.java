package org.usfirst.frc.team4716.robot.subsystems;

import org.usfirst.frc.team4716.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Bucket extends Subsystem {
    
	SpeedController MOTOR_BUCKET_OPS,
					MOTOR_BALL_INTAKE;
	
	DigitalInput SWITCH_UP,
				 SWITCH_DOWN;
	
	DoubleSolenoid PISTON_EJECT;
	boolean isPoked;

	public Bucket(){

		MOTOR_BUCKET_OPS = new Victor(RobotMap.MOTOR_BUCKET_OPS_PORT);
		MOTOR_BALL_INTAKE = new Victor(RobotMap.MOTOR_BALL_INTAKE_PORT);
		
		SWITCH_UP = new DigitalInput(RobotMap.SWTICH_UP_PORT);
		SWITCH_DOWN = new DigitalInput(RobotMap.SWITCH_DOWN_PORT);
		
		PISTON_EJECT = new DoubleSolenoid(RobotMap.PISTON_EJECT_MODULE_NUMBER
										, RobotMap.PISTON_EJECT_FORWARD_CHANNEL
										, RobotMap.PISTON_EJECT_REVERSE_CHANNEL);
		PISTON_EJECT.set(DoubleSolenoid.Value.kReverse);
		isPoked = false;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void togglePokePosition(){
    	if(this.isPoked){
    		this.isPoked = false;
    		this.setPokePosition(this.isPoked);
    	}
    	if(!this.isPoked){
    		this.isPoked = true;
    		this.setPokePosition(this.isPoked);
    	}
    }
    
    public void setPokePosition(boolean isPoked){
    	if(isPoked){
    		PISTON_EJECT.set(DoubleSolenoid.Value.kForward);
    		this.isPoked = true;
    	}
    	if(!isPoked){
    		PISTON_EJECT.set(DoubleSolenoid.Value.kReverse);
    		this.isPoked = false;
    	}
    }
    
    public boolean isUpLimitHit(){
    	return this.SWITCH_UP.get();
    }
    
    public boolean isDownLimitHit(){
    	return this.SWITCH_DOWN.get();
    }
    
}


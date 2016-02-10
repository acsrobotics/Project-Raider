package org.usfirst.frc.team4716.robot.subsystems;

import org.usfirst.frc.team4716.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
    
	SpeedController			CLIMBER_MOTOR,
							ANGLE_MOTOR;
	
	DigitalInput			limitSwitchStandardPosition,
							limitSwitch;
	
	Encoder					angleEncoder,
							extensionEncoder;
							
    
	public Climber() {}
//    	CLIMBER_MOTOR = new Victor(RobotMap.MOTOR_CLIMBER);
//    	ANGLE_MOTOR = new Victor(RobotMap.MOTOR_ANGLE);
//    	
//    	angleEncoder = new Encoder(RobotMap.ENCODER_MOTOR_ANGLE_A, RobotMap.ENCODER_MOTOR_ANGLE_B);
//    	extensionEncoder = new Encoder(RobotMap.ENCODER_MOTOR_CLIMBER_A, RobotMap.ENCODER_MOTOR_CLIMBER_B);
//    	
//    	limitSwitchStandardPosition = new DigitalInput(0);
//    }
//	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
//    
//    
//    public void setStandardPosition(double speed) {
//    	if (limitSwitchStandardPosition.get() == true){
//    		ANGLE_MOTOR.set(0);
//    		angleEncoder.reset();
//    	} else {
//    		ANGLE_MOTOR.set(speed);
//    	}
//    }
//    
//    public void setSpeedExtendArm(double speed) {
//    	CLIMBER_MOTOR.set(speed);
//    }
//    
//    public void setSpeedExtendArm(double speed, double length) {
//    	if(length > extensionEncoder.get()){
//    		CLIMBER_MOTOR.set(0);
//    	}else{
//    		CLIMBER_MOTOR.set(speed);
//    	}
//    }
//    
//    public void setSpeedArmAngle(double speed) {
//    	ANGLE_MOTOR.set(speed);
//    }
//    
//    public void setSpeedArmAngle(double speed, double length) {
//    	if(length > angleEncoder.get()){
//    		ANGLE_MOTOR.set(0);
//    	}else{
//    		ANGLE_MOTOR.set(speed);
//    	}
//    }
//    
    
    
//    public void climbTower(double speed, double height) {
//    	if (angleEncoder.get() > height) {
//    		ANGLE_MOTOR.set(speed);
//    	} else {
//    		ANGLE_MOTOR.set(0);
//    	}
//    	
//    	if (extensionEncoder.get() < 100) {
//    		CLIMBER_MOTOR.set(.75);
//    	} else {
//    		CLIMBER_MOTOR.set(0);
//    	}
//    }
    
}


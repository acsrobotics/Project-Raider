package org.usfirst.frc.team4716.robot.subsystems.Climber;

import org.usfirst.frc.team4716.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * In case everything doesn't work 
 */
@Deprecated
public class Climber_Regular extends Subsystem {
    
	SpeedController			EXTENSION_MOTOR,
							ANGLE_MOTOR;
	
	DigitalInput			LIMIT_SWITCH_STANDARD_POSITION,
							LIMIT_SWITCH;
	
	Encoder					ANGLE_ENCODER,
							EXTENSION_ENCODER;
							
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Climber_Regular() {
    	EXTENSION_MOTOR = new VictorSP(RobotMap.MOTOR_EXTENSION_PORT);
    	ANGLE_MOTOR = new VictorSP(RobotMap.MOTOR_ANGLE_PORT);
    	
    	ANGLE_ENCODER = new Encoder(RobotMap.ENCODER_MOTOR_ANGLE_A, RobotMap.ENCODER_MOTOR_ANGLE_B);
    	EXTENSION_ENCODER = new Encoder(RobotMap.ENCODER_MOTOR_EXTENSION_A, RobotMap.ENCODER_MOTOR_EXTENSION_B);
    	
    	LIMIT_SWITCH_STANDARD_POSITION = new DigitalInput(RobotMap.LIMIT_SWITCH_EXTENSION);
    	LIMIT_SWITCH = new DigitalInput(RobotMap.LIMIT_SWITCH_LIFT);
    }
    
//    public void setStandardPosition() {
//		ANGLE_MOTOR.set(-.5);
//    	if (LIMIT_SWITCH_STANDARD_POSITION.get() == true){
//    		ANGLE_MOTOR.set(0);
//    		ANGLE_ENCODER.reset();
//    	} else {
//    	}
//    }
    
    public void reset(){
    	this.resetAngleEncoder();
    	this.resetExtensionEncoder();
    }
    
    public void resetAngleEncoder(){
    	this.ANGLE_ENCODER.reset();
    }
    
    public void resetExtensionEncoder(){
    	this.EXTENSION_ENCODER.reset();
    }
    
    public void setArmExtendingSpeed(double extendSpeed) {
    	EXTENSION_MOTOR.set(extendSpeed);
    }
    
    public void setArmAngularSpeed(double angleSpeed) {
    	ANGLE_MOTOR.set(angleSpeed);
    }
    
    public double getExtensionEncoderDistance() {
    	return EXTENSION_ENCODER.getDistance();
    }
    
    public double getAngleEncoderDistance() {
    	return ANGLE_ENCODER.getDistance();
    }

    
}
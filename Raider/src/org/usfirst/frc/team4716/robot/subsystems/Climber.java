package org.usfirst.frc.team4716.robot.subsystems;

import org.usfirst.frc.team4716.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * In case everything doesn't work 
 */
public class Climber extends Subsystem {
    
	
	DigitalInput			LIMIT_SWITCH_EXTENSION,
							LIMIT_SWITCH_ANGULAR;
	
	Encoder ENCODER_ANGLE;
	
	Talon    MOTOR_ANGULAR;
	CANTalon MOTOR_EXTENSION;
							
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Climber() {
//    	MOTOR_EXTENSION = new CANTalon(0);
    	//MOTOR_ANGULAR = new Talon(0);
    	
    	//ENCODER_ANGLE = new Encoder(aSource, bSource)
    	
//    	EXTENSION_MOTOR = new VictorSP(RobotMap.MOTOR_EXTENSION_PORT);
//    	ANGLE_MOTOR = new VictorSP(RobotMap.MOTOR_ANGLE_PORT);
//    	
//    	ANGLE_ENCODER = new Encoder(RobotMap.ENCODER_MOTOR_ANGLE_A, RobotMap.ENCODER_MOTOR_ANGLE_B);
//    	EXTENSION_ENCODER = new Encoder(RobotMap.ENCODER_MOTOR_EXTENSION_A, RobotMap.ENCODER_MOTOR_EXTENSION_B);
//    	
//    	LIMIT_SWITCH_EXTENSION = new DigitalInput(RobotMap.LIMIT_SWITCH_EXTENSION);
//    	LIMIT_SWITCH_ANGULAR = new DigitalInput(RobotMap.LIMIT_SWITCH_LIFT);
    }
    
//    public void setStandardPosition() {
//		ANGLE_MOTOR.set(-.5);
//    	if (LIMIT_SWITCH_STANDARD_POSITION.get() == true){
//    		ANGLE_MOTOR.set(0);
//    		ANGLE_ENCODER.reset();
//    	} else {
//    	}
//    }

    public CANTalon getExtensionMotor(){
    	return this.MOTOR_EXTENSION;
    }
    
}
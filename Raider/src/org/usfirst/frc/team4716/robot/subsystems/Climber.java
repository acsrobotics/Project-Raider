package org.usfirst.frc.team4716.robot.subsystems;


import org.usfirst.frc.team4716.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * In case everything doesn't work 
 */
public class Climber extends Subsystem {
    
	final double GAME_LENGTH = 150.0;
	
	boolean overRide = false;
	
	CANTalon        MOTOR_CLIMB;
	DoubleSolenoid  EJECTING_LOCK;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Climber() {
    	MOTOR_CLIMB = new CANTalon(0);
    	EJECTING_LOCK = new DoubleSolenoid(RobotMap.EJECT_LOCK_MODULE
    									, RobotMap.EJECT_LOCK_FORWARD_CH
    									, RobotMap.EJECT_LOCK_BACKWARD_CH);
    	// lock elevator 
    	EJECTING_LOCK.set(Value.kForward);
    	
    }

    public void CANInit(){
    	MOTOR_CLIMB.enable();
    	
    	MOTOR_CLIMB.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
//    	MOTOR_CLIMB.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
//    	MOTOR_CLIMB.setPID(1.0, 0.0, 0.0);
//    	MOTOR_CLIMB.setInverted(false);
//    	MOTOR_CLIMB.setPosition(0);
    }
    
    public void setElevator(double speed){
    	MOTOR_CLIMB.set(speed);
    }
    
    public void CANHalt(){
    	MOTOR_CLIMB.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	MOTOR_CLIMB.set(0);
    }
    
    public void unlockElevator(){
    	if(GAME_LENGTH - Timer.getMatchTime() < 20 || overRide){
    		this.EJECTING_LOCK.set(Value.kReverse);
    	}
    }
    
    public void lockElevator(){
    	this.EJECTING_LOCK.set(Value.kForward);
    }
    
    public CANTalon getExtensionMotor(){
    	return this.MOTOR_CLIMB;
    }
    
    public void override(boolean status){
    	overRide = status;
    }
    
    public double getPosition(){
    	return this.MOTOR_CLIMB.getPosition();
    }
    
    
}
package org.usfirst.frc.team4716.robot.subsystems;

import org.usfirst.frc.team4716.robot.RobotMap;
import org.usfirst.frc.team4716.robot.commands.Bucket.Standby;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Bucket extends Subsystem {
//    
	public enum Direction {
		UP, DOWN, IN, OUT
	}
//	
//	
//	
	public SpeedController MOTOR_BUCKET_OPS,
					MOTOR_BALL_INTAKE;
////	
	DigitalInput LIMIT_UP,
				 LIMIT_DOWN;
////	
	DoubleSolenoid PISTON_EJECT;
	Direction PISTON_EJECT_STATE;
	
	DoubleSolenoid PISTON_ELEVATOR;
	Direction PISTON_ELEVATOR_STATE;
	
	public AnalogInput ultraProximateSensor;
//	
	
	public Bucket(){
//
		MOTOR_BUCKET_OPS = new Talon(4);
		MOTOR_BALL_INTAKE = new Talon(2);
//		
		LIMIT_UP = new DigitalInput(0); 
		LIMIT_DOWN = new DigitalInput(1);
//		
		PISTON_EJECT = new DoubleSolenoid(RobotMap.PISTON_EJECT_MODULE_NUMBER
										, RobotMap.PISTON_EJECT_FORWARD_CHANNEL
										, RobotMap.PISTON_EJECT_REVERSE_CHANNEL);
		
		PISTON_EJECT_STATE = Direction.IN;
		
		PISTON_ELEVATOR = new DoubleSolenoid(RobotMap.PISTON_ELEVATOR_MODULE_NUMBER
											, RobotMap.PISTON_ELEVATOR_FORWARD_CHANNEL
											, RobotMap.PISTON_ELEVATOR_REVERSE_CHANNEL);
		PISTON_ELEVATOR.set(DoubleSolenoid.Value.kReverse);
		PISTON_ELEVATOR_STATE = Direction.UP;
		
		ultraProximateSensor = new AnalogInput(0);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Standby());
    }
    
    //-----------IntakeMotor method---------//
    
    public void setIntakeMotor(Direction dir){
    	if(dir.equals(Direction.IN)){
    		MOTOR_BALL_INTAKE.set(RobotMap.MOTOR_INTAKE_SPEED);
    	}else{
    		MOTOR_BALL_INTAKE.set(RobotMap.MOTOR_OUTTAKE_SPEED);
    	}
    }
    
    public void haltIntakeMotor(){
    	MOTOR_BALL_INTAKE.set(0);
    }
    
    //-----------Elevator methods-----------//
    
    public void toggleElevator(){
    	if(PISTON_ELEVATOR_STATE == Direction.DOWN){
    		PISTON_ELEVATOR.set(DoubleSolenoid.Value.kReverse);
    		PISTON_ELEVATOR_STATE = Direction.UP;
    	}else{
    		PISTON_ELEVATOR.set(DoubleSolenoid.Value.kForward);
    		PISTON_ELEVATOR_STATE = Direction.DOWN;
    	}
    }
    
    public Direction getElevatorState(){
    	return this.PISTON_ELEVATOR_STATE;
    }

    //-----------Bucket methods-------------//
    
    public void moveBucket(Direction dir){
    	if(dir.equals(Direction.UP)){
    		MOTOR_BUCKET_OPS.set(RobotMap.BUCKET_FOLD_SPEED);
    	}
    	if(dir.equals(Direction.DOWN)){
    		MOTOR_BUCKET_OPS.set(RobotMap.BUCKET_UNFOLD_SPEED);
    	}
    }

    public void haltBucket(){
    	this.MOTOR_BUCKET_OPS.set(0.0);
    }
    
    public boolean isUpLimitHit(){
    	return !this.LIMIT_UP.get();
    }
    
    public boolean isDownLimitHit(){
    	return this.LIMIT_DOWN.get();
    }

    
    //------------Piston methods-------------//
    
    public void setEjectPosition(Direction dir){
    	if(dir.equals(Direction.IN)){
    		PISTON_EJECT.set(DoubleSolenoid.Value.kReverse);
    		PISTON_EJECT_STATE = Direction.IN;
    	}else if(dir.equals(Direction.OUT)){
    		PISTON_EJECT.set(DoubleSolenoid.Value.kForward);
    		PISTON_EJECT_STATE = Direction.OUT;
    		
    	}
    }
    
    public void togglePokePosition(){
    	
    	PISTON_EJECT.set(DoubleSolenoid.Value.kForward);
    	try {
			Thread.sleep(150);
			PISTON_EJECT.set(DoubleSolenoid.Value.kReverse);
		} catch (InterruptedException e) {

		}
//    	System.out.println("BIU");
//    	if(this.PISTON_EJECT_STATE.equals(Direction.IN)){
//    		PISTON_EJECT.set(DoubleSolenoid.Value.kReverse);
//    		PISTON_EJECT_STATE = Direction.OUT;
//    	}
//    	else if(this.PISTON_EJECT_STATE.equals(Direction.OUT)){
//			PISTON_EJECT.set(DoubleSolenoid.Value.kForward);
//			PISTON_EJECT_STATE = Direction.IN;
//    	}
    }


    public boolean isPoked(){
    	return PISTON_EJECT_STATE.equals(Direction.IN) ? false : true;
    }
    
}


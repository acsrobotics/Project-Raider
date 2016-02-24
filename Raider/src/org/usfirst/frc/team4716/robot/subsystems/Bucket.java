package org.usfirst.frc.team4716.robot.subsystems;

import org.usfirst.frc.team4716.robot.RobotMap;


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
//	public enum Direction {
//		UP, DOWN, IN, OUT
//	}
//	
//	
//	
//	SpeedController MOTOR_BUCKET_OPS,
//					MOTOR_BALL_INTAKE;
//	
//	DigitalInput LIMIT_UP,
//				 LIMIT_DOWN;
//	
//	DoubleSolenoid PISTON_EJECT;
//	boolean isPoked;
//	
//	DoubleSolenoid PISTON_ELEVATOR;
//	Direction PISTON_ELEVATOR_STATE;
//	
	
	public Bucket(){
//
//		MOTOR_BUCKET_OPS = new Talon(RobotMap.MOTOR_BUCKET_OPS_PORT);
//		MOTOR_BALL_INTAKE = new Talon(RobotMap.MOTOR_BALL_INTAKE_PORT);
//		
//		LIMIT_UP = new DigitalInput(RobotMap.SWTICH_UP_PORT);
//		LIMIT_DOWN = new DigitalInput(RobotMap.SWITCH_DOWN_PORT);
//		
//		PISTON_EJECT = new DoubleSolenoid(RobotMap.PISTON_EJECT_MODULE_NUMBER
//										, RobotMap.PISTON_EJECT_FORWARD_CHANNEL
//										, RobotMap.PISTON_EJECT_REVERSE_CHANNEL);
//		
//		PISTON_EJECT.set(DoubleSolenoid.Value.kReverse);
//		isPoked = false;
//		
//		PISTON_ELEVATOR = new DoubleSolenoid(RobotMap.PISTON_ELEVATOR_MODULE_NUMBER
//											, RobotMap.PISTON_ELEVATOR_FORWARD_CHANNEL
//											, RobotMap.PISTON_ELEVATOR_REVERSE_CHANNEL);
//		PISTON_ELEVATOR.set(DoubleSolenoid.Value.kReverse);
//		PISTON_ELEVATOR_STATE = Direction.UP;
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new Standby());
    }
//    
//    //-----------Elevator methods-----------//
//    
//    public void toggleElevator(){
//    	if(PISTON_ELEVATOR_STATE == Direction.DOWN){
//    		PISTON_ELEVATOR.set(DoubleSolenoid.Value.kReverse);
//    		PISTON_ELEVATOR_STATE = Direction.UP;
//    	}else{
//    		PISTON_ELEVATOR.set(DoubleSolenoid.Value.kForward);
//    		PISTON_ELEVATOR_STATE = Direction.DOWN;
//    	}
//    }
//    
//    public Direction getElevatorState(){
//    	return this.PISTON_ELEVATOR_STATE;
//    }
//    
//    //-----------Intake motor methods-------//
//    
//    public void energizeIntakeMotor(Direction direction){
//    	if(direction == Direction.IN){
//    		this.MOTOR_BALL_INTAKE.set(-RobotMap.MOTOR_INTAKE_SPEED);
//    	}else{
//    		this.MOTOR_BALL_INTAKE.set(RobotMap.MOTOR_INTAKE_SPEED);
//    	}
//    }
//    
//    public void haltIntakeMotor(){
//    	this.MOTOR_BALL_INTAKE.set(0.0);
//    }
//    
//    //-----------Bucket methods-------------//
//    
//    public void moveBucket(Direction dir){
//    	if(dir.equals(Direction.UP)){
//    		MOTOR_BUCKET_OPS.set(RobotMap.BUCKET_FOLD_SPEED);
//    	}
//    	if(dir.equals(Direction.DOWN)){
//    		MOTOR_BUCKET_OPS.set(RobotMap.BUCKET_UNFOLD_SPEED);
//    	}
//    }
//
//    public void toggleBucketPosition(){
//    	if(this.isUpLimitHit()){
//    		this.moveBucket(Direction.DOWN);
//    	}
//    	if(this.isDownLimitHit()){
//    		this.moveBucket(Direction.UP);
//    	}
//    }
//    
//    public void haltBucket(){
//    	this.MOTOR_BUCKET_OPS.set(0.0);
//    }
//
//    public boolean isUpLimitHit(){
//    	return this.LIMIT_UP.get();
//    }
//    
//    public boolean isDownLimitHit(){
//    	return this.LIMIT_DOWN.get();
//    }
//    
//    //------------Piston methods-------------//
//    
//    public void togglePokePosition(){
//    	if(this.isPoked){
//    		this.isPoked = false;
//    		this.setPokePosition(this.isPoked);
//    	}
//    	if(!this.isPoked){
//    		if(this.PISTON_ELEVATOR_STATE == Direction.DOWN){
//    			this.isPoked = true;
//    			this.setPokePosition(this.isPoked);
//    		}
//    	}
//    }
//    
//    public void setPokePosition(boolean isPoked){
//    	if(isPoked){
//    		PISTON_EJECT.set(DoubleSolenoid.Value.kForward);
//    		this.isPoked = isPoked;
//    	}
//    	if(!isPoked){
//    		PISTON_EJECT.set(DoubleSolenoid.Value.kReverse);
//    		this.isPoked = isPoked;
//    	}
//    }
//    
//    public boolean isPoked(){
//    	return this.isPoked;
//    }
//    
//    
}


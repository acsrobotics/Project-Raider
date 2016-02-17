package org.usfirst.frc.team4716.robot.subsystems;

import org.usfirst.frc.team4716.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Bucket extends Subsystem {
    
	public enum Direction {
		UP, DOWN
	}
	
	SpeedController MOTOR_BUCKET_OPS,
					MOTOR_BALL_INTAKE;
	
	DigitalInput LIMIT_UP,
				 LIMIT_DOWN;
	
	DoubleSolenoid PISTON_EJECT;
	boolean isPoked;

	Thread BACKGROUND_JOB;
//	
	public Bucket(){
//
		MOTOR_BUCKET_OPS = new Talon(2);
		MOTOR_BALL_INTAKE = new Talon(3);
		
		LIMIT_UP = new DigitalInput(0);
		LIMIT_DOWN = new DigitalInput(1);
		
		PISTON_EJECT = new DoubleSolenoid(1
										, 0
										, 1);
		PISTON_EJECT.set(DoubleSolenoid.Value.kReverse);
		isPoked = false;
		
//		BACKGROUND_JOB = new Thread(new BucketBackgroundTask(this.MOTOR_BALL_INTAKE));
//		BACKGROUND_JOB.start();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //-----------Bucket methods-------------//
    
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
//    		this.isPoked = true;
//    		this.setPokePosition(this.isPoked);
//    	}
//    }
//    
//    public void setPokePosition(boolean isPoked){
//    	if(isPoked){
//    		PISTON_EJECT.set(DoubleSolenoid.Value.kForward);
//    		this.isPoked = true;
//    	}
//    	if(!isPoked){
//    		PISTON_EJECT.set(DoubleSolenoid.Value.kReverse);
//    		this.isPoked = false;
//    	}
//    }
//    
//    public boolean isPoked(){
//    	return this.isPoked;
//    }
//    
//    class BucketBackgroundTask implements Runnable{
//    	
//    	private SpeedController intakeMotor;
//    	
//    	public BucketBackgroundTask(SpeedController intakeMotor){
//    		this.intakeMotor = intakeMotor;
//    	}
//    	
//    	public void run(){
//    		intakeMotor.set(RobotMap.BUCKET_INTAKE_SPEED);
//    	}
//    	
//    }
//    
}


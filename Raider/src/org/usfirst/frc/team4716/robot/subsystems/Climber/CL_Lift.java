package org.usfirst.frc.team4716.robot.subsystems.Climber;

import org.usfirst.frc.team4716.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class CL_Lift extends PIDSubsystem {

	public static CL_Lift self = null;

	SpeedController ANGLE_MOTOR;

	DigitalInput  LIMIT_SWITCH;

	Encoder ANGLE_ENCODER;
	
	private double motor_speed;
	
    // Initialize your subsystem here
    private CL_Lift() {
    	super("CL_Lift", 1.0, 0.0, 0.2);
    	setAbsoluteTolerance(50);
    	getPIDController().setContinuous(true);

    	ANGLE_MOTOR = new VictorSP(RobotMap.MOTOR_ANGLE_PORT);
    	ANGLE_MOTOR.set(0.0);
    	
    	ANGLE_ENCODER = new Encoder(RobotMap.ENCODER_MOTOR_ANGLE_A, RobotMap.ENCODER_MOTOR_ANGLE_B);
    	ANGLE_ENCODER.reset();
    	
    	LIMIT_SWITCH = new DigitalInput(RobotMap.LIMIT_SWITCH_LIFT);
    }
    
    public static CL_Lift getCL_Lift(){
    	if(CL_Lift.self == null){
    		CL_Lift.self = new CL_Lift();
    	}
    	return CL_Lift.self;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
    	return LIMIT_SWITCH.get() ? 0.0 : (double)ANGLE_ENCODER.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	this.motor_speed = output;
    	this.ANGLE_MOTOR.set(output);
    }
    
    public boolean isStopped(){
    	return this.motor_speed == 0 ? true : false;
    }
    
    public boolean isLimitHit(){
    	boolean isHit = this.LIMIT_SWITCH.get();
    	if(isHit){ this.reset();}
    	return isHit;
    }

    public void reset(){
    	this.ANGLE_ENCODER.reset();
    }
    
    public void setArmLiftingSpeed(double extendSpeed) {
    	this.motor_speed = extendSpeed;
    	ANGLE_MOTOR.set(extendSpeed);
    }
    
    public double getExtensionEncoderDistance() {
    	return ANGLE_ENCODER.getDistance();
    }
    
}

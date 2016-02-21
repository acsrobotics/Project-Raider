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
public class CL_Extension extends PIDSubsystem {

	private static CL_Extension self = null;
	
	SpeedController EXTENSION_MOTOR;

	DigitalInput LIMIT_SWITCH;

	Encoder EXTENSION_ENCODER;
	
	private double motor_speed;
	
    // Initialize your subsystem here
    private CL_Extension() {
    	super("CL_Extension", 1.0, 0.0, 0.2);
    	setAbsoluteTolerance(50);
    	getPIDController().setContinuous(true);

    	EXTENSION_MOTOR = new VictorSP(RobotMap.MOTOR_EXTENSION_PORT);
    	EXTENSION_MOTOR.set(0.0);
    	
    	EXTENSION_ENCODER = new Encoder(RobotMap.ENCODER_MOTOR_EXTENSION_A, RobotMap.ENCODER_MOTOR_EXTENSION_B);
    	EXTENSION_ENCODER.reset();
    	
    	LIMIT_SWITCH = new DigitalInput(RobotMap.LIMIT_SWITCH_EXTENSION);
    }
    
    public static CL_Extension getCL_Extension(){
    	if(CL_Extension.self == null){
    		CL_Extension.self = new CL_Extension();
    	}
    	return CL_Extension.self;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
    	return LIMIT_SWITCH.get() ? 0.0 : (double)EXTENSION_ENCODER.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	this.motor_speed = output;
    	EXTENSION_MOTOR.set(output);
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
    	this.EXTENSION_ENCODER.reset();
    }
    
    public void setArmExtendingSpeed(double extendSpeed) {
    	this.motor_speed = extendSpeed;
    	EXTENSION_MOTOR.set(extendSpeed);
    }
    
    public double getExtensionEncoderDistance() {
    	return EXTENSION_ENCODER.getDistance();
    }

}

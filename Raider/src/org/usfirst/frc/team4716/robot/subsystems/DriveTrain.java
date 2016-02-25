package org.usfirst.frc.team4716.robot.subsystems;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.commands.DriveTrain.JoystickDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	public enum PositionStatusCode {
		ALL_IN,
		ALL_OUT,
		LEFT_IN_RIGHT_OUT,
		LEFT_OUT_RIGHT_IN,
		FRONT_OUT_BACK_IN,
		FRONT_IN_BACK_OUT,
		FRONT_LEFT_OUT_REST_IN,
		FRONT_RIGHT_OUT_REST_IN,
		BACK_LEFT_OUT_REST_IN,
		BACK_RIGHT_OUT_REST_IN,
		FUBAR // Fucked-Up-Beyond-Recognition
	}
    
	SpeedController 		MOTOR_DRIVE_FRONT_LEFT,
   							MOTOR_DRIVE_FRONT_RIGHT,
   							MOTOR_DRIVE_BACK_LEFT,
   							MOTOR_DRIVE_BACK_RIGHT;

	DoubleSolenoid			SOLENOID_DRIVE_FRONT_LEFT,
							SOLENOID_DRIVE_FRONT_RIGHT,
							SOLENOID_DRIVE_BACK_LEFT,
							SOLENOID_DRIVE_BACK_RIGHT;

	AnalogGyro 				gyro;

	Encoder 				encoderDriveLeft,
							encoderDriveRight;

	Ultrasonic 				ultrasonicLeft,
							ultrasonicRight;
	
	RobotDrive 				drive;
	
	public boolean 	objectDetected;
	
	public DriveTrain(){
		MOTOR_DRIVE_FRONT_LEFT = new Victor(0);
		MOTOR_DRIVE_FRONT_RIGHT = new Victor(1);
		MOTOR_DRIVE_BACK_LEFT = new Victor(6);
		MOTOR_DRIVE_BACK_RIGHT = new Victor(7);
		drive = new RobotDrive(MOTOR_DRIVE_FRONT_LEFT, MOTOR_DRIVE_FRONT_RIGHT, MOTOR_DRIVE_BACK_LEFT, MOTOR_DRIVE_BACK_RIGHT);
		
		SOLENOID_DRIVE_FRONT_LEFT = new DoubleSolenoid(0,0,1);
		SOLENOID_DRIVE_FRONT_RIGHT = new DoubleSolenoid(0,2,3);
		SOLENOID_DRIVE_BACK_LEFT = new DoubleSolenoid(0,4,5);
		SOLENOID_DRIVE_BACK_RIGHT = new DoubleSolenoid(0,6,7);
		ultrasonicLeft = new Ultrasonic(0,1);
		
//		/*Encoder Initialzation*/
//		encoderDriveLeft = new Encoder(RobotMap.ENCODER_DRIVE_LEFT_PORT_A, RobotMap.ENCODER_DRIVE_LEFT_PORT_B);
//		encoderDriveRight = new Encoder(RobotMap.ENCODER_DRIVE_RIGHT_PORT_A, RobotMap.ENCODER_DRIVE_RIGHT_PORT_B);
//		
//
//		/*Gyro Initialzation*/
//		gyro = new AnalogGyro(RobotMap.GYRO_MAIN_PORT);
//		gyro.setSensitivity(RobotMap.GYRO_SENSITIVITY);

		/*LiveWindow Initialzation*/
		LiveWindow.addActuator("DriveTrain", "Front Left CIM", (Victor) MOTOR_DRIVE_FRONT_LEFT);
		LiveWindow.addActuator("DriveTrain", "Front Right CIM", (Victor) MOTOR_DRIVE_FRONT_RIGHT);
		LiveWindow.addActuator("DriveTrain", "Back Left CIM", (Victor) MOTOR_DRIVE_BACK_LEFT);
		LiveWindow.addActuator("DriveTrain", "Back Right CIM", (Victor) MOTOR_DRIVE_BACK_RIGHT);
		
		LiveWindow.addActuator("DriveTrain", "Front Left Piston", (DoubleSolenoid) SOLENOID_DRIVE_FRONT_LEFT);
		LiveWindow.addActuator("DriveTrain", "Front Right Piston", (DoubleSolenoid) SOLENOID_DRIVE_FRONT_RIGHT);
		LiveWindow.addActuator("DriveTrain", "Back Left Piston", (DoubleSolenoid) SOLENOID_DRIVE_BACK_LEFT);
		LiveWindow.addActuator("DriveTrain", "Back Right Piston", (DoubleSolenoid) SOLENOID_DRIVE_BACK_RIGHT);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new JoystickDrive());
    }
    
    public void reset(){
    	gyro.reset();
    	encoderDriveLeft.reset();
    	encoderDriveRight.reset();
    }
    
    public void gyroReset(){
    	gyro.reset();
    }
    
    public void encoderReset(){
    	encoderDriveLeft.reset();
    	encoderDriveRight.reset();
    }
    
    public void tankDrive(double left, double right){
    	drive.tankDrive(left, right);
    }
    
    public void arcadeDrive(double speed, double turn){
    	drive.arcadeDrive(speed, turn);
    }
    

    
    public void setPosition(PositionStatusCode code){
    	if((code.equals(PositionStatusCode.ALL_OUT) && !this.getPositionStatusCode().equals(PositionStatusCode.ALL_OUT))){
    		this.setSolenoidPosition(DoubleSolenoid.Value.kForward
    							, DoubleSolenoid.Value.kForward
    							, DoubleSolenoid.Value.kForward
    							, DoubleSolenoid.Value.kForward);
    	}else if((code.equals(PositionStatusCode.ALL_IN) && !this.getPositionStatusCode().equals(PositionStatusCode.ALL_IN))){
    		this.setSolenoidPosition(DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse);
    	}else if((code.equals(PositionStatusCode.FRONT_OUT_BACK_IN) && !this.getPositionStatusCode().equals(PositionStatusCode.FRONT_OUT_BACK_IN))){
    		this.setSolenoidPosition(DoubleSolenoid.Value.kForward
					, DoubleSolenoid.Value.kForward
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse);
    	}else if((code.equals(PositionStatusCode.FRONT_IN_BACK_OUT) && !this.getPositionStatusCode().equals(PositionStatusCode.FRONT_IN_BACK_OUT))){
    		this.setSolenoidPosition(DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kForward
					, DoubleSolenoid.Value.kForward);
    	}else if((code.equals(PositionStatusCode.LEFT_IN_RIGHT_OUT) && !this.getPositionStatusCode().equals(PositionStatusCode.LEFT_IN_RIGHT_OUT))){
    		this.setSolenoidPosition(DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kForward
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kForward);
    	}else if((code.equals(PositionStatusCode.LEFT_OUT_RIGHT_IN) && !this.getPositionStatusCode().equals(PositionStatusCode.LEFT_OUT_RIGHT_IN))){
    		this.setSolenoidPosition(DoubleSolenoid.Value.kForward
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kForward
					, DoubleSolenoid.Value.kReverse);
    	}else if((code.equals(PositionStatusCode.FRONT_LEFT_OUT_REST_IN) && !this.getPositionStatusCode().equals(PositionStatusCode.FRONT_LEFT_OUT_REST_IN))){
    		this.setSolenoidPosition(DoubleSolenoid.Value.kForward
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse);
    	}else if((code.equals(PositionStatusCode.FRONT_RIGHT_OUT_REST_IN) && !this.getPositionStatusCode().equals(PositionStatusCode.FRONT_RIGHT_OUT_REST_IN))){
    		this.setSolenoidPosition(DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kForward
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse);
    	}else if((code.equals(PositionStatusCode.BACK_LEFT_OUT_REST_IN) && !this.getPositionStatusCode().equals(PositionStatusCode.BACK_LEFT_OUT_REST_IN))){
    		this.setSolenoidPosition(DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kForward
					, DoubleSolenoid.Value.kReverse);
    	}else if((code.equals(PositionStatusCode.BACK_RIGHT_OUT_REST_IN) && !this.getPositionStatusCode().equals(PositionStatusCode.BACK_RIGHT_OUT_REST_IN))){
    		this.setSolenoidPosition(DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kReverse
					, DoubleSolenoid.Value.kForward);
    	}
    }
    
    
    
//    public void setFrontThing(){
//    	SOLENOID.set(DoubleSolenoid.Value.kForward);
//    }
//    
//    public void setFrontThingBack(){
//    	SOLENOID.set(DoubleSolenoid.Value.kReverse);
//    }
    
    public void setObjectState(boolean set){
    	this.objectDetected = set;
    }
    
    public boolean getObjectState(){
    	return this.objectDetected;
    }
    
    public double getUltrasonicDistance(){
    	return (ultrasonicLeft.getRangeInches() + ultrasonicRight.getRangeInches())/2;
    }
    
    public double getEncoderDistance(){
    	return (encoderDriveLeft.getDistance() + encoderDriveRight.getDistance()) / 2;
    }
    
    public double getEncoderDelta(){
    	return (encoderDriveLeft.getDistance() - encoderDriveRight.getDistance());
    }
    
    public double getGyro(){
    	return gyro.getAngle();
    }
    
    public double getUltrasonicLeft(){
    	return ultrasonicLeft.getRangeInches();
    }
    
    
    // AUTONOMOUS METHODS
    
    /**
     * Drives straight with a given speed. Independent of time.
     * @param _speed
     */
    public void driveStraight(double _speed){
    	double rightpow, leftpow;
    	double angle = gyro.getAngle();
    	double kP = 0.02;
    	if(2 < angle){
    		rightpow =  _speed - kP * angle;
    		tankDrive(rightpow, _speed);
    	}else if(-2 > angle){
    		leftpow =  _speed + kP * angle;
    		tankDrive(_speed, leftpow);
    	}else{
    		tankDrive(_speed,_speed);
    	}
    	
    }
    
    public void driveStraightDude(double speed){
    	tankDrive(speed,speed);
    }
    
    public void setSolenoidPosition(
    		DoubleSolenoid.Value frontLeft,
    		DoubleSolenoid.Value frontRight,
    		DoubleSolenoid.Value backLeft,
    		DoubleSolenoid.Value backRight
    		){
    	this.SOLENOID_DRIVE_BACK_LEFT.set(backLeft);
    	this.SOLENOID_DRIVE_BACK_RIGHT.set(backRight);
    	this.SOLENOID_DRIVE_FRONT_LEFT.set(frontLeft);
    	this.SOLENOID_DRIVE_FRONT_RIGHT.set(frontRight);
    }
    
    public PositionStatusCode getPositionStatusCode(){
    	if(this.SOLENOID_DRIVE_BACK_LEFT.get() == DoubleSolenoid.Value.kForward
    		&& this.SOLENOID_DRIVE_BACK_RIGHT.get() == DoubleSolenoid.Value.kForward
    		&& this.SOLENOID_DRIVE_FRONT_LEFT.get() == DoubleSolenoid.Value.kForward
    		&& this.SOLENOID_DRIVE_FRONT_RIGHT.get() == DoubleSolenoid.Value.kForward){
    		return PositionStatusCode.ALL_OUT;
    	}else if(this.SOLENOID_DRIVE_BACK_LEFT.get() == DoubleSolenoid.Value.kReverse
    		&& this.SOLENOID_DRIVE_BACK_RIGHT.get() == DoubleSolenoid.Value.kReverse
    		&& this.SOLENOID_DRIVE_FRONT_LEFT.get() == DoubleSolenoid.Value.kReverse
    		&& this.SOLENOID_DRIVE_FRONT_RIGHT.get() == DoubleSolenoid.Value.kReverse){
    		return PositionStatusCode.ALL_IN;
    	}else if(this.SOLENOID_DRIVE_BACK_LEFT.get() == DoubleSolenoid.Value.kReverse
    		&& this.SOLENOID_DRIVE_BACK_RIGHT.get() == DoubleSolenoid.Value.kForward
    		&& this.SOLENOID_DRIVE_FRONT_LEFT.get() == DoubleSolenoid.Value.kReverse
    		&& this.SOLENOID_DRIVE_FRONT_RIGHT.get() == DoubleSolenoid.Value.kForward){
    		return PositionStatusCode.LEFT_IN_RIGHT_OUT;
    	}else if(this.SOLENOID_DRIVE_BACK_LEFT.get() == DoubleSolenoid.Value.kForward
    		&& this.SOLENOID_DRIVE_BACK_RIGHT.get() == DoubleSolenoid.Value.kReverse
    		&& this.SOLENOID_DRIVE_FRONT_LEFT.get() == DoubleSolenoid.Value.kForward
    		&& this.SOLENOID_DRIVE_FRONT_RIGHT.get() == DoubleSolenoid.Value.kReverse){
    		return PositionStatusCode.LEFT_OUT_RIGHT_IN;
    	}else if(this.SOLENOID_DRIVE_BACK_LEFT.get() == DoubleSolenoid.Value.kReverse
    		&& this.SOLENOID_DRIVE_BACK_RIGHT.get() == DoubleSolenoid.Value.kReverse
    		&& this.SOLENOID_DRIVE_FRONT_LEFT.get() == DoubleSolenoid.Value.kForward
    		&& this.SOLENOID_DRIVE_FRONT_RIGHT.get() == DoubleSolenoid.Value.kForward){
    		return PositionStatusCode.FRONT_OUT_BACK_IN;
    	}else if(this.SOLENOID_DRIVE_BACK_LEFT.get() == DoubleSolenoid.Value.kForward
    		&& this.SOLENOID_DRIVE_BACK_RIGHT.get() == DoubleSolenoid.Value.kForward
    		&& this.SOLENOID_DRIVE_FRONT_LEFT.get() == DoubleSolenoid.Value.kReverse
    		&& this.SOLENOID_DRIVE_FRONT_RIGHT.get() == DoubleSolenoid.Value.kReverse){
    		return PositionStatusCode.FRONT_IN_BACK_OUT;
    	}else if(this.SOLENOID_DRIVE_BACK_LEFT.get() == DoubleSolenoid.Value.kReverse
        	&& this.SOLENOID_DRIVE_BACK_RIGHT.get() == DoubleSolenoid.Value.kReverse
        	&& this.SOLENOID_DRIVE_FRONT_LEFT.get() == DoubleSolenoid.Value.kForward
        	&& this.SOLENOID_DRIVE_FRONT_RIGHT.get() == DoubleSolenoid.Value.kReverse){
        	return PositionStatusCode.FRONT_LEFT_OUT_REST_IN;
    	}else if(this.SOLENOID_DRIVE_BACK_LEFT.get() == DoubleSolenoid.Value.kReverse
            && this.SOLENOID_DRIVE_BACK_RIGHT.get() == DoubleSolenoid.Value.kReverse
            && this.SOLENOID_DRIVE_FRONT_LEFT.get() == DoubleSolenoid.Value.kReverse
            && this.SOLENOID_DRIVE_FRONT_RIGHT.get() == DoubleSolenoid.Value.kForward){
            return PositionStatusCode.FRONT_RIGHT_OUT_REST_IN;
    	}else if(this.SOLENOID_DRIVE_BACK_LEFT.get() == DoubleSolenoid.Value.kForward
            && this.SOLENOID_DRIVE_BACK_RIGHT.get() == DoubleSolenoid.Value.kReverse
            && this.SOLENOID_DRIVE_FRONT_LEFT.get() == DoubleSolenoid.Value.kReverse
            && this.SOLENOID_DRIVE_FRONT_RIGHT.get() == DoubleSolenoid.Value.kReverse){
            return PositionStatusCode.BACK_LEFT_OUT_REST_IN;
    	}else if(this.SOLENOID_DRIVE_BACK_LEFT.get() == DoubleSolenoid.Value.kReverse
            && this.SOLENOID_DRIVE_BACK_RIGHT.get() == DoubleSolenoid.Value.kForward
            && this.SOLENOID_DRIVE_FRONT_LEFT.get() == DoubleSolenoid.Value.kReverse
            && this.SOLENOID_DRIVE_FRONT_RIGHT.get() == DoubleSolenoid.Value.kReverse){
            return PositionStatusCode.BACK_RIGHT_OUT_REST_IN;
    	}else {
    		return PositionStatusCode.FUBAR;
    	}
    }
    
    
    /**
     * Drives at an angle with a given speed. Independent of time.
     * @param _speed
     * @param _angle
     * @param _isRight
     */
    public void turnWithAngle(double _speed, double _angle, boolean _isRight){
    	double leftpow,rightpow;
    	if (_isRight == true && Math.abs(gyro.getAngle()) < _angle){
    		rightpow = _speed - (gyro.getAngle()/_angle) * _speed;
    		tankDrive(0, rightpow);
    	}else if(_isRight == false && Math.abs(gyro.getAngle()) < _angle){
    		leftpow = _speed - (gyro.getAngle()/_angle) * _speed;
    		tankDrive(leftpow,0);
    	}
    }
    
    
    // LOG
    public void log(){
    	SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
		SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
		SmartDashboard.putNumber("Right Drive Encoder", -encoderDriveLeft.getDistance());
		SmartDashboard.putNumber("Left Drive Encoder", encoderDriveRight.getDistance());
		SmartDashboard.putNumber("Delta Encoder",getEncoderDelta());
		SmartDashboard.putNumber("Drive Speed", Robot.oi.getJoyY());
    }
}


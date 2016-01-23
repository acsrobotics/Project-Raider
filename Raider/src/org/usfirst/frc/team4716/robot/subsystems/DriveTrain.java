package org.usfirst.frc.team4716.robot.subsystems;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.RobotMap;
import org.usfirst.frc.team4716.robot.commands.DriveTrain.JoystickDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
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
    
	SpeedController 		MOTOR_DRIVE_FRONT_LEFT,
   							MOTOR_DRIVE_FRONT_RIGHT,
   							MOTOR_DRIVE_BACK_LEFT,
   							MOTOR_DRIVE_BACK_RIGHT;

	Solenoid				SOLENOID_DRIVE_FRONT_LEFT,
							SOLENOID_DRIVE_FRONT_RIGHT,
							SOLENOID_DRIVE_BACK_LEFT,
							SOLENOID_DRIVE_BACK_RIGHT;

	AnalogGyro 				gyro;

	Encoder 				encoderDriveLeft,
							encoderDriveRight;

	Ultrasonic 				ultrasonicLeft,
							ultrasonicRight;
	
	RobotDrive 				drive;
	
	public DriveTrain(){
		MOTOR_DRIVE_FRONT_LEFT = new Victor(RobotMap.MOTOR_DRIVE_FRONT_LEFT_PORT);
		MOTOR_DRIVE_FRONT_RIGHT = new Victor(RobotMap.MOTOR_DRIVE_FRONT_RIGHT_PORT);
		MOTOR_DRIVE_BACK_LEFT = new Victor(RobotMap.MOTOR_DRIVE_BACK_LEFT_PORT);
		MOTOR_DRIVE_BACK_RIGHT = new Victor(RobotMap.MOTOR_DRIVE_BACK_RIGHT_PORT);
		drive = new RobotDrive(MOTOR_DRIVE_FRONT_LEFT, MOTOR_DRIVE_FRONT_RIGHT, MOTOR_DRIVE_BACK_LEFT, MOTOR_DRIVE_BACK_RIGHT);
		
		SOLENOID_DRIVE_FRONT_LEFT = new Solenoid(RobotMap.SOLENOID_DRIVE_FRONT_LEFT_PORT);
		SOLENOID_DRIVE_FRONT_RIGHT = new Solenoid(RobotMap.SOLENOID_DRIVE_FRONT_RIGHT_PORT);
		SOLENOID_DRIVE_BACK_LEFT = new Solenoid(RobotMap.SOLENOID_DRIVE_BACK_LEFT_PORT);
		SOLENOID_DRIVE_BACK_RIGHT = new Solenoid(RobotMap.SOLENOID_DRIVE_BACK_RIGHT_PORT);
		
		/*Encoder Initialzation*/
		encoderDriveLeft = new Encoder(RobotMap.ENCODER_DRIVE_LEFT_PORT_A, RobotMap.ENCODER_DRIVE_LEFT_PORT_B);
		encoderDriveRight = new Encoder(RobotMap.ENCODER_DRIVE_RIGHT_PORT_A, RobotMap.ENCODER_DRIVE_RIGHT_PORT_B);
		

		/*Gyro Initialzation*/
		gyro = new AnalogGyro(RobotMap.GYRO_MAIN_PORT);
		gyro.setSensitivity(RobotMap.GYRO_SENSITIVITY);

		/*LiveWindow Initialzation*/
		LiveWindow.addActuator("DriveTrain", "Front Left CIM", (Victor) MOTOR_DRIVE_FRONT_LEFT);
		LiveWindow.addActuator("DriveTrain", "Front Right CIM", (Victor) MOTOR_DRIVE_FRONT_RIGHT);
		LiveWindow.addActuator("DriveTrain", "Back Left CIM", (Victor) MOTOR_DRIVE_BACK_LEFT);
		LiveWindow.addActuator("DriveTrain", "Back Right CIM", (Victor) MOTOR_DRIVE_BACK_RIGHT);
		
		LiveWindow.addActuator("DriveTrain", "Front Left Piston", (Solenoid) SOLENOID_DRIVE_FRONT_LEFT);
		LiveWindow.addActuator("DriveTrain", "Front Right Piston", (Solenoid) SOLENOID_DRIVE_FRONT_RIGHT);
		LiveWindow.addActuator("DriveTrain", "Back Left Piston", (Solenoid) SOLENOID_DRIVE_BACK_LEFT);
		LiveWindow.addActuator("DriveTrain", "Back Right Piston", (Solenoid) SOLENOID_DRIVE_BACK_RIGHT);
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
    	drive.arcadeDrive(speed,turn);
    }
    
    public void setUpPosition(){
    	SOLENOID_DRIVE_FRONT_LEFT.set(true);
		SOLENOID_DRIVE_FRONT_RIGHT.set(true);
		SOLENOID_DRIVE_BACK_LEFT.set(true);
		SOLENOID_DRIVE_BACK_RIGHT.set(true);
    }
    
    public void setDownPosition(){
    	SOLENOID_DRIVE_FRONT_LEFT.set(false);
		SOLENOID_DRIVE_FRONT_RIGHT.set(false);
		SOLENOID_DRIVE_BACK_LEFT.set(false);
		SOLENOID_DRIVE_BACK_RIGHT.set(false);
    }
    
    public void setFrontUpPoisiton(){
    	SOLENOID_DRIVE_FRONT_LEFT.set(true);
		SOLENOID_DRIVE_FRONT_RIGHT.set(true);
		SOLENOID_DRIVE_BACK_LEFT.set(false);
		SOLENOID_DRIVE_BACK_RIGHT.set(false);
    }
    
    public void setBackUpPosition(){
    	SOLENOID_DRIVE_FRONT_LEFT.set(false);
		SOLENOID_DRIVE_FRONT_RIGHT.set(false);
		SOLENOID_DRIVE_BACK_LEFT.set(true);
		SOLENOID_DRIVE_BACK_RIGHT.set(true);
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


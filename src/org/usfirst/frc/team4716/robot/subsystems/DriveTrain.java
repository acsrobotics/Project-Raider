package org.usfirst.frc.team4716.robot.subsystems;

import java.util.Arrays;


import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.RobotMap;
import org.usfirst.frc.team4716.robot.commands.DriveTrain.JoystickDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
		ALL_IN(new DoubleSolenoid.Value[] {Value.kReverse, Value.kReverse}),
		ALL_OUT(new DoubleSolenoid.Value[] {Value.kForward, Value.kForward}),
		FRONT_OUT_BACK_IN(new DoubleSolenoid.Value[] {Value.kForward, Value.kReverse}),
		FRONT_IN_BACK_OUT(new DoubleSolenoid.Value[] {Value.kReverse, Value.kForward}),
		FUBAR(null); // Fucked-Up-Beyond-Recognition
		
		DoubleSolenoid.Value[] dsv = new DoubleSolenoid.Value[4];
		
		PositionStatusCode(DoubleSolenoid.Value[] dss) {
			dsv = dss;
		}
		
		public DoubleSolenoid.Value[] getDoubleSolenoidArray() {
			return dsv;
		}
	}
    
	SpeedController 		MOTOR_DRIVE_FRONT_LEFT,
   							MOTOR_DRIVE_FRONT_RIGHT,
   							MOTOR_DRIVE_BACK_LEFT,
   							MOTOR_DRIVE_BACK_RIGHT;

	DoubleSolenoid			VALVE_FRONT,
							VALVE_BACK;

	ADXRS450_Gyro 			gyro;
	
//	ADXRS450_Gyro gyroSPI;

	Encoder 				encoderDriveLeft,
							encoderDriveRight;

	Ultrasonic 				ultrasonicLeft,
							ultrasonicRight;
	
	public AnalogInput             ultraTreatAsRaw;
	
	RobotDrive 				drive;
	
	
	SpeedController motor;
	
	
	public DriveTrain(){
		MOTOR_DRIVE_FRONT_LEFT = new Victor(0);
		MOTOR_DRIVE_FRONT_RIGHT = new Victor(6);
		MOTOR_DRIVE_BACK_LEFT = new Victor(1);
		MOTOR_DRIVE_BACK_RIGHT = new Victor(7);
		drive = new RobotDrive(MOTOR_DRIVE_FRONT_LEFT, MOTOR_DRIVE_FRONT_RIGHT, MOTOR_DRIVE_BACK_LEFT, MOTOR_DRIVE_BACK_RIGHT);
		
		VALVE_FRONT = new DoubleSolenoid(0,6,7);
		VALVE_BACK = new DoubleSolenoid(0,4,5);
		//ultrasonicLeft = new Ultrasonic(0,1);
		
		ultraTreatAsRaw = new AnalogInput(1);
		
//		/*Gyro Initialzation*/
		gyro = new ADXRS450_Gyro();

		/*LiveWindow Initialzation*/
		LiveWindow.addActuator("DriveTrain", "Front Left CIM", (Victor) MOTOR_DRIVE_FRONT_LEFT);
		LiveWindow.addActuator("DriveTrain", "Front Right CIM", (Victor) MOTOR_DRIVE_FRONT_RIGHT);
		LiveWindow.addActuator("DriveTrain", "Back Left CIM", (Victor) MOTOR_DRIVE_BACK_LEFT);
		LiveWindow.addActuator("DriveTrain", "Back Right CIM", (Victor) MOTOR_DRIVE_BACK_RIGHT);
		
		LiveWindow.addActuator("DriveTrain", "Front Left Piston", (DoubleSolenoid) VALVE_FRONT);
		LiveWindow.addActuator("DriveTrain", "Front Right Piston", (DoubleSolenoid) VALVE_BACK);
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

        	try {
    			Thread.sleep(500);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        	this.VALVE_FRONT.set(DoubleSolenoid.Value.kForward);
        	this.VALVE_BACK.set(DoubleSolenoid.Value.kForward);
    	} else {
    		
        	this.setSolenoidPosition(code.getDoubleSolenoidArray()[0]
        							, code.getDoubleSolenoidArray()[1]);	
    	}
    }
    
    

    public double getUltrasonicDistance(){
    	//double val = (ultrasonicLeft.getRangeInches() + ultrasonicRight.getRangeInches())/2;
    	//System.out.println(val);
    	return 0.0;
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
    	//double val = ultrasonicLeft.getRangeInches();
    	double val = ultraTreatAsRaw.getVoltage();
//    	System.out.println("Voltage: " + val);
//    	System.out.println("Value " + ultraTreatAsRaw.getValue() + "\n");
    	return val;
    }
    
    public void setDriveModifier(double modifier){
    	RobotMap.DRIVE_MODIFIER = modifier;
    }
    
    
    // AUTONOMOUS METHODS
    
    /**
     * Drives straight with a given speed. Independent of time.
     * @param _speed
     */
    public void driveStraight(double _speed){
    	double rightpow, leftpow;
    	double angle = gyro.getAngle();
    	double kP = 0.05;
    	if(5 < angle){
    		rightpow =  _speed - kP * angle;
    		tankDrive(rightpow, _speed);
    	}else if(-5 > angle){
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
    		DoubleSolenoid.Value front,
    		DoubleSolenoid.Value back
    		){
    	
    	this.VALVE_FRONT.set(front);
    	this.VALVE_BACK.set(back);
    }
    
    public PositionStatusCode getPositionStatusCode(){
    	DoubleSolenoid.Value[] classSolenoids = new DoubleSolenoid.Value[] {
    			VALVE_FRONT.get(), 
    			VALVE_BACK.get()
    	};
    	
    	PositionStatusCode toReturn = PositionStatusCode.FUBAR;
    	
    	for (PositionStatusCode ds : PositionStatusCode.values()) {
    		if (Arrays.equals(ds.getDoubleSolenoidArray(), classSolenoids)) {
    			toReturn = ds;
    		}
    	}
    	
    	return toReturn;
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
    
    public void turnOnSpot(double _speed){
    	double rightpow, leftpow;
    	double angle = gyro.getAngle();
    	double kP = 0.05;
    	if(5 < angle){
    		rightpow =  _speed - kP * angle;
    		tankDrive(rightpow, 0);
    	}else if(-5 > angle){
    		leftpow =  _speed + kP * angle;
    		tankDrive(0, leftpow);
    	}else{
    		tankDrive(0,0);
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


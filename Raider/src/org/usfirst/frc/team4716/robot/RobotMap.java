package org.usfirst.frc.team4716.robot;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RobotMap {
	
	JSONParser parser = new JSONParser();
	static Object obj;
	static JSONObject jsonObject = (JSONObject)obj;
				
	
	RobotMap(){
		try {
			obj = parser.parse(new FileReader("meme.json"));
		} catch (Exception e){
			System.out.println("parsing error");
		}
	}

	public static  int 		MOTOR_DRIVE_FRONT_LEFT_PORT = (Integer) jsonObject.get("mFLDrive"),
   							MOTOR_DRIVE_FRONT_RIGHT_PORT = (Integer) jsonObject.get("mFRDrive"),
   							MOTOR_DRIVE_BACK_LEFT_PORT = (Integer) jsonObject.get("mBLDrive"),
   							MOTOR_DRIVE_BACK_RIGHT_PORT = (Integer) jsonObject.get("mBRDrive");
	
	public static final int	SOLENOID_DRIVE_FRONT_LEFT_PORT = 0,
							SOLENOID_DRIVE_FRONT_RIGHT_PORT = 1,
							SOLENOID_DRIVE_BACK_LEFT_PORT = 2,
							SOLENOID_DRIVE_BACK_RIGHT_PORT = 3;

	public static final int GYRO_MAIN_PORT = 0;					//CHANGE PORTS, THEY ARE INCORRECT
	public static final double GYRO_SENSITIVITY = 0.007;

	public static final int ENCODER_DRIVE_LEFT_PORT_A = 0,
							ENCODER_DRIVE_LEFT_PORT_B = 0,
							ENCODER_DRIVE_RIGHT_PORT_A = 0,
							ENCODER_DRIVE_RIGHT_PORT_B = 0;

	public static final int ULTRASONIC_LEFT_PORT = 0,
							ULTRASONIC_RIGHT_PORT = 0;
	
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

package org.usfirst.frc.team4716.robot;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RobotMap {
	
				
	public static  int 		MOTOR_DRIVE_FRONT_LEFT_PORT,
   							MOTOR_DRIVE_FRONT_RIGHT_PORT,
   							MOTOR_DRIVE_BACK_LEFT_PORT,
   							MOTOR_DRIVE_BACK_RIGHT_PORT;
	
	public static int	    SOLENOID_DRIVE_FRONT_LEFT_PORT = 0,
							SOLENOID_DRIVE_FRONT_RIGHT_PORT = 1,
							SOLENOID_DRIVE_BACK_LEFT_PORT = 2,
							SOLENOID_DRIVE_BACK_RIGHT_PORT = 3;

	public static int       GYRO_MAIN_PORT = 0;					//CHANGE PORTS, THEY ARE INCORRECT
	public static double    GYRO_SENSITIVITY = 0.007;

	public static int       ENCODER_DRIVE_LEFT_PORT_A = 0,
							ENCODER_DRIVE_LEFT_PORT_B = 0,
							ENCODER_DRIVE_RIGHT_PORT_A = 0,
							ENCODER_DRIVE_RIGHT_PORT_B = 0;

	public static int       ULTRASONIC_LEFT_PORT = 0,
							ULTRASONIC_RIGHT_PORT = 0;
	
	public static double DRIVE_MODIFIER = 1.0;
	
	public static int MOTOR_BUCKET_OPS_PORT,
					  MOTOR_BALL_INTAKE_PORT;
	
	public static int SWTICH_UP_PORT,
					  SWITCH_DOWN_PORT;
	
	public static int PISTON_EJECT_MODULE_NUMBER,
					  PISTON_EJECT_FORWARD_CHANNEL,
					  PISTON_EJECT_REVERSE_CHANNEL;
	
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

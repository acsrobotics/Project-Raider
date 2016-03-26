package org.usfirst.frc.team4716.robot;

public class RobotMap {
	
	
	//---------------DRIVETRAIN CONFIG------------------//

	
	public static int 		MOTOR_DRIVE_FRONT_LEFT_PORT = 0,
   							MOTOR_DRIVE_FRONT_RIGHT_PORT = 1,
   							MOTOR_DRIVE_BACK_LEFT_PORT = 6,
   							MOTOR_DRIVE_BACK_RIGHT_PORT = 7;
	
	public static int	    SOLENOID_DRIVE_FRONT_LEFT_PORT_A = 0,
							SOLENOID_DRIVE_FRONT_LEFT_PORT_B = 1,
							SOLENOID_DRIVE_FRONT_RIGHT_PORT_A = 2,
							SOLENOID_DRIVE_FRONT_RIGHT_PORT_B = 3,
							SOLENOID_DRIVE_BACK_LEFT_PORT_A = 4,
							SOLENOID_DRIVE_BACK_LEFT_PORT_B = 5,
							SOLENOID_DRIVE_BACK_RIGHT_PORT_A = 6,
							SOLENOID_DRIVE_BACK_RIGHT_PORT_B = 7,
							SOLENOID_A = 8,
							SOLENOID_B = 9;
	public static boolean   IS_UP;

	public static int       GYRO_MAIN_PORT = 0;					//CHANGE PORTS, THEY ARE INCORRECT
	public static double    GYRO_SENSITIVITY = 0.007;

	public static int       ENCODER_DRIVE_LEFT_PORT_A = 0,
							ENCODER_DRIVE_LEFT_PORT_B = 1,
							ENCODER_DRIVE_RIGHT_PORT_A = 2,
							ENCODER_DRIVE_RIGHT_PORT_B = 3;

	public static int       ULTRASONIC_LEFT_PORT = 4,
							ULTRASONIC_RIGHT_PORT = 5;
	
	public static double DRIVE_MODIFIER = 0.95;
	
	
	//---------------------BUCKET CONFIG--------------------//
	
	public static int MOTOR_BUCKET_OPS_PORT = 2,
					  MOTOR_BALL_INTAKE_PORT = 3;
	
	public static int SWTICH_UP_PORT = 0,
					  SWITCH_DOWN_PORT = 1;
	
	public static int PISTON_EJECT_MODULE_NUMBER = 1,
					  PISTON_EJECT_FORWARD_CHANNEL = 0,
					  PISTON_EJECT_REVERSE_CHANNEL = 1;
	
	public static int PISTON_ELEVATOR_MODULE_NUMBER = 1,
					  PISTON_ELEVATOR_FORWARD_CHANNEL = 2,
					  PISTON_ELEVATOR_REVERSE_CHANNEL = 3;
	
	public static double MOTOR_INTAKE_SPEED = -1.0;
	public static double MOTOR_OUTTAKE_SPEED = 1.0;
	
	public static double BUCKET_FOLD_SPEED = 0.6,
					     BUCKET_UNFOLD_SPEED = -0.6,
					     BUCKET_INTAKE_SPEED = 0.9;
	
	//---------------------CLIMBER CONFIG--------------------//
	
	public static int MOTOR_ANGLE_PORT = 4,
					  MOTOR_EXTENSION_PORT = 5;
	
	public static int LIMIT_SWITCH_EXTENSION= 2,
					  LIMIT_SWITCH_LIFT = 3;
	
	public static int ENCODER_MOTOR_ANGLE_A = 4,
					  ENCODER_MOTOR_ANGLE_B = 5,
					  ENCODER_MOTOR_EXTENSION_A = 6,
					  ENCODER_MOTOR_EXTENSION_B = 7;
	
	
	
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

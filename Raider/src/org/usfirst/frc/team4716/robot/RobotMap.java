package org.usfirst.frc.team4716.robot;

import java.io.FileReader;

public class RobotMap {
	
	

	public static  int 		MOTOR_DRIVE_FRONT_LEFT_PORT = 0,
   							MOTOR_DRIVE_FRONT_RIGHT_PORT = 1,
   							MOTOR_DRIVE_BACK_LEFT_PORT = 2,
   							MOTOR_DRIVE_BACK_RIGHT_PORT = 3;
	
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
	
	public static double DRIVE_MODIFIER = 1.0;
	
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

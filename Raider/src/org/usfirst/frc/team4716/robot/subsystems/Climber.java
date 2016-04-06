package org.usfirst.frc.team4716.robot.subsystems;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * In case everything doesn't work 
 */
public class Climber extends Subsystem {
    
	
	
	CANTalon MOTOR_EXTENSION;
							
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Climber() {
    	MOTOR_EXTENSION = new CANTalon(0);
    	
    }

    public CANTalon getExtensionMotor(){
    	return this.MOTOR_EXTENSION;
    }
    
}
package org.usfirst.frc.team4716.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
    
	SpeedController			EXTENSION_MOTOR,
							ANGLE_MOTOR;
	
	DigitalInput			limitSwitchStandardPosition,
							limitSwitch;
	
	Encoder					angleEncoder,
							extensionEncoder;
							
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Climber() {
    	EXTENSION_MOTOR = new Victor(0);
    	ANGLE_MOTOR = new Victor(1);
    	
    	angleEncoder = new Encoder(0, 0);
    	extensionEncoder = new Encoder(0, 0);
    	
    	limitSwitchStandardPosition = new DigitalInput(0);
    }
    
    public void setStandardPosition() {
    	if (limitSwitchStandardPosition.get() == true){
    		ANGLE_MOTOR.set(0);
    		angleEncoder.reset();
    	} else {
    		ANGLE_MOTOR.set(-.5);
    	}
    }
    
    public void extendArm(double extendSpeed) {
    	EXTENSION_MOTOR.set(extendSpeed);
    }
    
    public void changeArmAngle(double angleSpeed) {
    	ANGLE_MOTOR.set(angleSpeed);
    }
    
    public void climbTower() {
    	if (angleEncoder.get() < 100) {
    		ANGLE_MOTOR.set(.7);
    	} else {
    		ANGLE_MOTOR.set(0);
    	}
    	
    	if (extensionEncoder.get() < 100) {
    		EXTENSION_MOTOR.set(.75);
    	} else {
    		EXTENSION_MOTOR.set(0);
    	}
    }
    
    public double getExtensionEncoderDistance() {
    	return extensionEncoder.getDistance();
    }
    
    public double getAngleEncoderDistance() {
    	return angleEncoder.getDistance();
    }
    
}


package org.usfirst.frc.team4716.robot.commands.Climber;

import java.sql.Time;
import java.util.Calendar;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.subsystems.Climber.Climber;

import com.ni.vision.NIVision.CalibrationThumbnailType;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestLift extends Command {

	int limit;
	
	CANTalon climber;
	
    public TestLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    	climber = Robot.climber.getExtensionMotor();
    	//climber.reset();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	climber.enable();
    	//climber.enableControl();
//    	Robot.climber.getExtensionMotor().setEncPosition(0);
//    	Robot.climber.getExtensionMotor().setPosition(10000);
    	
//    	climber.reset();
    	climber.changeControlMode(CANTalon.TalonControlMode.Position);
    	climber.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	climber.setPID(1.0, 0.0, 0.0);
    	climber.setInverted(false);
    	climber.setPosition(0);
    	//climber.set(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	climber.set(500000);
    	System.out.println(Calendar.getInstance().getTimeInMillis());
    	System.out.println("From Lift: " + climber.getEncPosition());
    	System.out.println("Get velocity: " + climber.getEncVelocity() + "\n");
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	double err = Math.abs(Math.abs(climber.getEncPosition()) - 500000);
//    	System.out.println("Error: " + err);
//    	return err < 500;
        return Math.abs(Robot.climber.getExtensionMotor().getPosition()) > 500000 ? true:false;
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	climber.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	climber.set(0);
    	//climber.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}

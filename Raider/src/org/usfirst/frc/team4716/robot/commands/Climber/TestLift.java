package org.usfirst.frc.team4716.robot.commands.Climber;

import java.sql.Time;
import java.util.Calendar;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.subsystems.Climber.Climber_Regular;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestLift extends Command {

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
//    	Robot.climber.getExtensionMotor().setEncPosition(0);
//    	Robot.climber.getExtensionMotor().setPosition(10000);
    	
//    	climber.reset();
    	climber.changeControlMode(CANTalon.TalonControlMode.Position);
    	climber.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	climber.setPID(.5, 0.0, .33);
    	climber.setPosition(0);
    	//climber.set(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	climber.set(10000);
    	
    	System.out.println(Calendar.getInstance().getTimeInMillis());
    	System.out.println("From Lift: " + climber.getPosition());
    	System.out.println("Get velocity: " + climber.getAnalogInVelocity() + "\n");
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
        //return Robot.climber.getExtensionMotor().getPosition() > 20000 ? true:false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	climber.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	climber.disable();
    }
}

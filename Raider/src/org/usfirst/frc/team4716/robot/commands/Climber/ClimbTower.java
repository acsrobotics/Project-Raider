package org.usfirst.frc.team4716.robot.commands.Climber;

import org.usfirst.frc.team4716.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbTower extends Command {

    public ClimbTower() {
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.climbTower();
    	System.out.println("Climbing tower");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.climber.getExtensionEncoderDistance() < 1000 && Robot.climber.getAngleEncoderDistance() < 1000) {
        	return true;
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.changeArmAngle(0);
    	Robot.climber.extendArm(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.changeArmAngle(0);
    	Robot.climber.extendArm(0);
    }
}

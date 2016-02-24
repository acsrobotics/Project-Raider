package org.usfirst.frc.team4716.robot.commands.DriveTrain;

import org.usfirst.frc.team4716.robot.Robot;
import org.usfirst.frc.team4716.robot.subsystems.DriveTrain.PositionStatusCode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PositionTest extends Command {

	PositionStatusCode state;
    public PositionTest(PositionStatusCode state) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setPosition(this.state);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

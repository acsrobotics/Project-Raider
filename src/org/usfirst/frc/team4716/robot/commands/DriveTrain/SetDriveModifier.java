package org.usfirst.frc.team4716.robot.commands.DriveTrain;

import org.usfirst.frc.team4716.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetDriveModifier extends Command {

	double modifier; 
	
    public SetDriveModifier(double modifier) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.modifier = modifier;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setDriveModifier(this.modifier);
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


package org.usfirst.frc.team4716.robot;

import org.usfirst.frc.team4716.robot.commands.Auto.AutoDoNothing;
import org.usfirst.frc.team4716.robot.commands.Auto.AutoForwardReverseDefault;
import org.usfirst.frc.team4716.robot.commands.Auto.AutoMoveForwardHigh;
import org.usfirst.frc.team4716.robot.commands.Auto.AutoMoveForwardLow;
import org.usfirst.frc.team4716.robot.commands.Auto.AutoMoveForwardReverse;
import org.usfirst.frc.team4716.robot.commands.Auto.AutoMoveForwardReverseHigh;
import org.usfirst.frc.team4716.robot.commands.Auto.AutoMoveShoot;
import org.usfirst.frc.team4716.robot.commands.DriveTrain.DriveForwardTime;
//import org.usfirst.frc.team4716.robot.commands.Auto.DoNothing;
import org.usfirst.frc.team4716.robot.subsystems.Bucket;
import org.usfirst.frc.team4716.robot.subsystems.Bucket.Direction;
import org.usfirst.frc.team4716.robot.subsystems.Climber;
import org.usfirst.frc.team4716.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain drivetrain = new DriveTrain();
	public static final Bucket     bucket     = new Bucket();
	public static final Climber    climber    = new Climber();
	public static OI oi;
	
    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();

        chooser = new SendableChooser();
        chooser.addDefault("Nothing", new AutoDoNothing());
        chooser.addObject("Low Forward", new AutoMoveForwardLow());
        chooser.addObject("High Forward", new AutoMoveForwardHigh());
        chooser.addObject("Low Forward Reverse", new AutoMoveForwardReverse());
        chooser.addObject("High Forward Reverse", new AutoMoveForwardReverseHigh());
        chooser.addObject("Reverse Default", new DriveForwardTime(-0.8,3.5));
        chooser.addObject("Forward Default", new DriveForwardTime(0.8,3.5));
        chooser.addObject("Forward Reverse Default", new AutoForwardReverseDefault());
        chooser.addObject("Low goal shot", new AutoMoveShoot());
        
        
        SmartDashboard.putData("Auto mode", chooser);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	Robot.bucket.setEjectPosition(Direction.IN);
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
//    	Robot.client.update();
//    	RobotMap tempMap = new RobotMap();// just get a dummy object
//    	try {
//    		// update RobotMap with new data
//			TypeBridge.copy(tempMap, Robot.client.getDataObject(new RobotMapMirror().getClass()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    

}

package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftDrive extends Command {
	
    public LiftDrive() {
        requires(Robot.lift);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.getJoystick1POV() == 0.0) {
			Robot.lift.stop();
		} else if (Robot.oi.getJoystick1POV() == 1.0){
			Robot.lift.drive(1.0);
		} else if (Robot.oi.getJoystick1POV() == -1.0){
			Robot.lift.drive(-0.25);
		} else if (Robot.oi.getJoystick1POV() == 90){
			Robot.lift.drive(0.5);
		} else if (Robot.oi.getJoystick1POV() == -90){	
			Robot.lift.drive(-0.5);
		} else {
			Robot.lift.stop();
		}
    	
    	//Robot.lift.drive(Robot.oi.getJoystick3Y()/10);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

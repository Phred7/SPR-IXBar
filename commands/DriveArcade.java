package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveArcade extends Command {

    public DriveArcade() {
    	requires(Robot.driveWC);
	}

	protected void initialize() {
	}

	protected void execute() {
			Robot.driveWC.arcadeDrive(Robot.oi.getJoystick1X(), Robot.oi.getJoystick1Y());
		}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}
	
	protected void interrupted() {
	}
}

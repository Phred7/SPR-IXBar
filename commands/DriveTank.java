package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTank extends Command {

    public DriveTank() {
    	requires(Robot.driveWC);
	}

	protected void initialize() {
	}

	protected void execute() {
			Robot.driveWC.tankDrive(Robot.oi.getJoystick1Y1(), Robot.oi.getJoystick1Y2());
		}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}
	
	protected void interrupted() {
	}
}

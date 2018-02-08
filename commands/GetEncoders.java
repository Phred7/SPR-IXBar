package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GetEncoders extends Command {

	public GetEncoders() {

	}

	protected void initialize() {
	}

	protected void execute() {
		SmartDashboard.putNumber("encR", Robot.driveWC.getRHardEnc());
		SmartDashboard.putNumber("encL", Robot.driveWC.getLHardEnc());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
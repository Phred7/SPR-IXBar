package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AimVision extends Command {

	// P constant
	double kP = 0.1;
	// Minimum motor speed
	double minCommand = 0.1;
	// Angle from center on x-axis
	double xOffset;
	// Angle from center on x-axis (corrected sign)
	double headingError;
	// Varying motor speeds
	double leftSpeed;
	double rightSpeed;
	// Reports if the target is in view
	boolean isFinished = false;
	// Error margin from target
	double errorMargin = 0.2;

	public AimVision() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.limelight);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		requires(Robot.limelight);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// Get current offset on the x-axis
		xOffset = Robot.limelight.getXOffset();
		// Current heading error
		headingError = -xOffset;
				SmartDashboard.putNumber("AimVision().headingError", headingError);

		if (headingError > errorMargin) { // If the offset is greater than one, subtract the minimum
			leftSpeed = headingError * -kP - minCommand;
			rightSpeed = headingError * kP + minCommand;
		} else if (headingError < -errorMargin) { // If the offset is less than one, add the minimum
			leftSpeed = headingError * kP - minCommand;
			rightSpeed = headingError * -kP + minCommand;
		}else {
			isFinished = true;
		}
				SmartDashboard.putNumber("AimVision().leftSpeed", leftSpeed);
				SmartDashboard.putNumber("AimVision().rightSpeed", rightSpeed);

		// Set motors to motor speeds
		Robot.driveWC.drive(rightSpeed, leftSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println("AimVision(): Target aligned!");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
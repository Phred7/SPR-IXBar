package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SeekVision extends Command {

	// P constant
	double kP = -0.1;
	// Minimum motor speed
	double minCommand = 0.3;
	// Is a target in view
	boolean hasTarget;
	// Varying motor speeds
	double leftSpeed;
	double rightSpeed;
	// Reports if the target is in view
	boolean isFinished = false;

	public SeekVision() {
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
		// Get if the target is in view
		hasTarget = Robot.limelight.getHasTarget();

		if (hasTarget == false) { // If the target is not in view, set the motors to turn the robot
			leftSpeed = minCommand;
			rightSpeed = -minCommand;
		} else { // If the target is in view, finish the command
			isFinished = true;
		}

				SmartDashboard.putNumber("SeekVision().leftSpeed", leftSpeed);
				SmartDashboard.putNumber("SeekVision().rightSpeed", rightSpeed);
		
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
		System.out.println("SeekVision(): Target found!");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}

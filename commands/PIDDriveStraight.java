package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;
import org.usfirst.frc.team2906.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDDriveStraight extends Command {

	double motorSpeed = .15;
	double direction = 0;
	double currentAngle = 0;
	double distance = 0;
	double error = 0;
	double errorB = 0;
	double pAdjustment = 0;
	double iAdjustment = 0;
	double dAdjustment = 0;
	double lastError = 0;
	double PIDAdjustment = 0;

	public PIDDriveStraight(double motorSpeed, double direction, double distance) {
		this.motorSpeed = -1 * motorSpeed;
		this.direction = direction;
		this.distance = distance * RobotMap.encoderCountsLeftToIn;
		requires(Robot.driveWC);
	}

	protected void initialize() {
		Robot.driveWC.resetEncs();
		Robot.navX.resetNavX();
		dAdjustment = 0;
		if (motorSpeed > 0) {
			// iAdjustment = 0.25;
			// practiceBotForward();
		} else {
			// practiceBotBack();
		}
		iAdjustment = 0;
		pAdjustment = 0;
		error = 0;
		lastError = 0;
		PIDAdjustment = 0;
		errorB = 0;
	}

	protected void execute() {
		currentAngle = Robot.navX.getNavXAngle();
		error = direction - currentAngle;
		pAdjustment = (direction - currentAngle) * RobotMap.PIDDriveStraightP * RobotMap.PIDDriveStraightGainMultiplier;
		iAdjustment = iAdjustment + (error * RobotMap.PIDDriveStraightI * RobotMap.PIDDriveStraightGainMultiplier);
		dAdjustment = (error - lastError) * RobotMap.PIDDriveStraightD * RobotMap.PIDDriveStraightGainMultiplier;
		lastError = error;
		PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;
		SmartDashboard.putNumber("d-error", error);
		SmartDashboard.putNumber("distance error", lastError);
		SmartDashboard.putNumber("travel distance", this.distance);
		SmartDashboard.putNumber("Integral", iAdjustment);
		Robot.driveWC.driveR(-(motorSpeed) - PIDAdjustment);
		Robot.driveWC.driveL(motorSpeed - PIDAdjustment);
		SmartDashboard.putNumber("IAdjustment", iAdjustment);
		SmartDashboard.putNumber("PID Adjustment", PIDAdjustment);
		SmartDashboard.putNumber("errorB", errorB);
		SmartDashboard.putBoolean("Collission", Robot.navX.collisionDetection());
		SmartDashboard.putNumber("x velocity", Robot.navX.getXVelocity());
		SmartDashboard.putNumber("y velocity", Robot.navX.getYVelocity());
		SmartDashboard.putNumber("z velocity", Robot.navX.getZVelocity());

	}

	protected boolean isFinished() {
		return (Math.abs(Robot.driveWC.getLSoftEnc()) > ((Math.abs(distance /*- .5*/)))); // &&
																			// +
																							// .5)))));
	}

	protected void end() {
		SmartDashboard.putNumber("i", iAdjustment);
		Robot.driveWC.stop();
	}

	protected void interrupted() {
		end();
	}
}

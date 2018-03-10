package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;
import org.usfirst.frc.team2906.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IXBarPIDJoyDrive extends Command {

	double motorSpeed = 1.0;
	double error = 0;
	double errorB = 0;
	double pAdjustment = 0;
	double iAdjustment = 0;
	double dAdjustment = 0;
	double lastError = 0;
	double PIDAdjustment = 0;
	double TD = 0;
	double LD = 0;
	double GR = 0;
	double TargetTicks = 0;
	double ticksperrev = 12;
	
	double kF = RobotMap.kFIX;
	double kP = RobotMap.kPIX;
	double kI = RobotMap.kIIX;
	double kD = RobotMap.kDIX;
	
    public IXBarPIDJoyDrive() {
    	this.GR = RobotMap.GRIX;
    	requires(Robot.ixBar);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	TD = (Robot.oi.getJoystick2Y()*120);
    	this.TargetTicks = (TD*((ticksperrev*GR)/(360)));
    	LD = Robot.ixBar.getCurrentPosition();
    	error = TargetTicks - LD;
    	pAdjustment = (TargetTicks - LD) * kP * RobotMap.PIDDriveStraightGainMultiplier;
		iAdjustment = iAdjustment + (error * kI * RobotMap.PIDDriveStraightGainMultiplier);
		dAdjustment = (error - lastError) * kD * RobotMap.PIDDriveStraightGainMultiplier;
		lastError = error;
		PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;
		SmartDashboard.putNumber("travel degrees IX", TargetTicks);
		SmartDashboard.putNumber("Current Position", LD);
		Robot.ixBar.drive(motorSpeed - PIDAdjustment);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((LD > (TargetTicks + 20) && (LD < (TargetTicks - 20))));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ixBar.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
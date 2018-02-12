package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;
import org.usfirst.frc.team2906.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDLimelightDriveStraight extends Command {

	double motorSpeed = .5;
	double direction = 0;
	double currentAngle = 0;
	double distance = 0;
	double error = 0;
	double pAdjustment = 0;
	double iAdjustment = 0;
	double dAdjustment = 0;
	double lastError = 0;
	double PIDAdjustment = 0;
	    
	 public PIDLimelightDriveStraight() {
	    	requires(Robot.driveWC);
	    }
	    
		protected void initialize() {
	    	Robot.driveWC.resetEncs();
	    	//Robot.navX.resetNavX();
	    	dAdjustment = 0;
	    	if(motorSpeed > 0){
	    		//iAdjustment = 0.25;
	    	//	practiceBotForward();
	    	} else {
	    	//	practiceBotBack();
	    	}
	    	iAdjustment = 0;
	    	pAdjustment = 0;
	    	error = 0;
	    	lastError = 0;
	    	PIDAdjustment = 0;
	    	SmartDashboard.putNumber("distaneeece", RobotMap.distanceToTarget);
	    	distance = RobotMap.encoderCountsLeftToIn * (RobotMap.distanceToTarget-15);
	    	direction = Robot.navX.getNavXAngle();
	    }
	
    
	
	protected void execute() {
    	currentAngle = Robot.navX.getNavXAngle();
    	SmartDashboard.putNumber("error left", Math.abs(Robot.driveWC.getRSoftEnc()) - Math.abs(distance));
    	error = direction - currentAngle;
    	System.out.println(distance + "distance");
    	pAdjustment = (direction - currentAngle) * RobotMap.PIDDriveStraightP * RobotMap.PIDDriveStraightGainMultiplier;
    	iAdjustment = iAdjustment + (error * RobotMap.PIDDriveStraightI * RobotMap.PIDDriveStraightGainMultiplier);
    	dAdjustment = (error - lastError) * RobotMap.PIDDriveStraightD * RobotMap.PIDDriveStraightGainMultiplier;
    	lastError = error;
    	PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;
    	SmartDashboard.putNumber("Integral", iAdjustment);
    	Robot.driveWC.driveR(motorSpeed + PIDAdjustment);
    	Robot.driveWC.driveL(motorSpeed - PIDAdjustment);
    	SmartDashboard.putNumber("IAdjustment", iAdjustment);
    }

	protected boolean isFinished() {
        return Math.abs(Robot.driveWC.getLSoftEnc()) > Math.abs(distance);
    }

	protected void end() {
    	SmartDashboard.putNumber("i", iAdjustment);
    	Robot.driveWC.stop();
    }

	protected void interrupted() {
    	end();
    }
}

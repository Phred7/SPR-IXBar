package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;
import org.usfirst.frc.team2906.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class IXBarPIDDrive extends Command {

	double LD = 0;
	double GR = 0;
	double Target = 0;
    public IXBarPIDDrive(double locationDegs) {
    	requires(Robot.ixBar);
    	LD = locationDegs;
    	GR = RobotMap.GRIX;
    	Target = 0;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//SmartDashboard.putNumber("IXAngle: ", Robot.ixBar.getCurrentPosition());
    	//SmartDashboard.putNumber("IXError: ", Robot.ixBar.getCurrentError());
    	//SmartDashboard.putNumber("IXTarget: ", Robot.ixBar.getTargetDegrees());
    	Target = (LD * ((4096*GR)/360)/1000);
    	//Target = (Robot.oi.getJoystick2Y()) * 4096;
    	Robot.ixBar.drivePID(Target);
    	
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

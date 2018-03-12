package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LimeSwitchToCV extends Command {

	 boolean isFinished = false;
    public LimeSwitchToCV() {
        requires(Robot.limelight);
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 requires(Robot.limelight);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.limelight.switchLED();
    	isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

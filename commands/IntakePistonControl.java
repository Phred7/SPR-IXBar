package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakePistonControl extends Command {
	
	double location = 0.0;

    public IntakePistonControl() {
        requires(Robot.ixBar);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	location = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.ixBar.getVoltage() > 0.0 && Robot.ixBar.getPistonControl() == 1.0) {
    		location = 1.0;
    	} else if(Robot.ixBar.getVoltage() <= 0.0 && Robot.ixBar.getPistonControl() == 1.0) {
    		location = 0.0;
    	} else if (location == 1.0) {
    		Robot.pneumatics.Retract();
    	}
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

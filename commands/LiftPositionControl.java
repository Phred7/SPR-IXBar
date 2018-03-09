package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftPositionControl extends Command {

	int Cpos;
	
    public LiftPositionControl() {
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Cpos = Robot.lift.getCurrentPosition();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.PositionControl(Cpos);
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

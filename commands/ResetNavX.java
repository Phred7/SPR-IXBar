package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetNavX extends Command {

	 public ResetNavX() {
	    }

	    protected void initialize() {
	    }

	    protected void execute() {
	    	Robot.navX.resetNavX();
	    }

	    protected boolean isFinished() {
	        return false;
	    }

	    protected void end() {
	    }

	    protected void interrupted() {
	    }
	}

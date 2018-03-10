package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LimeTest extends Command {

    public LimeTest() {
        requires(Robot.limelight);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.limelight.getXOffset()>5) {
    		Robot.driveWC.drive(0.0, 0.2);
    	} else if(Robot.limelight.getXOffset()<-5) {
    		Robot.driveWC.drive(.2, 0.0);
    	} else if(Robot.limelight.getXOffset() > -5 && Robot.limelight.getXOffset() < 5) {
    		Robot.driveWC.stop();
    	} else
    		Robot.driveWC.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.limelight.getXOffset() > -5 && Robot.limelight.getXOffset() < 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveWC.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LimeTestStraight extends Command {

    public LimeTestStraight() {
    	requires(Robot.limelight);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.limelight.getArea()<80) {
    		Robot.driveWC.drive(0.3, 0.3);
    	} else if(Robot.limelight.getArea()<90) {
    		Robot.driveWC.drive(-.15, -.15);
    	} else
    		Robot.driveWC.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.limelight.getArea() > 80 && Robot.limelight.getArea() < 90;
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

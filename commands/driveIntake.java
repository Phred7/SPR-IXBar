package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class driveIntake extends Command {
	
	double m_speed;
	
    public driveIntake(double speed) {
    	this.m_speed = 1 * speed;
        requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(Robot.intake.GetSense() == false) {
    		Robot.intake.intake(this.m_speed);
    	} else if(this.m_speed > 0 && Robot.intake.GetSense() == true) {
    		Robot.intake.Stop();
    	} else if(this.m_speed < 0 && Robot.intake.GetSense() == true) {
    		Robot.intake.intake(this.m_speed);
    	} else {
    		Robot.intake.Stop();
    	}
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

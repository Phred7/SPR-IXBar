package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RelayLEDsOff extends Command {

    public RelayLEDsOff() {
    	requires(Robot.limelight);
    }
    
    protected void initialize() {
    }
    
    protected void execute() {
    	Robot.limelight.Loff();
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }

    protected void interrupted() {
    }
}
package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RelayLEDsOn extends Command {

    public RelayLEDsOn() {
        requires(Robot.limelight);
    }
    
    protected void initialize() {
    }
    
    protected void execute() {
    	Robot.limelight.Lon();
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }

    protected void interrupted() {
    }
}

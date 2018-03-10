package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class RSet extends Command {

    public RSet() {
        requires(Robot.pneumatics);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.pneumatics.Retract();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

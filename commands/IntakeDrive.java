package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class IntakeDrive extends Command {
	
    public IntakeDrive() {
        requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (Robot.oi.getJoystick1POV() == 0.0) {
			Robot.intake.Stop();
		} else if (Robot.oi.getJoystick1POV() == 1.0){
			Robot.intake.intake(1.0);
		} else if (Robot.oi.getJoystick1POV() == -1.0){
			Robot.intake.intake(-1.0);
		} else if (Robot.oi.getJoystick1POV() == 10){
			Robot.intake.intake(0.5);
		} else if (Robot.oi.getJoystick1POV() == -10){	
			Robot.intake.intake(-0.5);
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

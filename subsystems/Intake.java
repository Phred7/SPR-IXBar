package org.usfirst.frc.team2906.robot.subsystems;

import org.usfirst.frc.team2906.robot.Robot;
import org.usfirst.frc.team2906.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	Spark motor = RobotMap.Intake;
	DigitalInput sense = RobotMap.CubeSense;
	
	public void Intake() {
		
	}
	
	public void intake(Double speed) {
		motor.set(speed);
	}
	
	public void InFull() {
		motor.set(1.0);
	}
	
	public void OutFull() {
		motor.set(-1.0);
	}
	
	public void In50() {
		motor.set(0.5);
	}
	
	public void Out50() {
		motor.set(-0.5);
	}
	
	public void Stop() {
		motor.set(0.0);
	}
	
	public boolean GetSense() {
		return sense.get();
	}

    public void initDefaultCommand() {
    	Robot.intake.Stop();
    }
}


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

	Spark motorR = RobotMap.IntakeR;
	Spark motorL = RobotMap.IntakeL;
	
	public void Intake() {
		
	}
	
	public void intake(Double speed) {
		motorR.set(speed);
		motorL.set(-speed);
	}
	
	public void InFull() {
		motorR.set(-1.0);
		motorL.set(1.0);
	}
	
	public void OutFull() {
		motorR.set(1.0);
		motorL.set(-1.0);
	}
	
	public void In50() {
		motorR.set(-0.5);
		motorL.set(0.5);
	}
	
	public void Out50() {
		motorR.set(0.5);
		motorL.set(-0.5);
	}
	
	public void Stop() {
		motorR.set(0.0);
		motorL.set(0.0);
	}
	
	public double GetSpeed() {
		return motorR.get();
	}

    public void initDefaultCommand() {
    	
    }
}


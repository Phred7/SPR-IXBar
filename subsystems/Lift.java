package org.usfirst.frc.team2906.robot.subsystems;

import org.usfirst.frc.team2906.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

    WPI_TalonSRX motor = RobotMap.Lift;
    
    public Lift() {
    	
    }

	public double getVoltage() {
		return 0.0;//motor.getMotorOutputVoltage();
	}	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new );
    }
}


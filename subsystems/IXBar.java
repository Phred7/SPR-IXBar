package org.usfirst.frc.team2906.robot.subsystems;

import org.usfirst.frc.team2906.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IXBar extends Subsystem {

	WPI_TalonSRX IXBar = RobotMap.IXBar;
	WPI_TalonSRX slave = RobotMap.IXBarSlave;
	DigitalInput mag = RobotMap.LimIX;
	
	public static final int TOLERANCE = 0;
	public static final double F = 0.0, P = 0.1, I = 0.0, D = 0.0; 
	
	public void IXBar() {
		this.IXBar.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	}
	
	public void drive(double speed) {
		IXBar.set(speed);
	}
	
	public void stop() {
		IXBar.set(0.0);
	}
	public double getVoltage() {
		return 0.0;//IXBar.getMotorOutputVoltage();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


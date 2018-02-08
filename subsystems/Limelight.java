package org.usfirst.frc.team2906.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2906.robot.RobotMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Limelight extends Subsystem {
	
	NetworkTable table;
	Relay visionLEDs = RobotMap.leds;

	// Create variables
	double targetD;
	boolean hasTarget;
	double xOffset;
	double yOffset;
	double area;
	double skew;
	double LEDMode;
	double camMode;
	double pipeline;

	public Limelight() {
		table = NetworkTableInstance.getDefault().getTable("limelight");
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new AimVision());
	}

	public boolean getHasTarget() {
		targetD = table.getEntry("tv").getDouble(0);
		if (targetD == 0) {
			hasTarget = false;
		} else if (targetD == 1) {
			hasTarget = true;
		}
		return hasTarget;
	}

	public double getXOffset() {
		xOffset = table.getEntry("tx").getDouble(0);
		return xOffset;
	}

	public double getYOffset() {
		yOffset = table.getEntry("ty").getDouble(0);
		return yOffset;
	}

	public double getArea() {
		area = table.getEntry("ta").getDouble(0);
		return area;
	}

	public double getSkew() {
		skew = table.getEntry("ts").getDouble(0);
		return skew;
	}

	public double getLEDMode() {
		LEDMode = table.getEntry("ledMode").getDouble(1);
		return LEDMode;
	}

	public double getCamMode() {
		camMode = table.getEntry("camMode").getDouble(0);
		return camMode;
	}

	public double getPipeline() {
		pipeline = table.getEntry("pipeline").getDouble(0);
		return pipeline;
	}
	
	public void Lon() {
		visionLEDs.set(Relay.Value.kForward);
	}

	public void Loff() {
		visionLEDs.set(Relay.Value.kOff);
	}
	
	public void switchLED() {
		if (getLEDMode() == 0) {
			table.getEntry("ledMode").setDouble(1);
			SmartDashboard.putString("LED Mode", "Off");
		} else if (getLEDMode() == 1) {
			table.getEntry("ledMode").setDouble(0);
			SmartDashboard.putString("LED Mode", "On");
		} else if (getLEDMode() == 2) {
			table.getEntry("ledMode").setDouble(1);
			SmartDashboard.putString("LED Mode", "Off");
		}
	}

	public void switchCamera() {
		if (getCamMode() == 0) {
			table.getEntry("camMode").setDouble(1);
			SmartDashboard.putString("Camera Mode", "Camera");
		} else if (getCamMode() == 1) {
			table.getEntry("camMode").setDouble(0);
			SmartDashboard.putString("Camera Mode", "Vision");
		}
	}

	public void setPipeline(double pipeline) {
		table.getEntry("pipeline").setDouble(pipeline);
		SmartDashboard.putNumber("Camera Mode", pipeline);
	}
}

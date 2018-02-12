package org.usfirst.frc.team2906.robot.subsystems;

import org.usfirst.frc.team2906.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

	WPI_TalonSRX motor = RobotMap.Lift;
	WPI_TalonSRX slave = RobotMap.LiftSlave;

	public Lift() {
		// set the feedback device to a quad encoder
		/*
		 * motor.setFeedbackDevice(FeedbackDevice.QuadEncoder); //configure the codes
		 * per revolution motor.configEncoderCodesPerRev(360); //not using the limit
		 * switches since I'm running the code on half of our drive base
		 * //motor.enableLimitSwitch(false, true); //setting PID values, this does
		 * update when I look on the roboRIO web interface motor.setPID(P, I, D); //set
		 * the controlMode motor.changeControlMode(TalonControlMode.PercentVbus);
		 * 
		 * //set up the second drive motor to follow the other one
		 * //motorFollow.changeControlMode(TalonControlMode.Follower);
		 * //motorFollow.set(LiftSlave);
		 */
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// sets the position of the lifter using the CANTalon's set() function,
	// hopefully this does PID
	public void drive(double out) {
		motor.set(out);
	}
	
	public void stop() {
		motor.set(0.0);
	}

	public double getVoltage() {
		return 0.0;// motor.getMotorOutputVoltage();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new );
	}
}

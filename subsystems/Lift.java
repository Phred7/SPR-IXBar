package org.usfirst.frc.team2906.robot.subsystems;

import org.usfirst.frc.team2906.robot.Robot;
import org.usfirst.frc.team2906.robot.RobotMap;
import org.usfirst.frc.team2906.robot.commands.LiftDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

	WPI_TalonSRX motor = RobotMap.Lift;
	WPI_TalonSRX slave = RobotMap.LiftSlave;
	
	double kP = RobotMap.kPL;
	double kI = RobotMap.kIL;
	double kD = RobotMap.kDL;
	double kF = RobotMap.kFL;
	double GR = RobotMap.GRL;
	
	double iaccum = 0.0;
	
	int sensorPos = 0;
	int kTimeout = RobotMap.kTimeoutMsL;
	int kPIDLoop = RobotMap.kPIDLoopIdxL;
	
	boolean Phase = true;
	boolean Invert = false;

	public Lift() {

		motor.setInverted(Invert);
		motor.setSensorPhase(Phase);
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		motor.configNominalOutputForward(0, kTimeout);
		motor.configNominalOutputReverse(0, kTimeout);
		motor.configPeakOutputForward(1, kTimeout);
		motor.configPeakOutputReverse(-.25, kTimeout);

		//motor.configAllowableClosedloopError(0, kPIDLoop, kTimeout);

		motor.config_kP(kPIDLoop, kP, kTimeout);
		motor.config_kI(kPIDLoop, kI, kTimeout);
		motor.config_kD(kPIDLoop, kD, kTimeout);
		motor.config_kF(kPIDLoop, kF, kTimeout);
		
	}
	
	public void PositionControl(double target) {
		motor.set(ControlMode.Position, target);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// sets the position of the lifter using the CANTalon's set() function,
	// hopefully this does PID
	public void drive(double out) {
		motor.disable();
		motor.set(out);
	}
	
	public void stop() {
		motor.set(0.0);
	}

	public double getVoltagePerc() {
		return 0.0;// motor.getMotorOutputVoltage();
	}
	
	public void drivePID(double targetPosition) {
		motor.set(ControlMode.Position, targetPosition);
	}

	public double getVoltage() {
		return 0.0;//IXBar.getMotorOutputVoltage();
	}
	
	public int getCurrentPosition() {
		return motor.getSelectedSensorPosition(kPIDLoop);
	}
	
	public double getCurrentError() {
		return 0.0;
	}
	
	public double getTargetDegreees() {
		return 0.0;
	}
	
	public void clearI() {
		motor.setIntegralAccumulator(iaccum, 0, 10);
	}
	
	public void clearE() {
		motor.setSelectedSensorPosition(sensorPos, 0, 10);
	}


	public void initDefaultCommand() {
		//setDefaultCommand();
	}
}

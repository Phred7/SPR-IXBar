package org.usfirst.frc.team2906.robot.subsystems;

import org.usfirst.frc.team2906.robot.Robot;
import org.usfirst.frc.team2906.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IXBar extends Subsystem {

	WPI_TalonSRX motor = RobotMap.IXBar;
	WPI_TalonSRX slave = RobotMap.IXBarSlave;
	DigitalInput LMT = RobotMap.LimIXTop;
	DigitalInput LMB = RobotMap.LimIXBottom;
	
	int Slot = RobotMap.kSlotIdxIX;
	int LoopID = RobotMap.kPIDLoopIdxIX;
	int Timeout = RobotMap.kTimeoutMsIX;
	int sensorPos = 0;
	
	boolean Phase = RobotMap.kSensorPhaseIX;
	boolean Invert = RobotMap.kMotorInvertIX;
	
	double kF = RobotMap.kFIX;
	double kP = RobotMap.kPIX;
	double kI = RobotMap.kIIX;
	double kD = RobotMap.kDIX;
	double GR = RobotMap.GRIX;
	double iaccum = 0;

	String magT = "MagT";
	
	public static final int TOLERANCE = 0;
	public static final double F = 0.0, P = 0.1, I = 0.0, D = 0.0; 
	
	public void IXBar(int locationDegs) {
		//motor.configForwardLimitSwitchSource(LimitSwitchSource.valueOf(magT), LimitSwitchNormal.NormallyOpen, );
		this.motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		this.motor.setSensorPhase(false);
		
		/* get the decoded pulse width encoder position, 4096 units per rotation */
		int pulseWidthPos = motor.getSensorCollection().getPulseWidthPosition();
		/* get the pulse width in us, rise-to-fall in microseconds */
		int pulseWidthUs = motor.getSensorCollection().getPulseWidthRiseToFallUs();
		/* get the period in us, rise-to-rise in microseconds */
		int periodUs = motor.getSensorCollection().getPulseWidthRiseToRiseUs();
		/* get measured velocity in units per 100ms, 4096 units is one rotation */
		int pulseWidthVel = motor.getSensorCollection().getPulseWidthVelocity();
		/* is sensor plugged in to Talon */
		boolean sensorPluggedIn = false;
		if (periodUs != 0) {
		sensorPluggedIn = true;
		}
		
		/* +14 rotations forward when using CTRE Mag encoder */
		motor.configForwardSoftLimitThreshold(+1*4096, 10);
		/* -15 rotations reverse when using CTRE Mag encoder */
		motor.configReverseSoftLimitThreshold(-1*4096, 10);
		motor.configForwardSoftLimitEnable(true, 10);
		motor.configReverseSoftLimitEnable(true, 10);
		/* pass false to FORCE OFF the feature. Otherwise the enable flags above are honored */
		motor.overrideLimitSwitchesEnable(true);
		
		motor.selectProfileSlot(Slot, 0);
		motor.config_kF(Slot, kF, Timeout);
		motor.config_kP(Slot, kP, Timeout);
		motor.config_kI(Slot, kI, Timeout);
		motor.config_kD(Slot, kD, Timeout);
		motor.config_IntegralZone(0, 100, Timeout);
		
	}
	
	public void drivePID(double targetPosition) {
		motor.set(ControlMode.Position, targetPosition);
	}
	public void drive(double speed) {
		motor.set(speed);
	}
	
	public void stop() {
		motor.set(0.0);
	}
	public double getVoltage() {
		return 0.0;//IXBar.getMotorOutputVoltage();
	}
	
	public double getCurrentPosition() {
		return 0.0;
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
        Robot.ixBar.IXBar(0);
    }
}


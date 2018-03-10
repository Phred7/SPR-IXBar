package org.usfirst.frc.team2906.robot.subsystems;

import org.usfirst.frc.team2906.robot.Robot;
import org.usfirst.frc.team2906.robot.RobotMap;
import org.usfirst.frc.team2906.robot.commands.DriveArcade;
import org.usfirst.frc.team2906.robot.commands.DriveTank;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveWC extends Subsystem {

	private final DifferentialDrive driveWC = RobotMap.driveWC;
	
	SpeedControllerGroup l = RobotMap.m_left;
	SpeedControllerGroup r = RobotMap.m_right;
	
	Encoder ELeft = RobotMap.DTELeft;
	Encoder ERight = RobotMap.DTERight;
	
	public DriveWC() {
		ELeft.reset();
		ERight.reset();
	}

	public void resetRHardEnc() {
		ERight.reset();
	}

	public void resetLHardEnc() {
		ELeft.reset();
	}

	public void resetEncs() {
		ELeft.reset();
		ERight.reset();
	}

	public int getRSoftEnc() {
		return ERight.get() - RobotMap.dTERightReset;
	}

	public int getLSoftEnc() {
		return ELeft.get() - RobotMap.dTELeftReset;
	}

	public int getRHardEnc() {
		return ERight.get();
	}

	public int getLHardEnc() {
		return ELeft.get();
	}

	public void softResetR() {
		RobotMap.dTERightReset = getRHardEnc();

	}

	public void softResetL() {
		RobotMap.dTELeftReset = getLHardEnc();
	}

	
	public void drive(double RMotor, double LMotor) {
		r.set(RMotor);
		l.set(LMotor);
	}
	public void driveR(double RMotor) {
		r.set(RMotor);
	}

	public void driveL(double LMotor) {
		l.set(LMotor);
	}

	public void stopR() {
		r.set(0);
	}

	public void stopL() {
		l.set(0);
	}

	public void stop() {
		l.set(0);
		r.set(0);
	}
	
	public void tankDrive(double left, double right){
		Robot.driveWC.driveL(left);
		Robot.driveWC.driveR(right);
		//r.set(right);
	}
	
	public void ArcadeSwingLeft(double swing){
		Robot.driveWC.driveL(swing);
		Robot.driveWC.stopR();
	}
	
	public void ArcadeSwingRight(double swing){
		Robot.driveWC.driveR(swing);
		Robot.driveWC.stopL();
	}
	
	public void mirrorDrive(double speedMultiplier){
		Robot.driveWC.driveL(speedMultiplier);
		Robot.driveWC.driveR(speedMultiplier);
	}

	
	public void arcadeDrive(double xSpeed, double zRotate) {
		driveWC.arcadeDrive(xSpeed, -zRotate);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveTank());
    }
}


/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2906.robot;

import org.usfirst.frc.team2906.robot.subsystems.IXBar;
import org.usfirst.frc.team2906.robot.subsystems.Intake;
import org.usfirst.frc.team2906.robot.subsystems.Lift;

import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {

	public static DifferentialDrive driveWC;
	public static Compressor pnueCompressor;
	public static Intake intake;
	public static IXBar ixBar;
	public static Lift lift;
	public static Spark DriveLI;
	public static Spark DriveLII;
	public static Spark DriveRI;
	public static Spark DriveRII;
	public static Spark Intake;
	public static WPI_TalonSRX IXBar;
	public static WPI_TalonSRX Lift;
	public static SpeedControllerGroup m_left;
	public static SpeedControllerGroup m_right;
	public static Relay leds;
	public static Encoder DTELeft;
	public static Encoder DTERight;
	public static DoubleSolenoid Extension;
	public static Solenoid Spring;
	public static AHRS navX;
	public static CameraServer cam1;
	public static DigitalInput LimIX; //Attach magnets to gear/sprocket with epoxy?
	public static DigitalInput CubeSense;
	
	public static int dTELeftReset = 0;
	public static int dTERightReset = 0;
	
	public static final double sensitivity = 0.075;
	
	public static final double PIDNavxTurnGainMultiplier = 0.1;
	public static final double PIDNavxTurnP = 0.5;
	public static final double PIDNavxTurnI = 0.03;
	public static final double PIDNavxTurnD = 0.5;
	
	public static final double PIDDriveStraightGainMultiplier = 0.03; //.03
	public static final double PIDDriveStraightP = 0.45; //.45 //1.0 (12/23)
	public static final double PIDDriveStraightI = 0.015; //.015 //1.0 (12/23)
	public static final double PIDDriveStraightD = 0.011; //.011 //3.0 (12/23) //8.0(12/23-2)
	
	
	public static final double encoderCountsLeftToIn = 27.851497;//27.675; //29.07(12/23) //27.851497(12-24)
	public static final double encoderCountsRightToIn = 27.851497; //NEED TO CHANGE BEFORE FINAL! New gbs
	
	public static void init(){
		DriveLI = new Spark(1);
		DriveLII = new Spark(2);
		DriveRI = new Spark(3);
		DriveRII = new Spark(4);
		IXBar = new WPI_TalonSRX(0);
		Lift = new WPI_TalonSRX(1);
		
		m_left = new SpeedControllerGroup(DriveLI, DriveLII);
		m_right = new SpeedControllerGroup(DriveRI, DriveRII);
		
		driveWC = new DifferentialDrive(m_left, m_right);
		
		leds = new Relay(0);
		
		navX = new AHRS(SPI.Port.kMXP);
		
		DTELeft = new Encoder(0, 1);
		DTERight = new Encoder(2, 3);
		
		LimIX = new DigitalInput(9);
		
		pnueCompressor = new Compressor(0);
		
		Extension = new DoubleSolenoid(0, 0, 1);
		Spring = new Solenoid(0, 2);
		
		/*CameraServer server1 = CameraServer.getInstance();
		server1.startAutomaticCapture();*/
	}
}

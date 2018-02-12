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
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
	public static WPI_TalonSRX IXBarSlave;
	public static WPI_TalonSRX Lift;
	public static WPI_TalonSRX LiftSlave;
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
	
	public static boolean cubeTargetTracked = false;
	public static double azimuthToTarget = 0;
	public static double distanceToTarget = 0;
	public static double cubeCameraOffSetInDegrees = 0;
	public static final double PIDCameraAimP = 0.5;
    public static final double PIDCameraAimI = 0.03;
    public static final double PIDCameraAimD = 0.5;
    public static final double PIDCameraAimGainMultiplier = 0.15;
	
	public static final double PIDNavxTurnGainMultiplier = 0.1;
	public static final double PIDNavxTurnP = 0.5;
	public static final double PIDNavxTurnI = 0.03;
	public static final double PIDNavxTurnD = 0.5;
	
	public static final double PIDDriveStraightGainMultiplier = 0.03; //.03
	public static final double PIDDriveStraightP = 0.45; //.45 //1.0 (12/23)
	public static final double PIDDriveStraightI = 0.015; //.015 //1.0 (12/23)
	public static final double PIDDriveStraightD = 0.011; //.011 //3.0 (12/23) //8.0(12/23-2)
	
	
	public static final double encoderCountsLeftToIn = 27.851497;//27.675; //29.07(12/23) //27.851497(12-24)
	public static final double encoderCountsRightToIn = 27.851497; //NEED TO CHANGE BEFORE FINAL! New gbs +gear ration conversion
	
	//CONSTANTS FOR TALONS SRX ENCODERS
	
	//IXBar (POSITION)
	public static final int kSlotIdxIX = 0;  //Which PID slot to pull gains from. Starting 2018, you can choose from 0,1,2 or 3. Only the first two (0,1) are visible in web-based configuration.
	public static final int kPIDLoopIdxIX = 0; //Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For now we just want the primary one.
	public static final int kTimeoutMsIX = 10; // set to zero to skip waiting for confirmation, set to nonzero to wait andreport to DS if action fails.
	public static boolean kSensorPhaseIX = true; //choose so that Talon does not report sensor out of phase
	public static boolean kMotorInvertIX = false; // choose based on what direction you want to be positive,this does not affect motor invert. 
	public static double kFIX = 0.0; //typically remains 0
	public static double kPIX = 0.1;
	public static double kIIX = 0.0;
	public static double kDIX = 0.0;
	public static double GRIX = (42/12); //ex: if you want to move 90degs you would multiplie 90 by 42/12 to get a total movement of the output shaft at 315degs
	
	
	//LIFT (VELOCITY)
	public static final int kSlotIdxL = 0; //Which PID slot to pull gains from. Starting 2018, you can choose from 0,1,2 or 3. Only the first two (0,1) are visible in web-based configuration.	 
	public static final int kPIDLoopIdxL = 0; //Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For now we just want the primary one.
	public static final int kTimeoutMsL = 10; //set to zero to skip waiting for confirmation, set to nonzero to wait andreport to DS if action fails.
	public static double kFL = 0.1097;
	public static double kPL = 0.113333;
	public static double kIL = 0.0;
	public static double kDL = 0.0;
	
	public static void init(){
		DriveLI = new Spark(1);
		DriveLII = new Spark(2);
		DriveRI = new Spark(3);
		DriveRII = new Spark(4);
		IXBar = new WPI_TalonSRX(0);
		IXBarSlave = new WPI_TalonSRX(1);
		Lift = new WPI_TalonSRX(2);
		LiftSlave = new WPI_TalonSRX(3);
		
		LiftSlave.follow(Lift);
		IXBarSlave.follow(IXBar);
		
		Lift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMsL);
		Lift.setSensorPhase(true); //set the peak, nominal outputs */
		Lift.configNominalOutputForward(0, RobotMap.kTimeoutMsL);
		Lift.configNominalOutputReverse(0, RobotMap.kTimeoutMsL);
		Lift.configPeakOutputForward(1, RobotMap.kTimeoutMsL);
		Lift.configPeakOutputReverse(-1, RobotMap.kTimeoutMsL); //set closed loop gains in slot0
		Lift.config_kF(RobotMap.kPIDLoopIdxL, RobotMap.kFL, RobotMap.kTimeoutMsL);
		Lift.config_kP(RobotMap.kPIDLoopIdxL, RobotMap.kPL, RobotMap.kTimeoutMsL);
		Lift.config_kI(RobotMap.kPIDLoopIdxL, RobotMap.kIL, RobotMap.kTimeoutMsL);
		Lift.config_kD(RobotMap.kPIDLoopIdxL, RobotMap.kDL, RobotMap.kTimeoutMsL);
		
		IXBar.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMsIX);
		IXBar.setSensorPhase(true); //set the peak, nominal outputs */
		IXBar.configNominalOutputForward(0, RobotMap.kTimeoutMsIX);
		IXBar.configNominalOutputReverse(0, RobotMap.kTimeoutMsIX);
		IXBar.configPeakOutputForward(1, RobotMap.kTimeoutMsIX);
		IXBar.configPeakOutputReverse(-1, RobotMap.kTimeoutMsIX); //set closed loop gains in slot0
		IXBar.config_kF(RobotMap.kPIDLoopIdxIX, RobotMap.kFIX, RobotMap.kTimeoutMsIX);
		IXBar.config_kP(RobotMap.kPIDLoopIdxIX, RobotMap.kPIX, RobotMap.kTimeoutMsIX);
		IXBar.config_kI(RobotMap.kPIDLoopIdxIX, RobotMap.kIIX, RobotMap.kTimeoutMsIX);
		IXBar.config_kD(RobotMap.kPIDLoopIdxIX, RobotMap.kDIX, RobotMap.kTimeoutMsIX);
		
		m_left = new SpeedControllerGroup(DriveLI, DriveLII);
		m_right = new SpeedControllerGroup(DriveRI, DriveRII);
		
		driveWC = new DifferentialDrive(m_left, m_right);
		
		leds = new Relay(0);
		
		navX = new AHRS(SPI.Port.kMXP);
		
		DTELeft = new Encoder(0, 1);
		DTERight = new Encoder(2, 3);
		
		LimIX = new DigitalInput(9);
		CubeSense = new DigitalInput(8);
		
		pnueCompressor = new Compressor(0);
		
		Extension = new DoubleSolenoid(0, 0, 1);
		Spring = new Solenoid(0, 2);
		
		/*CameraServer server1 = CameraServer.getInstance();
		server1.startAutomaticCapture();*/
	}
}

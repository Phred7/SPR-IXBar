/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2906.robot;

import org.usfirst.frc.team2906.robot.subsystems.DriveWC;
import org.usfirst.frc.team2906.robot.subsystems.IXBar;
import org.usfirst.frc.team2906.robot.subsystems.Intake;
import org.usfirst.frc.team2906.robot.subsystems.Lift;
import org.usfirst.frc.team2906.robot.subsystems.Limelight;
import org.usfirst.frc.team2906.robot.subsystems.NavX;
import org.usfirst.frc.team2906.robot.subsystems.Pneumatics;
import org.usfirst.frc.team2906.robot.commands.*;
import org.usfirst.frc.team2906.robot.commands.auto.*;
import org.usfirst.frc.team2906.robot.commands.auto.AutoPIILine;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static DriveWC driveWC;
	public static Intake intake;
	public static IXBar ixBar;
	public static Lift lift;
	public static Limelight limelight;
	public static Pneumatics pneumatics;
	public static NavX navX;
	public static OI oi;

	Command AutoNone;
	Command AutoLine;
	Command AutoPIILine;
	Command AutoPISwitch;
	Command AutoPIIISwitch;
	Command m_autonomousCommand;

	/*
	 * final String None = "No Auto"; final String Line = "PI/PII - Line"; final
	 * String PIILine = "PII - Line"; final String PI
	 */

	public static enum AutoPosition {
		NONE, I, II, III
	};

	public static enum AutoMode {
		NONE, SWITCH, SCALE, SWITCHTHENSCALE, LINE, SWITCHTHENSCALETWICE, SCALETWICE, AUTOSELECTEDBASEDONRANDOMIZATION
	};

	static AutoPosition position;
	static AutoMode autoModeType;

	SendableChooser<AutoPosition> autoPosition;
	SendableChooser<AutoMode> autoType;

	public void robotInit() {
		RobotMap.init();

		driveWC = new DriveWC();
		intake = new Intake();
		ixBar = new IXBar();
		lift = new Lift();
		limelight = new Limelight();
		pneumatics = new Pneumatics();
		navX = new NavX();
		oi = new OI();

		autoType = new SendableChooser<AutoMode>();
		autoPosition = new SendableChooser<AutoPosition>();

		SmartDashboard.putData("Auto Action", autoType);
		SmartDashboard.putData("Robot Position", autoPosition);

		autoPosition.addDefault("NONE", AutoPosition.NONE);
		autoPosition.addObject("I", AutoPosition.I);
		autoPosition.addObject("II", AutoPosition.II);
		autoPosition.addObject("III", AutoPosition.III);

		autoType.addDefault("None", AutoMode.NONE);
		autoType.addObject("Auto Selected", AutoMode.AUTOSELECTEDBASEDONRANDOMIZATION);
		autoType.addObject("Line", AutoMode.LINE);
		autoType.addObject("1-Switch", AutoMode.SWITCH);
		autoType.addObject("1-Scale", AutoMode.SCALE);
		autoType.addObject("1-Switch, 1-Scale", AutoMode.SWITCHTHENSCALE);
		autoType.addObject("2-Scale", AutoMode.SCALETWICE);
		autoType.addObject("1-Switch, 2-Scale", AutoMode.SWITCHTHENSCALETWICE);

	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		/*
		 * m_m_autonomousCommand = (Command) chooser.getSelected();
		 * System.out.println("Auto selected: " + chooser.getSelected());
		 * 
		 * 
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": m_autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: m_autonomousCommand =
		 * new ExampleCommand(); break; }
		 * 
		 * 
		 * // schedule the autonomous command (example) if (m_m_autonomousCommand !=
		 * null) { m_m_autonomousCommand.start(); } }
		 */

		// if(gameData.charAt(0) == 'L')

		DriverStation.Alliance color;
		String gameData;

		color = DriverStation.getInstance().getAlliance();
		gameData = DriverStation.getInstance().getGameSpecificMessage();

		autoModeType = (AutoMode) autoType.getSelected();
		position = (AutoPosition) autoPosition.getSelected();

		if (autoModeType == AutoMode.NONE) { // No Auto
			m_autonomousCommand = new AutoNone();

		} else if (position == AutoPosition.II && autoModeType != AutoMode.NONE) { // Position II
			m_autonomousCommand = new AutoPIILine();

		} else if (position == AutoPosition.I && autoModeType != AutoMode.NONE) { // Position I
			if (autoModeType == AutoMode.AUTOSELECTEDBASEDONRANDOMIZATION) { // Random
				if (gameData.contentEquals("LLL")) {
					m_autonomousCommand = new AutoPISwitch();
					// m_autonomousCommand = new AutoPISwitchThenScale();
				} else if (gameData.contentEquals("RRR")) {
					m_autonomousCommand = new AutoLine();
					// m_autonomousCommand = new AutoPISwitchOp();
				} else if (gameData.contentEquals("LRL")) {
					m_autonomousCommand = new AutoPISwitch();
				} else if (gameData.contentEquals("RLR")) {
					m_autonomousCommand = new AutoLine();
					// m_autonomousCommand = new AutoPIScale();
				}
			} else if (autoModeType != AutoMode.AUTOSELECTEDBASEDONRANDOMIZATION) { // Chooser
				if (gameData.contentEquals("LLL")) {
					if (autoModeType == AutoMode.LINE) {
						m_autonomousCommand = new AutoLine();
					} else if (autoModeType == AutoMode.SWITCH) {
						m_autonomousCommand = new AutoPISwitch();
					} else if (autoModeType == AutoMode.SCALE) {
						m_autonomousCommand = new AutoPIScale();
					} else if (autoModeType == AutoMode.SWITCHTHENSCALE) {
						m_autonomousCommand = new AutoPISwitchThenScale();
					} else if (autoModeType == AutoMode.SWITCHTHENSCALETWICE) {
						m_autonomousCommand = new AutoPISwitchThenScaleTwice();
					} else if (autoModeType == AutoMode.SCALETWICE) {
						m_autonomousCommand = new AutoPIScaleTwice();
					}
				} else if (gameData.contentEquals("RRR")) {
					if (autoModeType == AutoMode.LINE) {
						m_autonomousCommand = new AutoLine();
					} else if (autoModeType == AutoMode.SWITCH) {
						m_autonomousCommand = new AutoPISwitchOp();
					} else if (autoModeType == AutoMode.SCALE) {
						m_autonomousCommand = new AutoPIScaleOp();
					} else if (autoModeType == AutoMode.SWITCHTHENSCALE) {
						m_autonomousCommand = new AutoPISwitchOpThenScaleOp();
					} else if (autoModeType == AutoMode.SWITCHTHENSCALETWICE) {
						m_autonomousCommand = new AutoPISwitchOpThenScaleOp();
					} else if (autoModeType == AutoMode.SCALETWICE) {
						m_autonomousCommand = new AutoPIScaleTwiceOp();
					}
				} else if (gameData.contentEquals("LRL")) {
					if (autoModeType == AutoMode.LINE) {
						m_autonomousCommand = new AutoLine();
					} else if (autoModeType == AutoMode.SWITCH) {
						m_autonomousCommand = new AutoPISwitch();
					} else if (autoModeType == AutoMode.SCALE) {
						m_autonomousCommand = new AutoPIScaleOp();
					} else if (autoModeType == AutoMode.SWITCHTHENSCALE) {
						m_autonomousCommand = new AutoPISwitchThenScaleOp();
					} else if (autoModeType == AutoMode.SWITCHTHENSCALETWICE) {
						m_autonomousCommand = new AutoPISwitchThenScaleOp();
					} else if (autoModeType == AutoMode.SCALETWICE) {
						m_autonomousCommand = new AutoPIScaleTwiceOp();
					}
				} else if (gameData.contentEquals("RLR")) {
					if (autoModeType == AutoMode.LINE) {
						m_autonomousCommand = new AutoLine();
					} else if (autoModeType == AutoMode.SWITCH) {
						m_autonomousCommand = new AutoPISwitchOp();
					} else if (autoModeType == AutoMode.SCALE) {
						m_autonomousCommand = new AutoPIScale();
					} else if (autoModeType == AutoMode.SWITCHTHENSCALE) {
						m_autonomousCommand = new AutoPIScaleThenSwitchOp();
					} else if (autoModeType == AutoMode.SWITCHTHENSCALETWICE) {
						m_autonomousCommand = new AutoPIScaleThenSwitchOp();
					} else if (autoModeType == AutoMode.SCALETWICE) {
						m_autonomousCommand = new AutoPIScaleTwice();
					}
				}
			} else if (position == AutoPosition.III && autoModeType != AutoMode.NONE) { // Position III
				if (autoModeType == AutoMode.AUTOSELECTEDBASEDONRANDOMIZATION) {
					if (gameData.contentEquals("LLL")) {
						m_autonomousCommand = new AutoLine();
						// m_autonomousCommand = new AutoPIIISwitchOp;
					} else if (gameData.contentEquals("RRR")) {
						m_autonomousCommand = new AutoPIIISwitch();
						// m_AutonomousCommand = new AutoPIIISwitchThenSclae();
					} else if (gameData.contentEquals("LRL")) {
						m_autonomousCommand = new AutoLine();
						// m_AutonomousCommand = new AutoPIIIScale();
						// m_AutonomousCommand = new AutoPIIISwitchOp();
					} else if (gameData.contentEquals("RLR")) {
						m_autonomousCommand = new AutoPIIISwitch();
						// m_AutonomousCommand = new AutoPIIISwitchThenScaleOp();
					}
				} else if (autoModeType != AutoMode.AUTOSELECTEDBASEDONRANDOMIZATION) { // Chooser
					if (gameData.contentEquals("LLL")) {
						if (autoModeType == AutoMode.LINE) {
							m_autonomousCommand = new AutoLine();
						} else if (autoModeType == AutoMode.SWITCH) {
							m_autonomousCommand = new AutoPIIISwitchOp();
						} else if (autoModeType == AutoMode.SCALE) {
							m_autonomousCommand = new AutoPIIIScaleOp();
						} else if (autoModeType == AutoMode.SWITCHTHENSCALE) {
							m_autonomousCommand = new AutoPIIISwitchOpThenScaleOp();
						} else if (autoModeType == AutoMode.SWITCHTHENSCALETWICE) {
							m_autonomousCommand = new AutoPIIISwitchOpThenScaleOp();
						} else if (autoModeType == AutoMode.SCALETWICE) {
							m_autonomousCommand = new AutoPIIIScaleTwiceOp();
						}
					} else if (gameData.contentEquals("RRR")) {
						if (autoModeType == AutoMode.LINE) {
							m_autonomousCommand = new AutoLine();
						} else if (autoModeType == AutoMode.SWITCH) {
							m_autonomousCommand = new AutoPIIISwitch();
						} else if (autoModeType == AutoMode.SCALE) {
							m_autonomousCommand = new AutoPIIIScale();
						} else if (autoModeType == AutoMode.SWITCHTHENSCALE) {
							m_autonomousCommand = new AutoPIIISwitchThenScale();
						} else if (autoModeType == AutoMode.SWITCHTHENSCALETWICE) {
							m_autonomousCommand = new AutoPIIISwitchThenScaleTwice();
						} else if (autoModeType == AutoMode.SCALETWICE) {
							m_autonomousCommand = new AutoPIIIScaleTwice();
						}
					} else if (gameData.contentEquals("LRL")) {
						if (autoModeType == AutoMode.LINE) {
							m_autonomousCommand = new AutoLine();
						} else if (autoModeType == AutoMode.SWITCH) {
							m_autonomousCommand = new AutoPIIISwitchOp();
						} else if (autoModeType == AutoMode.SCALE) {
							m_autonomousCommand = new AutoPIIIScale();
						} else if (autoModeType == AutoMode.SWITCHTHENSCALE) {
							m_autonomousCommand = new AutoPIIIScaleThenSwitchOp();
						} else if (autoModeType == AutoMode.SWITCHTHENSCALETWICE) {
							m_autonomousCommand = new AutoPIIIScaleThenSwitchOp();
						} else if (autoModeType == AutoMode.SCALETWICE) {
							m_autonomousCommand = new AutoPIIIScaleTwice();
						}
					} else if (gameData.contentEquals("RLR")) {
						if (autoModeType == AutoMode.LINE) {
							m_autonomousCommand = new AutoLine();
						} else if (autoModeType == AutoMode.SWITCH) {
							m_autonomousCommand = new AutoPIIISwitch();
						} else if (autoModeType == AutoMode.SCALE) {
							m_autonomousCommand = new AutoPIIIScaleOp();
						} else if (autoModeType == AutoMode.SWITCHTHENSCALE) {
							m_autonomousCommand = new AutoPIIISwitchThenScaleOp();
						} else if (autoModeType == AutoMode.SWITCHTHENSCALETWICE) {
							m_autonomousCommand = new AutoPIIISwitchThenScaleOp();
						} else if (autoModeType == AutoMode.SCALETWICE) {
							m_autonomousCommand = new AutoPIIIScaleTwiceOp();
						}
					}
				}
			}

			if (m_autonomousCommand != null) {
				m_autonomousCommand.start();
			}
		}
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("navx angle", RobotMap.navX.getAngle());
		SmartDashboard.putNumber("navx pitch", RobotMap.navX.getPitch());
		SmartDashboard.putNumber("navx roll", RobotMap.navX.getRoll());
		SmartDashboard.putNumber("navx yaw", RobotMap.navX.getYaw());
	}

	public void teleopInit() {

		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("navx angle", RobotMap.navX.getAngle());
		SmartDashboard.putNumber("navx pitch", RobotMap.navX.getPitch());
		SmartDashboard.putNumber("navx roll", RobotMap.navX.getRoll());
		SmartDashboard.putNumber("navx yaw", RobotMap.navX.getYaw());
		SmartDashboard.putBoolean("Collission", Robot.navX.collisionDetection());
		SmartDashboard.putNumber("x velocity", Robot.navX.getXVelocity());
		SmartDashboard.putNumber("y velocity", Robot.navX.getYVelocity());
		SmartDashboard.putNumber("z velocity", Robot.navX.getZVelocity());
	}

	public void testPeriodic() {
	}
}

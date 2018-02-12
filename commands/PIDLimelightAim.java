package org.usfirst.frc.team2906.robot.commands;

import org.usfirst.frc.team2906.robot.Robot;
import org.usfirst.frc.team2906.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDLimelightAim extends Command {

    	double motorSpeed = 0.4;
    	double direction = 0;
    	double currentAngle = 0;
    	double distance = 0;
        double error = 0;
        double pAdjustment = 0;
        double iAdjustment = 0;
        double dAdjustment = 0;
        double lastError = 0;
        double PIDAdjustment = 0;
        double speed = 0;
        double way = 1;
        int n = 0;
        Timer time;
        boolean pid = false;

	public PIDLimelightAim() {
        	requires(Robot.driveWC); 
        }

	protected void initialize() {
		dAdjustment = 0;
		iAdjustment = 0;
		n = 0;
		pAdjustment = 0;
		error = 0;
		lastError = 0;
		PIDAdjustment = 0;
		direction = RobotMap.azimuthToTarget + Robot.navX.getNavXAngle() + RobotMap.cubeCameraOffSetInDegrees;
		time = new Timer();
		time.reset();
		time.start();
	}

	protected void execute() {
		System.out.println(direction + "direction");
		currentAngle = Robot.navX.getNavXAngle();
		error = direction - currentAngle;
		System.out.println(error + "error");
		pAdjustment = error * RobotMap.PIDCameraAimP * RobotMap.PIDCameraAimGainMultiplier;
		iAdjustment = iAdjustment + (error * RobotMap.PIDCameraAimI * RobotMap.PIDCameraAimGainMultiplier);
		dAdjustment = (error - lastError) * RobotMap.PIDCameraAimD * RobotMap.PIDCameraAimGainMultiplier;
		lastError = error;
		PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;
		if (Robot.navX.getNavXAngle() < 10 + direction && Robot.navX.getNavXAngle() > direction - 10 && pid == false) {
			iAdjustment = 0;
			pid = true;
		}

		if (pid == true) {
			if (PIDAdjustment > .3) {
				speed = .3;
			} else if (PIDAdjustment < -.3) {
				speed = -.3;
			} else {
				speed = PIDAdjustment;
			}
		} else {
			if (error > 0) {
				speed = motorSpeed;
			} else {
				speed = -motorSpeed;
			}
		}
		Robot.driveWC.driveR((speed));
		Robot.driveWC.driveL(-(speed));
		if (Robot.navX.getNavXAngle() < .5 + direction && Robot.navX.getNavXAngle() > direction - .5) {
			n++;
		} else {
			n = 0;
		}
		SmartDashboard.putNumber("speed: ", speed);
	}

	protected boolean isFinished() {
		return (Robot.navX.getNavXAngle() < .5 + direction && Robot.navX.getNavXAngle() > direction - .5 && n > 5);
	}

	protected void end() {
		Robot.driveWC.stop();
		SmartDashboard.putString("done", "done");
	}

	protected void interrupted() {
		end();
	}
}

package org.usfirst.frc.team2906.robot.subsystems;

import org.usfirst.frc.team2906.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {

    DoubleSolenoid E = RobotMap.Extension;
    DoubleSolenoid S = RobotMap.ExtraIn;

    public void Extend() {
    	E.set(Value.kForward);
    }
    
    public void Retract() {
    	E.set(Value.kReverse);
    }
    
    public void Activate() {
    	S.set(Value.kForward);
    }
    
    public void Deactivate() {
    	S.set(Value.kReverse);
    }
    
    public void RSet() {
    	E.set(Value.kReverse);
    	S.set(Value.kForward);
    }
    
    public void initDefaultCommand() {
    }
}


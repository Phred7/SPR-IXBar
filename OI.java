/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2906.robot;

import org.usfirst.frc.team2906.robot.commands.Activate;
import org.usfirst.frc.team2906.robot.commands.Deactivate;
import org.usfirst.frc.team2906.robot.commands.DoubleExtendDelay;
import org.usfirst.frc.team2906.robot.commands.DoubleRetractDelay;
import org.usfirst.frc.team2906.robot.commands.DriveArcade;
import org.usfirst.frc.team2906.robot.commands.Extend;
import org.usfirst.frc.team2906.robot.commands.IXBarDrive;
import org.usfirst.frc.team2906.robot.commands.IXBarStop;
import org.usfirst.frc.team2906.robot.commands.LEDs;
import org.usfirst.frc.team2906.robot.commands.LiftDrive;
import org.usfirst.frc.team2906.robot.commands.LiftStop;
import org.usfirst.frc.team2906.robot.commands.LimeLEDsOff;
import org.usfirst.frc.team2906.robot.commands.LimeLEDsOn;
import org.usfirst.frc.team2906.robot.commands.Retract;
import org.usfirst.frc.team2906.robot.commands.Stop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick joystick1;
	public Joystick joystick2;
	public Joystick joystick3;
	
	public JoystickButton trigr1;
	public JoystickButton trigr2;
	public JoystickButton trigr3;
	public JoystickButton B11;
	public JoystickButton B12;
	public JoystickButton B3;
	public JoystickButton B4;
	
	public OI(){
		joystick1 = new Joystick(0);
		joystick2 = new Joystick(1);
		joystick3 = new Joystick(2);
		
		/*trigr1 = new JoystickButton(joystick1, 1);
		trigr1.whileHeld(new DriveArcade());
		trigr1.whenReleased(new Stop());*/
		
		B11 = new JoystickButton(joystick1, 11);
		B11.whenPressed(new DoubleExtendDelay());
		
		B12 = new JoystickButton(joystick1, 12);
		B12.whenPressed(new DoubleRetractDelay());
		
		B3 = new JoystickButton(joystick1, 3);
		B3.whenPressed(new LimeLEDsOn());
		B3.whileHeld(new Activate());
		
		B4 = new JoystickButton(joystick1, 4);
		B4.whenPressed(new LimeLEDsOff());
		B4.whileHeld(new Deactivate());
		
		trigr2 = new JoystickButton(joystick2, 1);
		trigr2.whileHeld(new IXBarDrive());
		trigr2.whenReleased(new IXBarStop());
		
		trigr3 = new JoystickButton(joystick3, 1);
		trigr3.whileHeld(new LiftDrive());
		trigr3.whenReleased(new LiftStop());
		
		
	}
	public Joystick getJoystick1() {
        return joystick1;
    }
   
    public double getJoystick1X(){
    	if(Math.abs(joystick1.getX())>RobotMap.sensitivity){
    		return -1*joystick1.getX();
    	} 
    	else {
    		return 0.0;
    	}
    }
    
    public double getJoystick1Y(){
    	if(Math.abs(joystick1.getY())>RobotMap.sensitivity){
    		return -1*joystick1.getY();
    	} 
    	else {
    		return 0.0;
    	}
    }
    
    public double getJoystick1R(){
    	if(Math.abs(joystick1.getRawAxis(2))>RobotMap.sensitivity){
    		return -1*joystick1.getRawAxis(2);
    	} 
    	else {
    		return 0.0;
    	}
    }
    
    public double getJoystick1Y1(){
    	if(Math.abs(joystick1.getRawAxis(0))>RobotMap.sensitivity){
    		return 1*joystick1.getRawAxis(0);
    	} 
    	else {
    		return 0.0;
    	}
    }
    
    public double getJoystick1Y2(){
    	if(Math.abs(joystick1.getRawAxis(2))>RobotMap.sensitivity){
    		return 1*joystick1.getRawAxis(2);
    	} 
    	else {
    		return 0.0;
    	}
    }
    
    public Joystick getJoystick2(){
    	return joystick2;
    }
    public double getJoystick2Y(){
    	if(Math.abs(joystick2.getY())>RobotMap.sensitivity){
    		return .4*joystick2.getY();
    	} 
    	else {
    		return 0.0;
    	}
    }
    public double getJoystick2X(){
    	if(Math.abs(joystick2.getY())>RobotMap.sensitivity){
    		return .4*joystick2.getY();
    	} 
    	else {
    		return 0.0;
    	}
    }
    public Joystick getJoystick3(){
    	return joystick3;
    }
    public double getJoystick3Y(){
    	if(Math.abs(joystick3.getY())>RobotMap.sensitivity){
    		return .4*joystick3.getY();
    	} 
    	else {
    		return 0.0;
    	}
    }
    public double getJoystick3X(){
    	if(Math.abs(joystick3.getY())>RobotMap.sensitivity){
    		return .4*joystick3.getY();
    	} 
    	else {
    		return 0.0;
    	}
    }
}
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
	
	public JoystickButton trigr1L;
	public JoystickButton trigr1R;
	public JoystickButton x;
	public JoystickButton a;
	public JoystickButton b;
	public JoystickButton y;
	public JoystickButton LB;
	public JoystickButton RB;
	public JoystickButton LT;
	public JoystickButton RT;
	public JoystickButton Back;
	public JoystickButton Start;
	public JoystickButton LClick;
	public JoystickButton RClick;
	
	public JoystickButton trigr2;
	public JoystickButton B2_2;
	public JoystickButton B3_2;
	public JoystickButton B4_2;
	
	public JoystickButton trigr3;
	public JoystickButton thumb3;
	public JoystickButton B3_3;
	public JoystickButton B4_3;
	public JoystickButton B5_3;
	public JoystickButton B6_3;
	
	
	
	public OI(){
		joystick1 = new Joystick(0);
		joystick2 = new Joystick(1);
		joystick3 = new Joystick(2);
		

		
		
	}
	public Joystick getJoystick1() {
        return joystick1;
    }
   
    public double getJoystick1X1(){
    	if(Math.abs(joystick1.getX())>RobotMap.sensitivity){
    		return -1*joystick1.getX();
    	} 
    	else {
    		return 0.0;
    	}
    }
    
    public double getJoystick1X2(){
    	if(Math.abs(joystick1.getRawAxis(2))>RobotMap.sensitivity){
    		return -1*joystick1.getRawAxis(2);
    	} 
    	else {
    		return 0.0;
    	}
    }
    
    public double getJoystick1Y1(){
    	if(Math.abs(joystick1.getRawAxis(1))>RobotMap.sensitivity){
    		return 1*joystick1.getRawAxis(1);
    	} 
    	else {
    		return 0.0;
    	}
    }
    
    public double getJoystick1Y2(){
    	if(Math.abs(joystick1.getRawAxis(3))>RobotMap.sensitivity){
    		return 1*joystick1.getRawAxis(3);
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
    public double getJoystick1POV(){
    	if(joystick1.getPOV(0) == 0){
    		return 1.0;
    	} else if(joystick1.getPOV(0) == 90){
    		return 90;
    	} else if(joystick1.getPOV(0) == 180){
    		return -1.0;
    	}else if(joystick1.getPOV(0) == 270){
    		return -90;
    	} else if(joystick1.getPOV(0) == -1){
    		return 0.0;
    	}
    	else{
    		return 0.0;
    	}
    }
	
	public double getJoystick3POV(){
    	if(joystick3.getPOV(0) == 270){
    		return -10.0;
    	} else if(joystick2.getPOV(0) == 90){
    		return 10.0;
    	} else if(joystick2.getPOV(0) == 45){
    		return 0.25;
    	}else if(joystick2.getPOV(0) == 315){
    		return -0.25;
    	} else if(joystick2.getPOV(0) == -1){
    		return 0.0;
    	}
    	else{
    		return 0.0;
    	}
    }
}
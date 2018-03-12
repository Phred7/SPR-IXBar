/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2906.robot;


import org.usfirst.frc.team2906.robot.commands.Extend;
import org.usfirst.frc.team2906.robot.commands.IXBarDrive;
import org.usfirst.frc.team2906.robot.commands.IXBarPIDDrive;
import org.usfirst.frc.team2906.robot.commands.IXBarPIDJoyDrive;
import org.usfirst.frc.team2906.robot.commands.IXBarStop;
import org.usfirst.frc.team2906.robot.commands.LEDs;
import org.usfirst.frc.team2906.robot.commands.LiftDrive;
import org.usfirst.frc.team2906.robot.commands.LiftPositionControl;
import org.usfirst.frc.team2906.robot.commands.LiftStop;
import org.usfirst.frc.team2906.robot.commands.LimeLEDsOff;
import org.usfirst.frc.team2906.robot.commands.LimeLEDsOn;
import org.usfirst.frc.team2906.robot.commands.RelayLEDsOff;
import org.usfirst.frc.team2906.robot.commands.RelayLEDsOn;
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
		
		RT = new JoystickButton(joystick1, 8);
		RT.whileHeld(new LiftDrive());
		RT.whenReleased(new LiftStop());
		
		RB = new JoystickButton(joystick1, 6);
		RB.whileHeld(new IXBarPIDDrive(3));
		RB.whenReleased(new IXBarPIDDrive(0.0001));
		
		LT = new JoystickButton(joystick1, 7);
		LT.whenPressed(new LimeLEDsOn());
		LT.whenPressed(new RelayLEDsOn());
		
		LB = new JoystickButton(joystick1, 5);
		LB.whenPressed(new LimeLEDsOff());
		LB.whenPressed(new RelayLEDsOff());
		
		b = new JoystickButton(joystick1, 3);
		b.whileHeld(new LiftPositionControl());
		b.whenReleased(new LiftStop());
		
		
		
		trigr2 = new JoystickButton(joystick2, 1);
		trigr2.whileHeld(new IXBarPIDJoyDrive());
		trigr2.whenReleased(new IXBarPIDDrive(0.0001));
		//trigr2.whenReleased(new IXBarStop());
		
		B2_2 = new JoystickButton(joystick2, 1);
		B2_2.whileHeld(new IXBarPIDDrive(10));
		B2_2.whenReleased(new IXBarPIDDrive(0.0001));
		//B2_2.whenReleased(new IXBarStop());
		
		B3_2 = new JoystickButton(joystick2, 3);
		B3_2.whileHeld(new IXBarPIDDrive(5));
		B3_2.whenReleased(new IXBarPIDDrive(0.0001));
		//B3_2.whenReleased(new IXBarStop());
		
		B4_2 = new JoystickButton(joystick2, 4);
		B4_2.whileHeld(new IXBarPIDDrive(20));
		B4_2.whenReleased(new IXBarPIDDrive(0.0001));
		//B4_2.whenReleased(new IXBarStop());
		
		
		
		trigr3 = new JoystickButton(joystick3, 1);
		trigr3.whenPressed(new Extend());
		
		thumb3 = new JoystickButton(joystick3, 2);
		thumb3.whenPressed(new Retract());
		
		
		
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
    	if(joystick1.getPOV(0) == 0){
    		return 1.0;
    	} else if(joystick1.getPOV(0) == 180){
    		return -1.0;
    	} else if(joystick1.getPOV(0) == -1){
    		return 0.0;
    	} else if(joystick2.getPOV(0) == 135){
    		return -10.0;
    	} else if(joystick2.getPOV(0) == 225){
    		return -10.0;
    	} else if(joystick2.getPOV(0) == 45){
    		return 10;
    	} else if(joystick2.getPOV(0) == 315){
    		return 10;
    	} else{
    		return 0.0;
    	}
    }
}
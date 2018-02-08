/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2906.robot;

import org.usfirst.frc.team2906.robot.commands.Activate;
import org.usfirst.frc.team2906.robot.commands.Deactivate;
import org.usfirst.frc.team2906.robot.commands.Extend;
import org.usfirst.frc.team2906.robot.commands.LEDsOff;
import org.usfirst.frc.team2906.robot.commands.LEDsOn;
import org.usfirst.frc.team2906.robot.commands.Retract;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
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
		
		trigr1 = new JoystickButton(joystick1, 1);
		trigr1.whileHeld(new LEDsOn());
		trigr1.whenReleased(new LEDsOff());
		
		B11 = new JoystickButton(joystick1, 11);
		B11.whenPressed(new Extend());
		
		B12 = new JoystickButton(joystick1, 12);
		B12.whenPressed(new Retract());
		
		B3 = new JoystickButton(joystick1, 3);
		B3.whileHeld(new Activate());
		
		B4 = new JoystickButton(joystick1, 4);
		B4.whileHeld(new Deactivate());
		
		
		
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
}

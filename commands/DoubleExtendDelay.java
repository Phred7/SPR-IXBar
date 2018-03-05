package org.usfirst.frc.team2906.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DoubleExtendDelay extends CommandGroup {

    public DoubleExtendDelay() {
    	addSequential(new Activate());
        //addSequential(new WaitCommand(.1));
        addSequential(new Extend());       
    }
}

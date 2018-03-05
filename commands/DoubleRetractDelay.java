package org.usfirst.frc.team2906.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DoubleRetractDelay extends CommandGroup {

    public DoubleRetractDelay() {
       addSequential(new Retract());
       //addSequential(new WaitCommand(.1));
       addSequential(new Deactivate());
    }
}

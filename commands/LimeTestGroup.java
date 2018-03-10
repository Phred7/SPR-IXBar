package org.usfirst.frc.team2906.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LimeTestGroup extends CommandGroup {

    public LimeTestGroup() {
        addSequential(new LimeTest());
        addSequential(new WaitCommand(.1));
        addSequential(new RelayLEDsOn());
        addSequential(new LimeTestStraight());
        addSequential(new RelayLEDsOff());
        addSequential(new WaitCommand(.5));
        
    }
}

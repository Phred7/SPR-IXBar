package org.usfirst.frc.team2906.robot.commands.auto;

import org.usfirst.frc.team2906.robot.commands.PIDDriveStraight;
import org.usfirst.frc.team2906.robot.commands.PIDNavXTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPIILine extends CommandGroup {

    public AutoPIILine() {
       addSequential(new PIDDriveStraight(.35, 0, 50));
       addSequential(new PIDNavXTurn(60));
       addSequential(new PIDDriveStraight(.35, 0, 50));
       addSequential(new PIDNavXTurn(-60));
       addSequential(new PIDDriveStraight(.35, 0, 50));
    }
}

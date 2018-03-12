package org.usfirst.frc.team2906.robot.commands.auto;

import org.usfirst.frc.team2906.robot.commands.PIDDriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLine extends CommandGroup {

    public AutoLine() {
        addSequential(new PIDDriveStraight(.35, 0, 140));
    }
}

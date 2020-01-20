package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command raises the robot in the climb while maintaining current level.
 */
public class ClimbLevelCommand extends CommandBase {
    private ClimbSubsystem m_climbSubsystem;

    public ClimbLevelCommand(ClimbSubsystem climbSubsystem) {
        m_climbSubsystem = climbSubsystem;
        addRequirements(m_climbSubsystem);
    }
}
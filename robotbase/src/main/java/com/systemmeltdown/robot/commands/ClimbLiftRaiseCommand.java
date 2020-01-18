package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command raises the scissor lift for climb.
 */
public class ClimbLiftRaiseCommand extends CommandBase {
    private ClimbSubsystem m_climbSubsystem;

    public ClimbLiftRaiseCommand(ClimbSubsystem climbSubsystem) {
        m_climbSubsystem = climbSubsystem;
        addRequirements(m_climbSubsystem);
    }

}
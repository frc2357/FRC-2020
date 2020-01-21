package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command lowers the scissor lift for climb.
 */
public class ClimbLiftLowerCommand extends CommandBase {
    private ClimbSubsystem m_climbSubsystem;

    public ClimbLiftLowerCommand(ClimbSubsystem climbSubsystem) {
        m_climbSubsystem = climbSubsystem;
        addRequirements(m_climbSubsystem);
    }
}
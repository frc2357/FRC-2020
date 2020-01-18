package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command climbs toward the right side of the robot.
 */
public class ClimbRightCommand extends CommandBase {
    private ClimbSubsystem m_climbSubsystem;

    public ClimbRightCommand(ClimbSubsystem climbSubsystem) {
        m_climbSubsystem = climbSubsystem;
        addRequirements(m_climbSubsystem);
    }
}
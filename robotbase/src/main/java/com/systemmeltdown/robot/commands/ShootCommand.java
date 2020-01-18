package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command shoots a ball.
 */
public class ShootCommand extends CommandBase {
    private ShooterSubsystem m_shooterSubsystem;

    public ShootCommand(ShooterSubsystem shooterSubsystem) {
        m_shooterSubsystem = shooterSubsystem;
        addRequirements(m_shooterSubsystem);
    }
}

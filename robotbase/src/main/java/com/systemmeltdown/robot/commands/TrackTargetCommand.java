package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command makes the shooter track a target.
 */
public class TrackTargetCommand extends CommandBase {
    private TurretSubsystem m_turretSubsystem;
    private ShooterSubsystem m_shooterSubsystem;

    public TrackTargetCommand(ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem) {
        m_shooterSubsystem = shooterSubsystem;
        m_turretSubsystem = turretSubsystem;
        addRequirements(m_shooterSubsystem, m_turretSubsystem);
    }
}

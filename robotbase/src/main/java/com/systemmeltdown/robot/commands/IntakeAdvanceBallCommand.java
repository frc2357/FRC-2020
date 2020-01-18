package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSub;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command advances the intake one ball towards the shooter.
 */
public class IntakeAdvanceBallCommand extends CommandBase {
    private IntakeSub m_intakeSubsystem;

    public IntakeAdvanceBallCommand(IntakeSub intakeSubsystem) {
        m_intakeSubsystem = intakeSubsystem;
        addRequirements(m_intakeSubsystem);
    }
}
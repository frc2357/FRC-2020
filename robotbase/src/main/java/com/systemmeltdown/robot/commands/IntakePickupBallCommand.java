package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSub;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command brings a ball into the intake from the ground.
 */
public class IntakePickupBallCommand extends CommandBase {
    private IntakeSub m_intakeSubsystem;

    public IntakePickupBallCommand(IntakeSub intakeSubsystem) {
        m_intakeSubsystem = intakeSubsystem;
        addRequirements(m_intakeSubsystem);
    }
}
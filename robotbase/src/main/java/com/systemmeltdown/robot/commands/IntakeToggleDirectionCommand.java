package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeToggleDirectionCommand extends CommandBase {
    private IntakeSubsystem m_intakeSub;

    public IntakeToggleDirectionCommand(IntakeSubsystem intakeSub) {
        m_intakeSub = intakeSub;
        addRequirements(intakeSub);
    }

    @Override
    public void initialize() {
        m_intakeSub.toggleRollDirection();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
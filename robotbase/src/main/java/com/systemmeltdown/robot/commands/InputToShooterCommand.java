package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class InputToShooterCommand extends CommandBase {
    private FeederSubsystem m_feederSubsystem;

    public InputToShooterCommand(FeederSubsystem feederSubsystem) {
        m_feederSubsystem = feederSubsystem;
        addRequirements(m_feederSubsystem);
    }

    @Override
    public void execute() {
        m_feederSubsystem.runFeederMotor(0.0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
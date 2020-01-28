package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class InputToShooterCommand extends CommandBase{
    private ShooterSubsystem m_shooterSubsystem;

    public InputToShooterCommand(ShooterSubsystem shooterSubsystem) {
        m_shooterSubsystem = shooterSubsystem;
        addRequirements(m_shooterSubsystem);
    }

    @Override 
    public void execute() {
        m_shooterSubsystem.runFeederMotor(0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
} 
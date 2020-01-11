package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootCommand extends CommandBase {
    ShooterSubsystem m_shooterSubsystem;

    public ShootCommand(ShooterSubsystem shooterSubsystem) {
        m_shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        System.out.println("SHOOT");
        m_shooterSubsystem.runMotor(1.0);
    }
}
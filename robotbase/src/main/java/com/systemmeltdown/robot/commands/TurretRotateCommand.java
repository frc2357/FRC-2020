package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurretRotateCommand extends CommandBase {
    private TurretSubsystem m_turretSub;
    private boolean m_rotatePositive;

    public TurretRotateCommand(TurretSubsystem turretSub, boolean rotatePositive) {
        m_turretSub = turretSub;
        m_rotatePositive = rotatePositive;
        addRequirements(turretSub);
    }

    @Override
    public void execute() {
        m_turretSub.setTurretMotorSpeed(m_rotatePositive ? 1 : -1);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_turretSub.setTurretMotorSpeed(0);
    }
}
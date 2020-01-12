package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootCommand extends CommandBase {
    private ShooterSubsystem m_shootSub;
    private GunnerControls m_gunnerControls;

    public ShootCommand(ShooterSubsystem shootSub, GunnerControls gunnerControls) {
        m_shootSub = shootSub;
        m_gunnerControls = gunnerControls;
        addRequirements(shootSub);
    }

    @Override
    public void execute() {
        m_shootSub.runMotor(m_gunnerControls.getTriggerValue(Hand.kRight));
    }

    @Override
    public void end(boolean interupted) {
        m_shootSub.runMotor(0.0);
    }
}
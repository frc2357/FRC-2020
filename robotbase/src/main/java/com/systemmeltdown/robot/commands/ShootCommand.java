package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;

import frc.robot.Constants;

/**
 * Tells the turret to shoot by calling runMotor() on the {@link ShooterSubsystem}.
 *
 * This command uses a constant shooting velocity and needs to be
 * paired with an aiming hood.
 * @category Turret
 */
public class ShootCommand extends CommandLoggerBase {
    private ShooterSubsystem m_shootSub;
    private GunnerControls m_gunnerControls;

    /**
     * @param shootSub The {@link ShooterSubsystem}.
     * 
     * @param gunnerControls The {@link GunnerControls}.
     */
    public ShootCommand(ShooterSubsystem shootSub, GunnerControls gunnerControls) {
        m_shootSub = shootSub;
        m_gunnerControls = gunnerControls;
        addRequirements(shootSub);
    }

    @Override
    public void execute() {
        m_shootSub.setMotorSpeed(Constants.SHOOTER_MAX_SPEED_RPM);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_shootSub.runMotorOpenLoop(0.0);
    }
}

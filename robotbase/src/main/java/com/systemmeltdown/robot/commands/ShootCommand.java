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
    private final ShooterSubsystem m_shootSub;

    /**
     * @param shootSub       The {@link ShooterSubsystem}.
     */
    public ShootCommand(final ShooterSubsystem shootSub) {
        m_shootSub = shootSub;
        addRequirements(shootSub);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_shootSub.setMotorSpeed(Constants.SHOOTER_MAX_SPEED_RPM);
    }

    @Override
    public void end(final boolean interrupted) {
        super.end(interrupted);
        m_shootSub.runMotorOpenLoop(0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

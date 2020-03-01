package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ShooterSubsystem;

/**
 * Tells the turret to shoot by calling runMotor() on the {@link ShooterSubsystem}.
 *
 * This command uses a constant shooting velocity and needs to be
 * paired with an aiming hood.
 * @category Turret
 */
public class ShooterSpeedCommand extends CommandLoggerBase {
    private final ShooterSubsystem m_shootSub;
    private final double m_speed;

    /**
     * @param shootSub       The {@link ShooterSubsystem}.
     */
    public ShooterSpeedCommand(final ShooterSubsystem shootSub, final double speed) {
        m_shootSub = shootSub;
        m_speed = speed;
        addRequirements(shootSub);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_shootSub.setMotorSpeed(m_speed);
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

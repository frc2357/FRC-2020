package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robotlib.subsystems.LimelightSubsystem.VisionTarget;

import frc.robot.Constants;

/**
 * Tells the turret to shoot by calling runMotor() on the {@link ShooterSubsystem}.
 *
 * This command uses a constant shooting velocity and needs to be
 * paired with an aiming hood.
 * @category Turret
 */
public class ShooterVisionCommand extends CommandLoggerBase {
    private final ShooterSubsystem m_shootSub;
    private final TogglableLimelightSubsystem m_visionSub;
    private double m_targetHeight = Constants.VISION_TARGET_HEIGHT_FROM_FLOOR;

    /**
     * @param shootSub       The {@link ShooterSubsystem}.
     */
    public ShooterVisionCommand(final ShooterSubsystem shootSub, final TogglableLimelightSubsystem visionSub) {
        m_shootSub = shootSub;
        m_visionSub = visionSub;
        addRequirements(shootSub);
    }

    public VisionTarget getTarget() {
        return m_visionSub.acquireTarget(m_targetHeight);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_shootSub.setVisionTarget(this::getTarget);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(final boolean interrupted) {
        super.end(interrupted);
        m_shootSub.setVisionTarget(null);
        m_shootSub.runMotorOpenLoop(0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

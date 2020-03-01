package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robotlib.subsystems.LimelightSubsystem.VisionTarget;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;

import frc.robot.Constants;

/**
 * This command makes the shooter track a target.
 * 
 * @category Turret
 * @category Automode Commands
 */
public class TrackTargetCommand extends CommandLoggerBase {
    private TurretSubsystem m_turretSubsystem;
    private TogglableLimelightSubsystem m_limelightSubsystem;

    /** Target height from floor in inches */
    private double m_targetHeight = Constants.VISION_TARGET_HEIGHT_FROM_FLOOR;
    private boolean m_finishWhenLocked;

    public TrackTargetCommand(TurretSubsystem turretSubsystem, TogglableLimelightSubsystem limelightSubsystem) {
        this(turretSubsystem, limelightSubsystem, false);
    }

    /**
     * This command uses the limelight to track targets. Uses {@link Constants} to find the target.
     * 
     * @param turretSubsystem The {@link TurretSubsystem}.
     * @param limelightSubsystem The {@link LimelightSubsystem}.
     */
    public TrackTargetCommand(TurretSubsystem turretSubsystem, TogglableLimelightSubsystem limelightSubsystem, boolean finishWhenLocked) {
        m_turretSubsystem = turretSubsystem;
        m_limelightSubsystem = limelightSubsystem;
        m_finishWhenLocked = finishWhenLocked;
        addRequirements(m_turretSubsystem, m_limelightSubsystem);
    }

    public VisionTarget getTarget() {
        return m_limelightSubsystem.acquireTarget(m_targetHeight);
    }

    @Override
    public boolean isFinished() {
        if (m_finishWhenLocked) {
            return m_turretSubsystem.isTargetLocked();
        }
        return false;
    }

    @Override
    public void initialize() {
        m_turretSubsystem.enableClosedLoop(this::getTarget);
    }

    @Override
    public void end(boolean cancelled) {
        m_turretSubsystem.disableClosedLoop();
    }
}

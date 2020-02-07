package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robotlib.subsystems.LimelightSubsystem;
import com.systemmeltdown.robotlib.util.Utility;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * This command makes the shooter track a target.
 */
public class TrackTargetCommand extends CommandBase {
    /** The tracking mode */
    private enum Mode {
        Seeking, /// < No target visible, looking for it
        Aiming,  /// < Target visible, aiming turret
        Locked   /// < Turret aimed at target
    }

    private TurretSubsystem m_turretSubsystem;
    private LimelightSubsystem m_limelightSubsystem;
    private Mode m_currentMode;
    private PIDController m_aimController;

    /**
     * Speed at which to turn the turret while seeking, units match
     * TurretSubsystem.setTurretMotor
     */
    private double m_seekingSpeed = Constants.TURRET_SEEK_SPEED;

    /** Target height from floor in inches */
    private double m_targetHeight = Constants.VISION_TARGET_HEIGHT_FROM_FLOOR;

    /** We consider the aim correct if the target offset is within this (degrees) */
    private double m_targetLockTolerance = Constants.TURRET_AIM_TOLERANCE;

    public TrackTargetCommand(TurretSubsystem turretSubsystem, LimelightSubsystem limelightSubsystem) {
        m_turretSubsystem = turretSubsystem;
        m_limelightSubsystem = limelightSubsystem;
        addRequirements(m_turretSubsystem, m_limelightSubsystem);

        m_currentMode = Mode.Seeking;
        m_aimController = new PIDController(Constants.TURRET_AIM_P, Constants.TURRET_AIM_I, Constants.TURRET_AIM_D);
        SendableRegistry.addLW(m_aimController, getSubsystem(), "aimPID");
    }

    public String getCurrentMode() {
        return m_currentMode.name();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addStringProperty("targetingMode", this::getCurrentMode, null);
    }

    @Override
    public void initialize() {
        m_currentMode = Mode.Seeking;
    }

    @Override
    public void execute() {
        LimelightSubsystem.VisionTarget target = m_limelightSubsystem.acquireTarget(m_targetHeight);

        // possibly change mode based on target
        switch (m_currentMode) {
        case Seeking:
            if (target != null) {
                m_currentMode = Mode.Aiming;
            }
            break;
        case Aiming:
            if (target == null) {
                m_currentMode = Mode.Seeking;
            } else if (isTargetLocked()) {
                m_currentMode = Mode.Locked;
            }
            break;
        case Locked:
            if (target == null) {
                m_currentMode = Mode.Seeking;
            } else if (!isTargetLocked()) {
                m_currentMode = Mode.Aiming;
            }
            break;
        }

        double turretRotateSpeed = 0;
        switch (m_currentMode) {
        case Seeking:
            turretRotateSpeed = m_seekingSpeed;
            break;
        case Aiming:
            double cv = m_aimController.calculate(target.getX(), getDesiredTargetAngle(target));
            turretRotateSpeed = Utility.clamp(cv, -1, 1);
            break;
        case Locked:
            // hold still if locked. change this if minor adjustments are needed
            turretRotateSpeed = 0;
            break;
        }

        m_turretSubsystem.setTurretMotor(turretRotateSpeed);
    }

    public boolean isTargetLocked() {
        LimelightSubsystem.VisionTarget target = m_limelightSubsystem.getCurrentTarget();
        if (target == null) {
            return false;
        }
        double desiredAngle = getDesiredTargetAngle(target);
        return Math.abs(desiredAngle - target.getX()) <= m_targetLockTolerance;
    }

    /**
     * To aim at the hole behind the target, we need the target to be offset from
     * the center of the camera. This function uses the law of cosines to determine
     * the correct offset angle.
     * 
     * TODO check that the target rotation sign is positive to the left of the robot
     * 
     * @param target
     * @return desired vision target offset in degrees
     */
    private double getDesiredTargetAngle(LimelightSubsystem.VisionTarget target) {
        final double theta = Math.toRadians(target.getTargetRotationDegrees());
        final double targetDist = target.getInchesFromTarget();
        final double recessDist = Constants.VISION_DISTANCE_TO_HOLE;

        // cos(pi - theta) = -cos(theta)
        double distanceToHoleSq = recessDist * recessDist + targetDist * targetDist
                + 2 * recessDist * targetDist * Math.cos(theta);
        double phi = Math.acos(recessDist * recessDist - distanceToHoleSq - targetDist * targetDist
                + 2 * Math.sqrt(distanceToHoleSq) * targetDist);
        return Math.toDegrees(phi);
    }

}

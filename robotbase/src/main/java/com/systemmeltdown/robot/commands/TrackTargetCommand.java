package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem.PipelineIndex;
import com.systemmeltdown.robotlib.subsystems.LimelightSubsystem.VisionTarget;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;

//import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
//import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
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
    private VisionTarget m_currentTarget;

    /** Target height from floor in inches */
    private double m_targetHeight = Constants.VISION_TARGET_HEIGHT_FROM_FLOOR;

    /**
     * This command uses the limelight to track targets. Uses {@link Constants} to find the target.
     * 
     * @param turretSubsystem The {@link TurretSubsystem}.
     * @param limelightSubsystem The {@link LimelightSubsystem}.
     */
    public TrackTargetCommand(TurretSubsystem turretSubsystem, TogglableLimelightSubsystem limelightSubsystem) {
        m_turretSubsystem = turretSubsystem;
        m_limelightSubsystem = limelightSubsystem;
        addRequirements(m_turretSubsystem, m_limelightSubsystem);
    }

    public VisionTarget getCurrentTarget() {
        return m_currentTarget;
    }

    public String getTargetStatus() {
        if (m_currentTarget == null) {
            return "";
        }
        if (m_turretSubsystem.isTargetLocked()) {
            return "LOCKED";
        } else {
            return "TARGETING";
        }
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);
        builder.addStringProperty("Target", this::getTargetStatus, null);
    }

    @Override
    public void initialize() {
        m_turretSubsystem.enableClosedLoop(this::getCurrentTarget);
    }

    @Override
    public void execute() {
        m_currentTarget =  m_limelightSubsystem.acquireTarget(m_targetHeight);
    }

    @Override
    public void end(boolean cancelled) {
        m_turretSubsystem.disableClosedLoop();
    }
}

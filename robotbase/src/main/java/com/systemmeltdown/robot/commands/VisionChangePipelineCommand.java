package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem.PipelineIndex;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Changes the camera pipeline (Which camera stream is being shown).
 * 
 * @category Camera
 */
public class VisionChangePipelineCommand extends CommandBase {
    private TogglableLimelightSubsystem m_visionSub;

    /**
     * @param visionSub The {@link TogglableLimelightSubsystem}.
     */
    public VisionChangePipelineCommand(TogglableLimelightSubsystem visionSub) {
        m_visionSub = visionSub;
        addRequirements(visionSub);
    }

    @Override
    public void execute() {
        m_visionSub.setPipeline(PipelineIndex.VISION_TARGET);
    }

    @Override
    public void end(boolean interrupted) {
        m_visionSub.setPipeline(PipelineIndex.HUMAN_VIEW);
    }
}
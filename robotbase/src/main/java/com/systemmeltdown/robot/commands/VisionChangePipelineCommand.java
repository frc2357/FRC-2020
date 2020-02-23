package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem.PipelineIndex;

/**
 * Changes the camera pipeline (Which camera stream is being shown).
 * 
 * @category Camera
 */
public class VisionChangePipelineCommand extends CommandLoggerBase {
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
        super.end(interrupted);
        m_visionSub.setPipeline(PipelineIndex.HUMAN_VIEW);
    }
}
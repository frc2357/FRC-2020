package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem.PipelineIndex;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionChangePipelineCommand extends CommandBase {
    private TogglableLimelightSubsystem m_visionSub;

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
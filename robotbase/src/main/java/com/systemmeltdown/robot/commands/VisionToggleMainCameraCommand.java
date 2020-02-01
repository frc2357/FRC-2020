package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class VisionToggleMainCameraCommand extends CommandBase {
    private TogglableLimelightSubsystem m_visionSub;

    public VisionToggleMainCameraCommand(TogglableLimelightSubsystem visionSub) {
        m_visionSub = visionSub;
        addRequirements(visionSub);
    }

    @Override
    public void execute() {
        m_visionSub.toggleStream();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
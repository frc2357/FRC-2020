package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class InvertDriveCommand extends CommandBase{
    private TogglableLimelightSubsystem m_visionSub;
    private InvertDriveControls m_controls;

    public InvertDriveCommand(TogglableLimelightSubsystem visionSub, InvertDriveControls controls) {
        m_controls = controls;
        m_visionSub = visionSub;
    }

    @Override
    public void initialize() {
        m_controls.invert();
        m_visionSub.toggleStream();
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
}
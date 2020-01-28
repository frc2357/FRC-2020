package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ControlPanelSub;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateControlPanelCommand extends CommandBase {
    private ControlPanelSub m_controlPanelSub;

    public RotateControlPanelCommand(ControlPanelSub controlPanelSub) {
        m_controlPanelSub = controlPanelSub;
        addRequirements(m_controlPanelSub);
    }

    @Override 
    public void initialize() {
        m_controlPanelSub.rotateControlPanel(3.5);
    }

    @Override
    public boolean isFinished() {
        return true;
    } 
}
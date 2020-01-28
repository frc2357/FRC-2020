package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ControlPanelSub;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetControlPanelColorCommand extends CommandBase {
    private ControlPanelSub m_controlPanelSub;

    public SetControlPanelColorCommand(ControlPanelSub controlPanelSub) {
        m_controlPanelSub = controlPanelSub;
        addRequirements(m_controlPanelSub);
    }

    @Override 
    public void initialize() {
        
    }

    @Override
    public boolean isFinished() {
        return true;
    } 
}
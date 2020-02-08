package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ControlPanelSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PivotControlPanelArmCommand extends CommandBase {
    private ControlPanelSubsystem m_controlPanelSub;

    public PivotControlPanelArmCommand(ControlPanelSubsystem controlPanelSub) {
        m_controlPanelSub = controlPanelSub;
        addRequirements(m_controlPanelSub);
    }

    @Override
    public void initialize() {
        m_controlPanelSub.changeExtenderPosition();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
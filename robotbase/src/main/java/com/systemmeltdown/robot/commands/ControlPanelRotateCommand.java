package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ControlPanelSub;

import edu.wpi.first.wpilibj2.command.CommandBase;


public class ControlPanelRotateCommand extends CommandBase {
    private ControlPanelSub m_controlPanelSub;

    public ControlPanelRotateCommand(ControlPanelSub controlPanelSub) {
        m_controlPanelSub = controlPanelSub;

        addRequirements(m_controlPanelSub);

    }

    @Override
    public void execute() {
        for(int i = 0; i < 30; i++) {
            m_controlPanelSub.rotate1Color();  
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ControlPanelSub;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PivotControlPanelArmCommand extends CommandBase {
    private ControlPanelSub m_controlPanelSub;

    /**
     * This command changes the position of the Control Panel Arm, so that the sensor is able to scan the
     * control panel's colors.
     * 
     * @param controlPanelSub The {@link ControlPanelSub}.
     */
    public PivotControlPanelArmCommand(ControlPanelSub controlPanelSub) {
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
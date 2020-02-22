package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ControlPanelSubsystem;


/**
 * This command changes the position of the Control Panel Arm, so that the arm is able to
 * interact with the control panel. 
 * 
 * @category Control Panel
 */
public class PivotControlPanelArmCommand extends CommandLoggerBase {
    private ControlPanelSubsystem m_controlPanelSub;

    /**
     * @param controlPanelSub The {@link ControlPanelSubsystem}.
     */
    public PivotControlPanelArmCommand(ControlPanelSubsystem controlPanelSub) {
        m_controlPanelSub = controlPanelSub;
        addRequirements(m_controlPanelSub);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_controlPanelSub.changeExtenderPosition();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
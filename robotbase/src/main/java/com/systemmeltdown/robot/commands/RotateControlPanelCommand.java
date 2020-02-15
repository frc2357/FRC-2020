package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ControlPanelSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Calls on a wheel/motor/whatever we decide on to rotate the control panel 3.5 times.
 * 
 * @category Control Panel
 */
public class RotateControlPanelCommand extends CommandBase {
    private ControlPanelSubsystem m_controlPanelSub;

    /**
     * @param controlPanelSub The {@link ControlPanelSubsystem}.
     */
    public RotateControlPanelCommand(ControlPanelSubsystem controlPanelSub) {
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
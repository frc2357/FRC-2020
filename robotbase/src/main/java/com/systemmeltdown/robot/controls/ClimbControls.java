package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robotlib.util.XboxRaw;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class ClimbControls {

    public final JoystickButton m_climbButton;
    XboxController m_controller;

    public ClimbControls(XboxController controller) {
        m_controller = controller;
        m_climbButton = new JoystickButton(controller, XboxRaw.TriggerRight.value);
    }

}
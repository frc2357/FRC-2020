package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robotlib.util.XboxRaw;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class GunnerControls {
    XboxController m_controller;

    public final JoystickButton m_shootButton;

    public GunnerControls(XboxController controller) {
        m_controller = controller;

        m_shootButton = new JoystickButton(controller, XboxRaw.A.value);
    }
}
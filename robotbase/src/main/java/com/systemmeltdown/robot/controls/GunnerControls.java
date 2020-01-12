package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robotlib.triggers.AxisThresholdTrigger;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class GunnerControls {
    XboxController m_controller;

    public AxisThresholdTrigger m_trigger;

    public GunnerControls(XboxController controller) {
        m_controller = controller;
        m_trigger = new AxisThresholdTrigger(controller, Hand.kRight, .1);
    }

    public double getTriggerValue(Hand hand) {
        return m_controller.getTriggerAxis(hand);
    }
}
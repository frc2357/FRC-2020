package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robotlib.triggers.AxisThresholdTrigger;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class GunnerControls {
    XboxController m_controller;

    public AxisThresholdTrigger m_rightTrigger;
    public AxisThresholdTrigger m_leftTrigger;

    public GunnerControls(XboxController controller) {
        m_controller = controller;
        m_rightTrigger = new AxisThresholdTrigger(controller, Hand.kRight, .1);
        m_leftTrigger = new AxisThresholdTrigger(controller, Hand.kLeft, .1);
    }

    public double getTriggerValue(Hand hand) {
        return m_controller.getTriggerAxis(hand);
    }
}
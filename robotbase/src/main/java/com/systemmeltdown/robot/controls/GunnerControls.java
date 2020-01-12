package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robotlib.triggers.AxisThresholdTrigger;
import com.systemmeltdown.robotlib.util.XboxRaw;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class GunnerControls {
    public XboxController m_controller;
    
    public AxisThresholdTrigger m_trigger;

    public GunnerControls(XboxController controller) {
        m_controller = controller;
        m_trigger = new AxisThresholdTrigger(controller, Hand.kRight, .1);
    }
}
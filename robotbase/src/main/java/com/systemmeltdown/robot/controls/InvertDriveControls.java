package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robotlib.controllers.DriverControls;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.systemmeltdown.robotlib.util.XboxRaw;

public class InvertDriveControls extends DriverControls {
    private final XboxController m_controller;
    private final double m_deadband;
    public final JoystickButton m_invertButton;
    private boolean m_isToggled = false;

    /**
     * Create driver controls
     * 
     * @param controller The Xbox Controller used for the driver
     * @param deadband   The deadband used for all drive axis output (typically 0.1
     *                   or less)
     */
    public InvertDriveControls(XboxController controller, double deadband) {
        super(controller, deadband);
        m_controller = controller;
        m_deadband = deadband;
        m_invertButton = new JoystickButton(controller, XboxRaw.Start.value);
    }

    /**
     * Changes the value of m_isToggled from true to false or vice versa
     */
    public void invert() {
        m_isToggled = !m_isToggled;
        System.out.println(m_isToggled);
    }
    
    @Override
    public double getSpeed() {
        if (m_isToggled) {
            return super.getSpeed();
        } else {
            return -super.getSpeed();
        }
    }
}
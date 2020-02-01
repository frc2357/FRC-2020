package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robotlib.controllers.DriverControls;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.systemmeltdown.robotlib.util.XboxRaw;

public class InvertDriveControls extends DriverControls {
    public final JoystickButton m_invertButton;
    private boolean m_isToggled = false;

    public InvertDriveControls(XboxController controller, double deadband) {
        super(controller, deadband);
        m_invertButton = new JoystickButton(controller, XboxRaw.A.value);
    }

    /**
     * Changes the value of m_isToggled from true to false or vice versa
     */
    public void invert() {
        m_isToggled = !m_isToggled;
    }
    
    @Override
    public double getSpeed() {
        double speed = super.getSpeed();
        speed = inputCurve(speed, 2);
        return m_isToggled ? speed : -speed;
    }

    @Override
    public double getTurn() {
        return -inputCurve(super.getTurn(), 3);
    }

    public double inputCurve(double input, int curveFactor) {
        return Math.signum(input) * Math.abs(Math.pow(input, curveFactor));
    }
}
package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robot.commands.InvertDriveCommand; //This import is used for the javadoc, sorry

import com.systemmeltdown.robotlib.controllers.DriverControls;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.systemmeltdown.robotlib.util.XboxRaw;

/**
 * These extend {@link DriverControls} so these are the Driver's controls, adapted to support the
 * {@link InvertDriveCommand}.
 * 
 * @category Drive
 */
public class InvertDriveControls extends DriverControls {
    public final JoystickButton m_invertButton;
    public final JoystickButton m_changePipelineButton;
    private boolean m_isToggled = false;

    /**
     * @param controller The driver's {@link XboxController}.
     * @param deadband The deadband for the driver's controller.
     */
    public InvertDriveControls(XboxController controller, double deadband) {
        super(controller, deadband);
        m_invertButton = new JoystickButton(controller, XboxRaw.A.value);
        m_changePipelineButton = new JoystickButton(controller, XboxRaw.Back.value);
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

    //I would put a javadoc here, but I really don't understand it. If you do, please delete this and put a javadoc here.
    public double inputCurve(double input, int curveFactor) {
        return Math.signum(input) * Math.abs(Math.pow(input, curveFactor));
    }
}
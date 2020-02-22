package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlog.topics.BooleanTopic;

/**
 * The subsystem responsible for dealing with the Control Panel.
 * 
 * @category Control Panel
 * @category Subsystems
 */
public class ControlPanelSubsystem extends ClosedLoopSubsystem {
    // color sensor
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private int m_clicksPerRotation;

    WPI_TalonSRX m_rotationTalon;
    Solenoid m_extenderSolenoid;
    boolean m_extenderPosition = false;

    /* RobotLog Topics */
    //private final StringTopic m_controlPanelErrorTopic = new StringTopic("Control Panel Sub Error");
    // /\ Unused /\
    private final BooleanTopic m_isExtended = new BooleanTopic("Extender Extended");

    /**
     * @param channel The channel on the PCM for the extender solenoid.
     * @param rotationTalonID
     */
    public ControlPanelSubsystem(int channel, int rotationTalonID) {
        m_rotationTalon = new WPI_TalonSRX(rotationTalonID);
        m_extenderSolenoid = new Solenoid(channel);
        m_isExtended.log(m_extenderPosition);
    }

    @Override
    public void periodic() {
        // nothing
    }

    /**
     * Carbon copy of IntakeSubsystem's changeArmPosition.
     */
    public void changeExtenderPosition() {
        m_extenderPosition = !m_extenderPosition;
        m_extenderSolenoid.set(m_extenderPosition);
        m_isExtended.log(m_extenderPosition);
    }

    /**
     * Rotates the control panel.
     * 
     * @param fullRotationsToDo The amount of times the control panel should be rotated.
     */
    public void rotateControlPanel(double fullRotationsToDo) {
        m_rotationTalon.set(ControlMode.Position, m_clicksPerRotation * fullRotationsToDo);
    }

    /**
     * Rotates to whatever color is given by FMS.
     * 
     * @param color The color given by FMS. Should be a String containing one of these letters:
     *              "R", "G", "B", or "Y".
     */
    public void rotateToColor(String color) {
        Color translatedColor = null;

        switch (color) {
            case "R": {
                translatedColor = Color.kRed;
                break;
            } 
            case "G": {
                translatedColor = Color.kGreen;
                break;
            } 
            case "B": {
                translatedColor = Color.kBlue;
                break;
            } 
            case "Y": {
                translatedColor = Color.kYellow;
                break;
            }
        }

        while (m_colorSensor.getColor() != translatedColor) {
            rotateControlPanel(0.125);
        }
    }

    //===================
    //     GETTERS
    //===================
    
    public int getRotations() {
        return m_clicksPerRotation / m_rotationTalon.getSelectedSensorPosition();
    }

    public Color getCurrentColor() {
        return m_colorSensor.getColor();
    }

    //===================
    //     SETTERS
    //===================
    
    public void setClicksPerRotation(int clicksPerRotation) {
        m_clicksPerRotation = clicksPerRotation;
    }
}

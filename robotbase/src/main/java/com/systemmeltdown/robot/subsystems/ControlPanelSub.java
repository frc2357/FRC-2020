package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;


public class ControlPanelSub extends ClosedLoopSubsystem {
    //color sensor
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    
    WPI_TalonSRX m_rotationTalon;
    Solenoid m_extenderSolenoid;
    boolean m_extenderPosition = false;
    private int m_clicksPerRotation;

    public ControlPanelSub(int channel, int rotationTalonID) {
        m_rotationTalon = new WPI_TalonSRX(rotationTalonID);
        m_extenderSolenoid = new Solenoid(channel);
    }

    @Override
    public void periodic() {
        // nothing
    }

    // carbon copy of IntakeSubs changeArmPosition.
    public void changeExtenderPosition() {
        if (m_extenderPosition) {
            m_extenderSolenoid.set(false);
            m_extenderPosition = false;
        } else {
            m_extenderSolenoid.set(true);
            m_extenderPosition = true;
        }
    }

    public void setClicksPerRotation(int clicksPerRotation) {
        m_clicksPerRotation = clicksPerRotation;
    }

    public int getRotations() {
        return  m_clicksPerRotation / m_rotationTalon.getSelectedSensorPosition();
    }

    public void rotateControlPanel(double fullRotationsToDo) {
        m_rotationTalon.set(ControlMode.Position, m_clicksPerRotation * fullRotationsToDo);
    }

    public Color getCurrentColor() {
        return m_colorSensor.getColor();
    }

    public void rotateToColor(String color) {
        Color translatedColor = null;

        switch(color) {
            case "R": {
                translatedColor = Color.kRed;
                break;

            } case "G": {
                translatedColor = Color.kGreen;
                break;

            } case "B": {
                translatedColor = Color.kBlue;
                break;

            } case "Y": {
                translatedColor = Color.kYellow;
                break;
            }
        }

        while(m_colorSensor.getColor() != translatedColor) {
            rotateControlPanel(0.125);
        }
    }

    @Override
    public boolean isClosedLoopEnabled() {
        return m_useClosedLoop;
    }

    @Override
    public void setClosedLoopEnabled(boolean ClosedLoopEnabled) {
        m_useClosedLoop = ClosedLoopEnabled;
    }
}
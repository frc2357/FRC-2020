package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanelSub extends SubsystemBase {
    //color sensor
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

    //carbon copy of IntakeSubs changeArmPosition.
    public void changeExtenderPosition() {
        if(m_extenderPosition) {
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
}
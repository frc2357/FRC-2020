package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.fasterxml.jackson.databind.util.ArrayBuilders.IntBuilder;
import com.systemmeltdown.robotlib.util.ClosedLoopSystem;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanelSub extends SubsystemBase implements ClosedLoopSystem {
    // color sensor
    boolean m_useClosedLoop;

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

    public void rotate1Color() {
        // while color hasn't changed
        // TODO: Replace with color sensor
        int i = 100;

        if (isClosedLoopEnabled()) {

            while (i > 1) {
                m_rotationTalon.set(ControlMode.PercentOutput, 0.5);

                i--;
            }

            m_rotationTalon.set(ControlMode.PercentOutput, 0.0);

        } else {
            rotateByClicks(4);
        }
    }

    public void setClicksPerRotation(int clicksPerRotation) {
        m_clicksPerRotation = clicksPerRotation;
    }

    public int getClickPerRotation() {
        return m_clicksPerRotation;
    }

    public int getRotations() {
        return m_clicksPerRotation / m_rotationTalon.getSelectedSensorPosition();
    }

    public void rotateByClicks(int clicks) {
        m_rotationTalon.set(ControlMode.Position, clicks);
    }

    public void rotateControlPanel(double fullRotationsToDo) {
        m_rotationTalon.set(ControlMode.Position, m_clicksPerRotation * fullRotationsToDo);
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
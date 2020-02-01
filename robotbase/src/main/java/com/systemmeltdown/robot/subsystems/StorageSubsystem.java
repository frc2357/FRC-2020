package com.systemmeltdown.robot.subsystems;

import com.systemmeltdown.robotlib.util.ClosedLoopSystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class StorageSubsystem extends SubsystemBase implements ClosedLoopSystem {
    private boolean m_useClosedLoop;
    private int m_numOfCells = 3;
    private DigitalInput m_feedSensor;

    public StorageSubsystem(DigitalInput feedSensor) {
        m_feedSensor = feedSensor;
    }

    public boolean isFeedSensorBlocked() {
        return m_feedSensor.get();
    }

    public void setNumOfCells(int numOfCells) {
        m_numOfCells = numOfCells;
    }
 
    public int getNumbOfCells() {
        return m_numOfCells;
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
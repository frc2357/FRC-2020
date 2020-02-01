package com.systemmeltdown.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

public class StorageSubsystem extends ClosedLoopSubsystem {
    private int m_numOfBalls = 3;
    private DigitalInput m_feedSensor;

    public StorageSubsystem(DigitalInput feedSensor) {
        m_feedSensor = feedSensor;
    }

    public boolean isFeedSensorBlocked() {
        return m_feedSensor.get();
    }

    public void setNumOfBalls(int numOfBalls) {
        m_numOfBalls = numOfBalls;
    }
 
    public int getNumbOfBalls() {
        return m_numOfBalls;
    }
}
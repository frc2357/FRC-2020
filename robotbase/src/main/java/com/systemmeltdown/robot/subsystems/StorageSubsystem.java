package com.systemmeltdown.robot.subsystems;

import com.systemmeltdown.robot.shuffleboard.CellNumberWidget; //Imported for the javadoc

import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The subsystem for the power cell storage.
 * 
 * @category Storage
 * @category Subsystems
 */
public class StorageSubsystem extends ClosedLoopSubsystem {
    private int m_numOfCells = 3;
    private DigitalInput m_feedSensor;

    /**
     * @param feedSensor The sensor mounted in the storage. This sensor is used to count the number of
     *                   power cells in the robot and should be the type of {@link DigitalInput}.
     */
    public StorageSubsystem(DigitalInput feedSensor) {
        m_feedSensor = feedSensor;
    }

    /**
     * TODO: Find out what values the feed sensor returns when it is blocked.
     * 
     * @return The feed sensor's status.
     */
    public boolean isFeedSensorBlocked() {
        return m_feedSensor.get();
    }

    /**
     * Sets the number of cells. This value will also be displayed on the {@link CellNumberWidget}.
     * @param numOfCells The value to set the number of cells to.
     */
    public void setNumOfCells(int numOfCells) {
        m_numOfCells = numOfCells;
    }
 
    public int getNumbOfCells() {
        return m_numOfCells;
    }
}
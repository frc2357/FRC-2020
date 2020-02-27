package com.systemmeltdown.robot.shuffleboard;

import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
 * A Shuffleboard widget that displays the amount of power cells in the robot.
 * 
 * @category Shuffleboard
 */
public class CellNumberWidget extends ShuffleboardWidget {
    private final StorageSubsystem m_storageSub;
    private static NetworkTableEntry m_cellNumWidget;

    /**
     * @param tabTitle The title of the tab the widget will be added to.
     *                 If it does not exist, it will be created automatically.
     * 
     * @param storageSub The {@link StorageSubsystem}.
     */
    public CellNumberWidget(String tabTitle, StorageSubsystem storageSub) {
        super(tabTitle);
        m_storageSub = storageSub;
        NetworkTableEntry cellNumWidget = Shuffleboard.getTab(tabTitle)
            .add("Num of Power Cells", 0)
            .withWidget(BuiltInWidgets.kTextView)
            .getEntry();

        m_cellNumWidget = cellNumWidget;
    }

    /**
     * Adds one to the power cell count.
     */
    public void addCell() {
        int numOfCells = m_storageSub.getNumOfCells();
        m_storageSub.setNumOfCells(++numOfCells);
        m_cellNumWidget.setNumber(numOfCells);
    }

    /**
    * Subtracts one from the power cell count.
    */
    public void subCell() {
        int numOfCells = m_storageSub.getNumOfCells();
        m_storageSub.setNumOfCells(--numOfCells);
        m_cellNumWidget.setNumber(numOfCells);
    }

    public void periodic() {
        // TODO:
    }
}
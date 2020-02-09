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
public class CellNumberWidget {
    private final StorageSubsystem m_storageSub;
    private static String m_tabTitle;
    private static NetworkTableEntry m_cellNumWidget;

    /**
     * @param tabTitle
     * @param storageSub
     */
    public CellNumberWidget(String tabTitle, StorageSubsystem storageSub) {
        m_storageSub = storageSub;
        NetworkTableEntry cellNumWidget = Shuffleboard.getTab(tabTitle)
            .add("Num of Power Cells", 0)
            .withWidget(BuiltInWidgets.kTextView)
            .getEntry();

        m_cellNumWidget = cellNumWidget;
        m_tabTitle = tabTitle;
    }

    /**
     * Adds one to the power cell count.
     */
    public void addBall() {
        int numOfCells = m_storageSub.getNumbOfCells();
        m_storageSub.setNumOfCells(++numOfCells);
        m_cellNumWidget.setNumber(numOfCells);
    }

    /**
    * Subtracts one from the power cell count.
    */
    public void subBall() {
        int numOfCells = m_storageSub.getNumbOfCells();
        m_storageSub.setNumOfCells(--numOfCells);
        m_cellNumWidget.setNumber(numOfCells);
    }

    public static void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }

    public void periodic() {
    }
}
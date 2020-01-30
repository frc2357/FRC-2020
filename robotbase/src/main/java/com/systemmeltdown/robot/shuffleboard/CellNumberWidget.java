package com.systemmeltdown.robot.shuffleboard;

import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
* A Shuffleboard widget that displays the amount of power cells in the robot
* 
* @param tabTitle Title of the tab you want to add the widget to
*/
public class CellNumberWidget {
    private final StorageSubsystem m_storageSub;
    private static String m_tabTitle;
    private static NetworkTableEntry m_cellNumWidget;

    
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
        int numOfBalls = m_storageSub.getNumbOfBalls();
        m_storageSub.setNumOfBalls(++numOfBalls);
        m_cellNumWidget.setNumber(numOfBalls);
    }

    /**
    * Subtracts one from the power cell count.
    */
    public void subBall() {
        int numOfBalls = m_storageSub.getNumbOfBalls();
        m_storageSub.setNumOfBalls(--numOfBalls);
        m_cellNumWidget.setNumber(numOfBalls);
    }

    public static void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }

    public void periodic() {
    }
}
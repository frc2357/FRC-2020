package com.systemmeltdown.robot.shuffleboard;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.util.Color;

/**
* A Shuffleboard widget that displays the amount of power cells in the robot
* 
* @param tabTitle Title of the tab you want to add the widget to
*/
public class CellNumberWidget {
    private static final String TITLE = "Num of Power Cells";
    private static int m_cellNum = 0;
    private static NetworkTableEntry m_cellNumWidget;

    
    public CellNumberWidget(String tabTitle) {
        NetworkTableEntry cellNumWidget = Shuffleboard.getTab(tabTitle)
            .add("Num of Cells", 0)
            .withWidget(BuiltInWidgets.kTextView)
            .getEntry();

        m_cellNumWidget = cellNumWidget;
  }

  /**
   * Adds one to the power cell count.
   */
  public void addBall() {
      m_cellNum++;
      m_cellNumWidget.setNumber(m_cellNum);
  }

  /**
   * Subtracts one from the power cell count.
   */
  public void subBall() {
      m_cellNum--;
      m_cellNumWidget.setNumber(m_cellNum);
  }

  /**
   * @return int: Amount of Power Cells in the robot.
   */
  public int getBallCount() {
      return m_cellNum;
  }

  public static void show() {
    Shuffleboard.selectTab(TITLE);
  }

  public void periodic() {
  }
}
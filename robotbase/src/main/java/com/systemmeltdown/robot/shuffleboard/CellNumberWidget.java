package com.systemmeltdown.robot.shuffleboard;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

class CellNumberWidget {
    private static final String TITLE = "Num of Power Cells";
    int m_cellNum = 0;

    /**
     * A Shuffleboard widget that displays the amount of power cells in the robot
     * 
     * @param tabTitle Title of the tab you want to add the widget to
     */
    public CellNumberWidget(String tabTitle) {
        NetworkTableEntry cellNumWidget = Shuffleboard.getTab(tabTitle)
            .add("Num of Cells", 0)
            .withWidget(BuiltInWidgets.kTextView)
            .getEntry();
  }

  public void show() {
    Shuffleboard.selectTab(TITLE);
  }

  public void periodic() {
  }
}
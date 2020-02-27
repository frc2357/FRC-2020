package com.systemmeltdown.robot.shuffleboard;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public abstract class ShuffleboardWidget {
  private final String m_tabTitle;

  protected ShuffleboardWidget(String tabTitle) {
    m_tabTitle = tabTitle;
  }

  public void show() {
      Shuffleboard.selectTab(m_tabTitle);
  }

  public void periodic() {
  }
}
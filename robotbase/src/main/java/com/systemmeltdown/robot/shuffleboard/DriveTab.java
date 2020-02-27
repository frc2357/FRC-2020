package com.systemmeltdown.robot.shuffleboard;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class DriveTab {
  private static final String TITLE = "Drive";

  private ShuffleboardTab tab;
  private List<ShuffleboardWidget> widgets;

  public DriveTab() {
    tab = Shuffleboard.getTab(TITLE);
    widgets = new ArrayList<ShuffleboardWidget>();
  }

  public void addWidget(ShuffleboardWidget widget) {
    widgets.add(widget);
  }

  public void show() {
    Shuffleboard.selectTab(TITLE);
  }

  public void periodic() {
    for (ShuffleboardWidget widget : widgets) {
      widget.periodic();
    }
  }
}

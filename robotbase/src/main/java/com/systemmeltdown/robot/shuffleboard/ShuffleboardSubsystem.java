package com.systemmeltdown.robot.shuffleboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShuffleboardSubsystem extends SubsystemBase {
  private DriveTab driveTab = new DriveTab();

  public void drive() {
    driveTab.show();
  }

  @Override
  public void periodic() {
    driveTab.periodic();
  }
}

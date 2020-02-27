package com.systemmeltdown.robot.shuffleboard;

import com.systemmeltdown.robot.subsystems.TurretSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class TargetingWidget extends ShuffleboardWidget {
    private TurretSubsystem m_turretSubsystem;
    private static NetworkTableEntry m_widget;

    public TargetingWidget(String tabTitle, TurretSubsystem turretSubsystem) {
        super(tabTitle);
        m_turretSubsystem = turretSubsystem;
        NetworkTableEntry widget = Shuffleboard.getTab(tabTitle)
            .add("Target", 0)
            .withWidget(BuiltInWidgets.kTextView)
            .getEntry();

        m_widget = widget;
    }

    @Override
    public void periodic() {
      if (m_turretSubsystem.isTargetLocked()) {
        m_widget.setString("LOCKED");
      } else if (m_turretSubsystem.hasTarget()) {
        m_widget.setString("TARGETING");
      } else {
        m_widget.setString("");
      }
    }
}

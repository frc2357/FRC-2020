package com.systemmeltdown.robot.subsystems;

import com.systemmeltdown.robotlib.subsystems.LimelightSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;

public class TogglableLimelightSubsystem extends LimelightSubsystem {
    private NetworkTableEntry m_stream = super.m_Table.getEntry("stream");
    
    public TogglableLimelightSubsystem(boolean isLimelightPrimary) {
        setStream(isLimelightPrimary);
    }

    public void setStream(boolean isLimelightPrimary) {
        m_stream.setValue(isLimelightPrimary ? 1 : 2);
    }

    public void toggleStream() {
        setStream(m_stream.getDouble(1.0) != 1.0);
    }
}
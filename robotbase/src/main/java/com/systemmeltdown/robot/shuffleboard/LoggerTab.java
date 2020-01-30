package com.systemmeltdown.robot.shuffleboard;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class LoggerTab {
    private ShuffleboardTab m_loggerTab;

    public LoggerTab() {
        ShuffleboardTab m_loggerTab = Shuffleboard.getTab("Logger");
    }
}
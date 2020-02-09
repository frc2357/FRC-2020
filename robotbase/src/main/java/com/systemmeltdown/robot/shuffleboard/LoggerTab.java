package com.systemmeltdown.robot.shuffleboard;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * NOTE: This is still functional, but will be removed when something gets put on it. This is because
 * a tab will be created if a widget is created referencing a tab that does not exist, so there is no reason
 * to create a class unless there is some issue with creating a defualt tab.
 * 
 * A shuffleboard tab for logging.
 */
public class LoggerTab {
    private ShuffleboardTab m_loggerTab;

    public LoggerTab() {
        ShuffleboardTab m_loggerTab = Shuffleboard.getTab("Logger");
    }
}
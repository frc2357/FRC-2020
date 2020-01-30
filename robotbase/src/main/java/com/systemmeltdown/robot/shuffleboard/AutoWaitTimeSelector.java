package com.systemmeltdown.robot.shuffleboard;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
* A Shuffleboard widget that displays the amount of power cells in the robot
* 
* @param tabTitle Title of the tab you want to add the widget to
*/
public class AutoWaitTimeSelector {
    private static final String TITLE = "Wait Time";
    private static String m_tabTitle;
    private static double m_waitTime = 0;
    private static NetworkTableEntry m_timeSelectorWidget;

    
    public AutoWaitTimeSelector(String tabTitle, int index) {
        NetworkTableEntry timeSelctorWidget = Shuffleboard.getTab(tabTitle)
            .add(TITLE + index, 0)
            .withWidget(BuiltInWidgets.kTextView)
            .getEntry();

        m_timeSelectorWidget = timeSelctorWidget;
        m_tabTitle = tabTitle;
    }

  

   /**
    * 
    * @return Amount of time to wait before next Command.
    */
    public double getWaitTime() {
        return m_waitTime;
    }

    public static void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }

    public void periodic() {
    }
}
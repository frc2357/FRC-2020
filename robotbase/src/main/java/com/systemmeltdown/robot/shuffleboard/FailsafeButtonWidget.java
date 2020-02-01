package com.systemmeltdown.robot.shuffleboard;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

class FailsafeButtonWidget {
    private final String m_tabTitle;
    private final NetworkTableEntry m_failsafeButton;

    /**
     * A failsafe button.
     * 
     * @param tabTitle Title of the tab you want to add this widget to. 
     * Tab will be created if it does not exist already.
     * 
     * @param title Title displayed on shuffleboard
     */
    public FailsafeButtonWidget(String tabTitle, String title) {
        NetworkTableEntry failsafeButton = Shuffleboard.getTab(tabTitle)
            .add(title, false)
            .withWidget(BuiltInWidgets.kToggleButton)
            .getEntry();

        failsafeButton.setBoolean(false);
        m_failsafeButton = failsafeButton;
        m_tabTitle = tabTitle;
    }

    /**
     * A failsafe button.
     * 
     * @param tabTitle Title of the tab you want to add this widget to. 
     * Tab will be created if it does not exist already.
     */
    public FailsafeButtonWidget(String tabTitle) {
        NetworkTableEntry failsafeButton = Shuffleboard.getTab(tabTitle)
            .add("Unnamed Failsafe Button", false)
            .withWidget(BuiltInWidgets.kToggleButton)
            .getEntry();

        failsafeButton.setBoolean(false);
        m_failsafeButton = failsafeButton;
        m_tabTitle = tabTitle;
    }

    /** 
     * Checks if button has been pressed. This function should be called periodically.
     * If it has, this function will return true then set the button back to false.
     * 
     * @return True if button has been pressed, false if not.
     */
    public boolean checkButtonPressed() {
        if (m_failsafeButton.getBoolean(false)) {
            m_failsafeButton.setBoolean(false);
            return true;
        } else {
            return false;
        }
    }

    public void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }
}
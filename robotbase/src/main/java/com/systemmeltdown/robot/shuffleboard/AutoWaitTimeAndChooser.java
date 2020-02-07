package com.systemmeltdown.robot.shuffleboard;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * A Shuffleboard widget that allows editing of wait time and Automode actions
 * 
 * @param tabTitle Title of the tab you want to add the widget to
 */
public class AutoWaitTimeAndChooser {
    private static String m_tabTitle;
    public NetworkTableEntry m_autoTimeSelectorWidget;
    public SendableChooser<AutomodeActions> m_chooser;

    public AutoWaitTimeAndChooser(String tabTitle, int index) {
        // Adds Chooser Widget
        ShuffleboardTab tab = Shuffleboard.getTab(tabTitle);
        m_chooser = new SendableChooser<>();

        //Sets options for chooser widget
        m_chooser.setDefaultOption("Shoot", AutomodeActions.SHOOT);
        m_chooser.addOption("Pickup From Trench", AutomodeActions.PICKUP_FROM_TRENCH);
        m_chooser.addOption("Pickup From Shield", AutomodeActions.PICKUP_FROM_SHEILD);
        m_chooser.addOption("None", AutomodeActions.NONE);
        tab.add("Automode Chooser" + index, m_chooser);

        // Adds Wait Time Widget
        NetworkTableEntry autoTimeSelectorWidget = Shuffleboard.getTab(tabTitle)
            .add("Wait Time" + index, 0)
            .withWidget(BuiltInWidgets.kTextView).getEntry();

        m_autoTimeSelectorWidget = autoTimeSelectorWidget;

        m_tabTitle = tabTitle;
    }

    /**
     * 
     * @return Amount of time to wait before next Command.
     */

    public static void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }
}
package com.systemmeltdown.robot.shuffleboard;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
* A Shuffleboard widget that allows the drive team to choose what automode they want
* 
* @param tabTitle Title of the tab you want to add the widget to
*/
public class AutomodeChooserWidget {
    private static final String m_TITLE = "Automode Chooser";
    private static String m_tabTitle;
    //Use .addOption(String, COMMAND), with "String" being the name of the dropdown selection.
    private static SendableChooser<AutomodeActions> m_chooser;
    private static SendableChooser<AutomodeActions> m_chooser2;
    private static final String m_title2 = "Automode Chooser 2";

    public AutomodeChooserWidget(String tabTitle) {
        ShuffleboardTab tab = Shuffleboard.getTab(tabTitle);
        m_chooser = new SendableChooser<>();
        m_chooser.setDefaultOption("Shoot", AutomodeActions.NONE);
        m_chooser.addOption("Pickup From Trench", AutomodeActions.PICKUP_FROM_TRENCH);
        m_chooser.addOption("Pickup From Shield", AutomodeActions.PICKUP_FROM_SHEILD);
        m_chooser.addOption("None", AutomodeActions.SHOOT);
        
        tab.add(m_TITLE, m_chooser);
        m_tabTitle = tabTitle;
    }


    public static void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }

    public void periodic() {
    }    
}
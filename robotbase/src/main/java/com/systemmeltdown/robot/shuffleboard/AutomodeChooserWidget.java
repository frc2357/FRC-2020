package com.systemmeltdown.robot.shuffleboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
* A Shuffleboard widget that allows the drive team to choose what automode they want
* 
* @param tabTitle Title of the tab you want to add the widget to
*/
public class AutomodeChooserWidget {
    private static final String TITLE = "Automode Chooser";
    //Use .addOption(String, COMMAND), with "String" being the name of the dropdown selection.
    private static SendableChooser<AutomodeActions> m_chooser;
    private static NetworkTable m_automodeChooser;

    public AutomodeChooserWidget(String tabTitle) {
        ShuffleboardTab tab = Shuffleboard.getTab(tabTitle);
        m_chooser = new SendableChooser<>();
        m_chooser.setDefaultOption("Shoot", AutomodeActions.SHOOT);
        m_chooser.addOption("Pickup From Trench", AutomodeActions.PICKUP_FROM_TRENCH);
        m_chooser.addOption("Pickup From Shield", AutomodeActions.PICKUP_FROM_SHEILD);
        m_chooser.addOption("None", AutomodeActions.NONE);

        tab.add("Automode Chooser", m_chooser);
    }

    public static void show() {
        Shuffleboard.selectTab(TITLE);
    }

    public void periodic() {
    }    
}
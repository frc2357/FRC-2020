package com.systemmeltdown.robot.shuffleboard;

import com.systemmeltdown.robot.commands.TrackTargetCommand;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * A Shuffleboard widget that allows editing of wait time and Automode actions.
 * 
 * @category Shuffleboard
 */
public class AutoWaitTimeAndChooser {
    private static String m_tabTitle;
    public NetworkTableEntry m_autoTimeSelectorWidget;
    public SendableChooser<AutomodeActions> m_chooser;
    private TurretSubsystem m_turretSubsystem;
    private TogglableLimelightSubsystem m_limelightSubsystem;

    /**
     * @param tabTitle The title of the tab the widget should be added to.
     * @param index    The index of the Wait Time widget, since there are more than
     *                 one being created.
     */
    public AutoWaitTimeAndChooser(TogglableLimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem, String tabTitle, int index) {
        // Adds Chooser Widget
        ShuffleboardTab tab = Shuffleboard.getTab(tabTitle);
        m_chooser = new SendableChooser<>();

        // Sets options for chooser widget
        m_chooser.setDefaultOption("Shoot", AutomodeActions.SHOOT);
        m_chooser.addOption("Pickup From Trench", AutomodeActions.PICKUP_FROM_TRENCH);
        m_chooser.addOption("Pickup From Shield", AutomodeActions.PICKUP_FROM_SHEILD);
        m_chooser.addOption("None", AutomodeActions.NONE);
        tab.add("Automode Chooser" + index, m_chooser);

        // Adds Wait Time Widget
        NetworkTableEntry autoTimeSelectorWidget = Shuffleboard.getTab(tabTitle).add("Wait Time" + index, 0)
                .withWidget(BuiltInWidgets.kTextView).getEntry();

        m_autoTimeSelectorWidget = autoTimeSelectorWidget;

        m_tabTitle = tabTitle;

        m_turretSubsystem = turretSubsystem;
        m_limelightSubsystem = limelightSubsystem;
    }

    public static void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }

    public SequentialCommandGroup getChosenCommand() {
        SequentialCommandGroup returnGroup = new SequentialCommandGroup(new WaitCommand(m_autoTimeSelectorWidget.getDouble(0.0)));
        switch (m_chooser.getSelected()) {
            case SHOOT: {
                returnGroup.addCommands(new TrackTargetCommand(m_turretSubsystem, m_limelightSubsystem, true));
                // returnGroup.addCommands(Kevin's shoot command);

                break;
            }
            case PICKUP_FROM_SHEILD: {
                // returnGroup.addCommands(PICKUP_FROM_SHEILD Command);
                break;
            }
            case PICKUP_FROM_TRENCH: {
                // returnGroup.addCommands(PICKUP_FROM_TRENCH Command);
                break;
            }
            case BACKUP: {
                // returnGroup.addCommands(BACKUP Command);
                break;
            }
            case NONE: {
                
                break;
            }
        }

        return returnGroup;
    }
}
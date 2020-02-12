package com.systemmeltdown.robot.shuffleboard;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
 * A Shuffleboard widget that displays the amount of power cells in the robot.
 * 
 * @category Shuffleboard
 */
public class CellNumberWidget {
    private final StorageSubsystem m_storageSub;
    private static String m_tabTitle;
    private static NetworkTableEntry m_cellNumWidget;
    private static IntakeSubsystem m_intakeSub;

    /**
     * @param tabTitle The title of the tab the widget will be added to.
     *                 If it does not exist, it will be created automatically.
     * 
     * @param storageSub The {@link StorageSubsystem}.
     * @param intakeSub  The {@link IntakeSubsystem}. This is needed to get the
     *                   number of cells in the intake from the sensors.
     */
    public CellNumberWidget(String tabTitle, StorageSubsystem storageSub, IntakeSubsystem intakeSub) {
        m_storageSub = storageSub;
        NetworkTableEntry cellNumWidget = Shuffleboard.getTab(tabTitle)
            .add("Num of Power Cells", 0)
            .withWidget(BuiltInWidgets.kTextView)
            .getEntry();

        m_cellNumWidget = cellNumWidget;
        m_tabTitle = tabTitle;
        m_intakeSub = intakeSub;
    }

    /**
     * Adds one to the power cell count.
     */
    public void addBall() {
        int numOfCells = m_storageSub.getNumOfCells();
        m_storageSub.setNumOfCells(++numOfCells);
        m_cellNumWidget.setNumber(numOfCells);
    }

    /**
     * Subtracts one from the power cell count.
     */
    public void subBall() {
        int numOfCells = m_storageSub.getNumOfCells();
        m_storageSub.setNumOfCells(--numOfCells);
        m_cellNumWidget.setNumber(numOfCells);
    }

    /**
     * This function will check the number that it gets from the sensor with
     * the number that it gets from the widget. Currently, since we only have
     * the intake sensor and not a storage sensor, 
     */
    public void checkCount() {
        int intakeNum = m_intakeSub.getCount();
        int storageNum= m_storageSub.getNumOfCells();

        //intakeNum will be -1 if the arduino is not connected. Otherwise, who knows.

        if (!(intakeNum < 0)) {
            m_cellNumWidget.setNumber(intakeNum + storageNum);
            
        } else if (intakeNum < 0) {
            System.out.println("ERROR IN CellNumberWidget: intakeNum is less than 0.");
            System.out.print(  "This probably means that the arduino is not connected.");
        }
    }

    public static void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }

    public void periodic() {
        checkCount();
    }
}
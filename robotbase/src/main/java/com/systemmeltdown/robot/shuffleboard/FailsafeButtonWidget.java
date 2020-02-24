package com.systemmeltdown.robot.shuffleboard;

import com.systemmeltdown.robot.commands.FailsafeCommand;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlib.triggers.ToggleTrigger;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
 * A failsafe button.
 * 
 * @category Failsafe
 * @category Shuffleboard
 */
public class FailsafeButtonWidget {
    private final String m_tabTitle;
    private final NetworkTableEntry m_failsafeButton;
    private ToggleTrigger failsafeTrigger;

    /**
     * @param tabTitle       Title of the tab you want to add this widget to. Tab will
     *                       be created if it does not exist already.
     * 
     * @param subsystems     All of the subsystems. Needs these so the button can call
     *                       the {@link FailsafeCommand} on all of the subsystems.
     * 
     * @param gunnerControls The {@link GunnerControls}. Needed for the {@link FailsafeCommand}.
     */
    public FailsafeButtonWidget(String tabTitle, ClosedLoopSubsystem[] subsystems, 
                                GunnerControls gunnerControls) {
        m_failsafeButton = Shuffleboard.getTab(tabTitle)
            .add("FAILSAFE", false)
            .withWidget(BuiltInWidgets.kToggleButton)
            .getEntry();

        failsafeTrigger = new ToggleTrigger(m_failsafeButton);
        failsafeTrigger.whenActive(new FailsafeCommand(true, subsystems, gunnerControls));
        failsafeTrigger.whenInactive(new FailsafeCommand(false, subsystems, gunnerControls));
        m_tabTitle = tabTitle;
    }

    public void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }
}
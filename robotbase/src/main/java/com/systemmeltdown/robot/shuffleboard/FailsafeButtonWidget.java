package com.systemmeltdown.robot.shuffleboard;

import java.util.List;

import com.systemmeltdown.robot.commands.FailsafeCommand;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlib.triggers.ToggleTrigger;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class FailsafeButtonWidget {
    private final String m_tabTitle;
    private final NetworkTableEntry m_failsafeButton;
    private ToggleTrigger failsafeTrigger;

    /**
     * A failsafe button.
     * 
     * @param tabTitle Title of the tab you want to add this widget to. 
     * Tab will be created if it does not exist already.
     * 
     * @param subsystems All of the subsystems. Needs these so the button can call the {@link FailsafeCommand}
     * on all of the subsystems.
     * IMPORTANT: TO PUT SUBSYSTEMS INTO THE PARAMETER, YOU MUST PUT IT INTO AN ARRAY, NOT A LIST.
     */
    public FailsafeButtonWidget(String tabTitle, ClosedLoopSubsystem[] subsystems) {
        m_failsafeButton = Shuffleboard.getTab(tabTitle)
            .add("FAILSAFE", false)
            .withWidget(BuiltInWidgets.kToggleButton)
            .getEntry();

        failsafeTrigger = new ToggleTrigger(m_failsafeButton);
        failsafeTrigger.whenActive(new FailsafeCommand(true, subsystems));
        failsafeTrigger.whenInactive(new FailsafeCommand(false, subsystems));
        m_tabTitle = tabTitle;
    }
    
    public void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }
}
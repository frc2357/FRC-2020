package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ControlPanelSub;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Calls on a wheel/motor/whatever we decide on to rotate the control panel to the color given by FMS.
 * If a color has not been given, then this command will simply print a line to the console.
 * 
 * @category Control Panel
 */
public class SetControlPanelColorCommand extends CommandBase {
    private ControlPanelSub m_controlPanelSub;

    /**
     * @param controlPanelSub The {@link ControlPanelSub}.
     */
    public SetControlPanelColorCommand(ControlPanelSub controlPanelSub) {
        m_controlPanelSub = controlPanelSub;
        addRequirements(m_controlPanelSub);
    }

    // Format can be found at https://docs.wpilib.org/en/latest/docs/software/wpilib-overview/2020-Game-Data.html
    @Override
    public void execute() {
        String color = DriverStation.getInstance().getGameSpecificMessage();

        switch (color) {
            case "R": case "G": case "B": case "Y": {
                 m_controlPanelSub.rotateToColor(color);
                break;
            }
            case "": {
                System.out.println("Color not given by FMS");
                break;
            }
            default: {
                System.out.println("Color not found");
            }
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    } 
}
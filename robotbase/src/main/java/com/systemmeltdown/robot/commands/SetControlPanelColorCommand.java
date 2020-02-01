package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ControlPanelSub;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetControlPanelColorCommand extends CommandBase {
    private ControlPanelSub m_controlPanelSub;

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
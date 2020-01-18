package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.InvertDriveControls;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class InvertDriveCommand extends CommandBase{
    private InvertDriveControls m_controls;

    public InvertDriveCommand(InvertDriveControls controls) {
        m_controls = controls;
    }

    

    @Override
    public void initialize() {
        m_controls.invert();
    }
    
    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return true;
    }
}
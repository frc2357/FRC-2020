package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSub;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PivotIntakeCommand extends CommandBase {
    private IntakeSub m_intakeSubsystem;

    public PivotIntakeCommand(IntakeSub intakeSubsystem) {
        m_intakeSubsystem = intakeSubsystem;
        addRequirements(m_intakeSubsystem);
    }

    
    @Override 
    public void initialize() {
        m_intakeSubsystem.changeArmPosition();
    }

    @Override
    public boolean isFinished() {
        return true;
    } 
}
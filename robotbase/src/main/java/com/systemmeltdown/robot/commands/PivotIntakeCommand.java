package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSub;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Moves the intake by calling changeArmPosition on the {@link IntakeSub}.
 * 
 * @category Intake
 */
public class PivotIntakeCommand extends CommandBase {
    private IntakeSub m_intakeSubsystem;

    /**
     * @param intakeSubsystem The {@link IntakeSub}.
     */
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
package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Moves the intake by calling changeArmPosition on the {@link IntakeSubsystem}.
 * 
 * @category Intake
 */
public class PivotIntakeCommand extends CommandBase {
    private IntakeSubsystem m_intakeSubsystem;

    /**
     * @param intakeSubsystem The {@link IntakeSubsystem}.
     */
    public PivotIntakeCommand(IntakeSubsystem intakeSubsystem) {
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
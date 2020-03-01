package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;


/**
 * Moves the intake by calling changeArmPosition on the {@link IntakeSubsystem}.
 * 
 * @category Intake
 */
public class IntakeSetPivotCommand extends CommandLoggerBase {
    private IntakeSubsystem m_intakeSubsystem;
    private DoubleSolenoid.Value m_value;

    /**
     * @param intakeSubsystem The {@link IntakeSubsystem}.
     */
    public IntakeSetPivotCommand(IntakeSubsystem intakeSubsystem, DoubleSolenoid.Value value) {
        m_intakeSubsystem = intakeSubsystem;
        m_value = value;
        addRequirements(m_intakeSubsystem);
    }

    
    @Override 
    public void initialize() {
        super.initialize();
        m_intakeSubsystem.setPivot(m_value);
    }

    @Override
    public boolean isFinished() {
        return true;
    } 
}
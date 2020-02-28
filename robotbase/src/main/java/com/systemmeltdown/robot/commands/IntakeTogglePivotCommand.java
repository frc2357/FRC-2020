package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


/**
 * Moves the intake by calling changeArmPosition on the {@link IntakeSubsystem}.
 * 
 * @category Intake
 */
public class IntakeTogglePivotCommand extends CommandLoggerBase {
    private IntakeSubsystem m_intakeSubsystem;

    /**
     * @param intakeSubsystem The {@link IntakeSubsystem}.
     */
    public IntakeTogglePivotCommand(IntakeSubsystem intakeSubsystem) {
        m_intakeSubsystem = intakeSubsystem;
        addRequirements(m_intakeSubsystem);
    }
    
    @Override 
    public void initialize() {
        super.initialize();
        Value currentPosition = m_intakeSubsystem.getPivot();
        Value nextPosition = currentPosition == Value.kReverse ? Value.kForward : Value.kReverse;
        m_intakeSubsystem.setPivot(nextPosition);
    }

    @Override
    public boolean isFinished() {
        return true;
    } 
}
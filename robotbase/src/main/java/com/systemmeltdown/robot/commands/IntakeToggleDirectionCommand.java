package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;

/**
 * When the {@link InvertDriveCommand} is called, this command should be called. When it is,
 * this command will call {@link IntakeSub.toggleRollDirection()} on the intake subsystem.
 * 
 * @category Intake
 */
public class IntakeToggleDirectionCommand extends CommandLoggerBase {
    private IntakeSubsystem m_intakeSub;

    /**
     * @param intakeSub The {@link IntakeSubsystem}.
     */
    public IntakeToggleDirectionCommand(IntakeSubsystem intakeSub) {
        m_intakeSub = intakeSub;
        addRequirements(intakeSub);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_intakeSub.toggleRollDirection();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;

public class IntakePickupWithoutController extends CommandLoggerBase {
    private IntakeSubsystem m_intakeSub;

    /**
     * @param intakeSub      The {@link IntakeSubsystem}.
     */
    public IntakePickupWithoutController(IntakeSubsystem intakeSub) {
        m_intakeSub = intakeSub;
        addRequirements(m_intakeSub);
    }
    
    @Override
    public void execute() {
        m_intakeSub.triggerIntakeRoller(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_intakeSub.triggerIntakeRoller(0.0);
    }
}
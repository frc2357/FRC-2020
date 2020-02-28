package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;

/**
 * This command brings a cell into the intake from the ground.
 * 
 * @category Intake
 */
public class IntakePickupCellCommand extends CommandLoggerBase {
    private IntakeSubsystem m_intakeSub;
    private double m_speed;

    /**
     * @param intakeSub      The {@link IntakeSubsystem}.
     * @param gunnerControls The {@link GunnerControls}.
     */
    public IntakePickupCellCommand(IntakeSubsystem intakeSub, double speed) {
        m_intakeSub = intakeSub;
        m_speed = speed;
        addRequirements(m_intakeSub);
    }
    
    @Override
    public void initialize() {
        super.initialize();
        m_intakeSub.triggerIntakeRoller(m_speed);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_intakeSub.triggerIntakeRoller(0.0);
    }
}
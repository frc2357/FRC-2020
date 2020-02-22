package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * This command brings a cell into the intake from the ground.
 * 
 * @category Intake
 */
public class IntakePickupCellCommand extends CommandLoggerBase {
    private IntakeSubsystem m_intakeSub;
    private GunnerControls m_gunnerControls;

    /**
     * @param intakeSub      The {@link IntakeSubsystem}.
     * @param gunnerControls The {@link GunnerControls}.
     */
    public IntakePickupCellCommand(IntakeSubsystem intakeSub, GunnerControls gunnerControls) {
        m_intakeSub = intakeSub;
        m_gunnerControls = gunnerControls;
        addRequirements(m_intakeSub);
    }
    
    @Override
    public void execute() {
        super.execute();
        m_intakeSub.triggerIntakeRoller(m_gunnerControls.getTriggerValue(Hand.kLeft));
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_intakeSub.triggerIntakeRoller(0.0);
    }
}
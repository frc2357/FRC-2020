package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.IntakeSub;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command brings a ball into the intake from the ground.
 */
public class IntakePickupBallCommand extends CommandBase {
    private IntakeSub m_intakeSub;
    private GunnerControls m_gunnerControls;

    public IntakePickupBallCommand(IntakeSub intakeSub, GunnerControls gunnerControls) {
        m_intakeSub = intakeSub;
        m_gunnerControls = gunnerControls;
        addRequirements(m_intakeSub);
    }
    
    @Override
    public void execute() {
        m_intakeSub.triggerIntakeRoller(m_gunnerControls.getTriggerValue(Hand.kLeft));
    }

    @Override
    public void end(boolean interupted) {
        m_intakeSub.triggerIntakeRoller(0.0);
    }
}
package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * This command brings a cell into the intake from the ground.
 * 
 * @category Intake
 */
public class IntakePickupCellCommand extends CommandBase {
    private IntakeSubsystem m_intakeSub;
    private StorageSubsystem m_storageSub;
    private GunnerControls m_gunnerControls;

    /**
     * @param intakeSub      The {@link IntakeSubsystem}.
     * @param storageSub     The {@link StorageSubsystem}.
     * @param gunnerControls The {@link GunnerControls}.
     */
    public IntakePickupCellCommand(IntakeSubsystem intakeSub, StorageSubsystem storageSub,
     GunnerControls gunnerControls) {
        m_intakeSub = intakeSub;
        m_storageSub = storageSub;
        m_gunnerControls = gunnerControls;
        addRequirements(m_intakeSub);
    }
    
    @Override
    public void execute() {
        m_intakeSub.triggerIntakeRoller(m_gunnerControls.getTriggerValue(Hand.kLeft));
        m_storageSub.setRotationSpeed(Constants.STORAGE_CAROUSEL_ROTATION_SPEED);
    }

    @Override
    public void end(boolean interupted) {
        m_intakeSub.triggerIntakeRoller(0.0);
    }
}
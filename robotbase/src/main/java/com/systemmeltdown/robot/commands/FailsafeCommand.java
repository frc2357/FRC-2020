package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The failsafe: Kill all commands. For Safety ;D
 * 
 * @category Failsafe
 */
public class FailsafeCommand extends CommandLoggerBase {
    private boolean m_failsafeActive;
    private ClosedLoopSubsystem[] m_subsystems;
    private GunnerControls m_gunnerControls;
    
    //Subsystems for rebinding buttons
    private StorageSubsystem m_storageSubsystem = null;
    private IntakeSubsystem  m_intakeSub = null;

    /**
     * @param failsafeActive Pass in true if failsafe should be active, false if not.
     * @param subsystems All Subsystems you wish to trigger this failsafe command on.
     */
    public FailsafeCommand(boolean failsafeActive, ClosedLoopSubsystem[] subsystems,
                           GunnerControls gunnerControls) {
        
        for (ClosedLoopSubsystem subsystem : subsystems) {
            if (subsystem.getClass() == StorageSubsystem.class) {
                m_storageSubsystem = (StorageSubsystem) subsystem;
            }

            if (subsystem.getClass() == IntakeSubsystem.class) {
                m_intakeSub = (IntakeSubsystem) subsystem;
            }
        }

        m_failsafeActive = failsafeActive;
        m_subsystems = subsystems;
        m_gunnerControls = gunnerControls;
    }

    /**
     * If failsafe is active, rebinds the controls to failsafe controls.
     * 
     * @param isFailsafeActive If true, will rebind controls to failsafe controls. If false, will
     *                         rebind controls to default controls.
     */
    private void rebindControls(boolean isFailsafeActive) {
        // Failsafe is active, so rebind buttons to failsafe buttons
        if (isFailsafeActive) {
            //Rebind X
            if (m_storageSubsystem != null) {
                m_gunnerControls.m_xButton.whenHeld(new RotateStorageContinuous(m_storageSubsystem));
            } else {
                System.out.println("Could not rebind X to failsafe command, "
                + "since StorageSubsystem was not found");
            }

            //Put other rebindings down here (WITHIN THIS SCOPE)

            // Failsafe is not active, so rebind buttons to default bindings
        } else {
            //Rebind X
            if (m_intakeSub != null) {
                m_gunnerControls.m_xButton.whenPressed(new PivotIntakeCommand(m_intakeSub));
            } else {
                System.out.println("Could not rebind X to default command, "
                + "since IntakeSubsystem was not found");
            }

            //More default bindings down here (WITHIN THIS SCOPE)
        }
    }

    @Override
    public void initialize() {
        super.initialize();

        //Set all subsystems in array to the value of m_failsafeActive
        for (ClosedLoopSubsystem subsystem : m_subsystems) {
            subsystem.setClosedLoopEnabled(!m_failsafeActive);
        }

        rebindControls(m_failsafeActive);

        //Kill them. Kill them all.
        CommandScheduler.getInstance().cancelAll();
    }
  
    @Override
    public boolean isFinished() {
        return true;
    }
  }
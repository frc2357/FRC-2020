package com.systemmeltdown.robot.commands;

import java.util.ArrayList;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The failsafe: Kill all commands. For Safety ;D
 * 
 * @category Failsafe
 */
public class FailsafeCommand extends CommandBase {
    private boolean m_failsafeActive;
    private ClosedLoopSubsystem[] m_subsystems;

    /**
     * @param failsafeActive Pass in true if failsafe should be active, false if not.
     * @param subsystems All Subsystems you wish to trigger this failsafe command on.
     */
    public FailsafeCommand(boolean failsafeActive, ClosedLoopSubsystem[] subsystems) {
        m_failsafeActive = failsafeActive;
        m_subsystems = subsystems;
    }

    @Override
    public void initialize() {
        for (ClosedLoopSubsystem subsystem : m_subsystems) {
            subsystem.setClosedLoopEnabled(m_failsafeActive);
        }
        CommandScheduler.getInstance().cancelAll();
    }
  
    @Override
    public boolean isFinished() {
        return true;
    }
  }
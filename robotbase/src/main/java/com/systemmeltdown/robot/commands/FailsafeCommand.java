package com.systemmeltdown.robot.commands;

import java.util.ArrayList;
import java.util.List;

import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The failsafe: Kill all commands. For Safety ;D
 */
public class FailsafeCommand extends CommandBase {
    private boolean m_failsafeActive;
    private List<ClosedLoopSubsystem> m_subsystems = new ArrayList<>();

    /**
     * @param failsafeActive Pass in true if failsafe should be active, false if not.
     * @param subsystems All Subsystems you wish to trigger this failsafe command on.
     * IMPORTANT: TO PUT SUBSYSTEMS INTO THE PARAMETER, YOU MUST PUT IT INTO AN ARRAY, NOT A LIST.
     */
    public FailsafeCommand(boolean failsafeActive, ClosedLoopSubsystem[] subsystems) {
        m_failsafeActive = failsafeActive;
        for (int i = 0; i < subsystems.length; i++) {
            m_subsystems.add(subsystems[i]);
        }
    }

    @Override
    public void initialize() {
        for (int i = 0; i < m_subsystems.size(); i++) {
            m_subsystems.get(i).setClosedLoopEnabled(m_failsafeActive);
        }
        CommandScheduler.getInstance().cancelAll();
    }
  
    @Override
    public boolean isFinished() {
        return true;
    }
  }
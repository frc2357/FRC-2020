package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

/**
 * This command climbs upward with the winch.
 * 
 * @category Climb
 */
public class ClimbUpCommand extends CommandLoggerBase {
    private ClimbSubsystem m_climbSubsystem;

    /**
     * @param climbSubsystem The {@link ClimbSubsystem}.
     */
    public ClimbUpCommand(ClimbSubsystem climbSubsystem) {
        m_climbSubsystem = climbSubsystem;
        addRequirements(m_climbSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_climbSubsystem.climb();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_climbSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
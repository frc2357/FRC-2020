package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

/**
 * This command raises the robot in the climb while maintaining current level.
 * 
 * @category Climb
 */
public class ClimbLevelCommand extends CommandLoggerBase {
    private ClimbSubsystem m_climbSubsystem;

    /**
     * @param climbSubsystem The {@link ClimbSubsystem}.
     */
    public ClimbLevelCommand(ClimbSubsystem climbSubsystem) {
        m_climbSubsystem = climbSubsystem;
        addRequirements(m_climbSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_climbSubsystem.setKeepLevel(true);
        m_climbSubsystem.climbUp();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_climbSubsystem.stopClimb();
        m_climbSubsystem.setKeepLevel(false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

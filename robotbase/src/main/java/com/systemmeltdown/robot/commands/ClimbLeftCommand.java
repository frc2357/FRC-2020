package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

/**
 * This command climbs toward the left side of the robot.
 * 
 * @category Climb
 */
public class ClimbLeftCommand extends CommandLoggerBase {
    private ClimbSubsystem m_climbSubsystem;

    /**
     * @param climbSubsystem The {@link ClimbSubsystem}.
     */
    public ClimbLeftCommand(ClimbSubsystem climbSubsystem) {
        m_climbSubsystem = climbSubsystem;
        addRequirements(m_climbSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        System.out.println("left");
        m_climbSubsystem.setKeepLevel(true);
        m_climbSubsystem.climbLeft();
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
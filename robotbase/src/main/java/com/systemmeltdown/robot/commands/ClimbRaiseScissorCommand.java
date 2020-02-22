package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

/**
 * This command raises the scissor lift for climb.
 * 
 * This will start the scissor extension when initialized, then
 * stop the extension when the command ends.
 * 
 * @category Climb
 */
public class ClimbRaiseScissorCommand extends CommandLoggerBase {
    private ClimbSubsystem m_climbSubsystem;

    /**
     * @param climbSubsystem The {@link ClimbSubsystem}.
     */
    public ClimbRaiseScissorCommand(ClimbSubsystem climbSubsystem) {
        m_climbSubsystem = climbSubsystem;
        addRequirements(m_climbSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_climbSubsystem.extendScissor();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_climbSubsystem.releaseScissor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
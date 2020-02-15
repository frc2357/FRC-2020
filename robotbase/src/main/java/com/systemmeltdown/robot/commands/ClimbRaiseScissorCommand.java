package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command raises the scissor lift for climb.
 * 
 * This will start the scissor extension when initialized, then
 * stop the extension when the command ends.
 * 
 * @category Climb
 */
public class ClimbRaiseScissorCommand extends CommandBase {
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
        m_climbSubsystem.extendScissor();
    }

    @Override
    public void end(boolean interrupted) {
        m_climbSubsystem.releaseScissor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
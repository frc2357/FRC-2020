package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command climbs toward the left side of the robot.
 */
public class ClimbLeftCommand extends CommandBase {
    private ClimbSubsystem m_climbSubsystem;

    public ClimbLeftCommand(ClimbSubsystem climbSubsystem) {
        m_climbSubsystem = climbSubsystem;
        addRequirements(m_climbSubsystem);
    }

    @Override
    public void initialize() {
        m_climbSubsystem.setKeepLevel(true);
        m_climbSubsystem.climbLeft();
    }

    @Override
    public void end(boolean interrupted) {
        m_climbSubsystem.stopClimb();
        m_climbSubsystem.setKeepLevel(false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
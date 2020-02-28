package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;

/**
 * This command runs the motor that feeds the power cell from the carousel to the shooter.
 * 
 * @category Intake
 */
public class FeedToShooterCommand extends CommandLoggerBase {
    private FeederSubsystem m_feederSubsystem;

    /**
     * @param feederSubsystem The {@link FeederSubsystem}.
     */
    public FeedToShooterCommand(FeederSubsystem feederSubsystem) {
        m_feederSubsystem = feederSubsystem;
        addRequirements(m_feederSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_feederSubsystem.runFeederMotor(1.0);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_feederSubsystem.runFeederMotor(0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
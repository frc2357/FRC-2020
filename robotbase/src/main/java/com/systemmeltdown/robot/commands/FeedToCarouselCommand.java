package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;

import frc.robot.Constants;

/**
 * This command runs the motor that feeds the power cell from the carousel to the shooter.
 * 
 * @category Intake
 */
public class FeedToCarouselCommand extends CommandLoggerBase {
    private FeederSubsystem m_feederSubsystem;

    /**
     * @param feederSubsystem The {@link FeederSubsystem}.
     */
    public FeedToCarouselCommand(FeederSubsystem feederSubsystem) {
        m_feederSubsystem = feederSubsystem;
        addRequirements(m_feederSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_feederSubsystem.runFeederMotor(Constants.FEEDER_MOTOR_BACKWARD_SPEED);
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
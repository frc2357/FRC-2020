package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * This command runs the motor that feeds the power cell from the carousel to the shooter.
 * 
 * @category Intake
 */
public class InputToShooterCommand extends CommandBase {
    private FeederSubsystem m_feederSubsystem;

    /**
     * @param feederSubsystem The {@link FeederSubsystem}.
     */
    public InputToShooterCommand(FeederSubsystem feederSubsystem) {
        m_feederSubsystem = feederSubsystem;
        addRequirements(m_feederSubsystem);
    }

    @Override
    public void execute() {
        m_feederSubsystem.runFeederMotor(1.0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
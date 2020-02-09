package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSub;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * When the {@link InvertDriveCommand} is called, this command should be called. When it is,
 * this command will call {@link IntakeSub.toggleRollDirection()} on the intake sub.
 * 
 * @category Intake
 */
public class IntakeToggleDirectionCommand extends CommandBase {
    private IntakeSub m_intakeSub;

    /**
     * @param intakeSub The {@link IntakeSub}.
     */
    public IntakeToggleDirectionCommand(IntakeSub intakeSub) {
        m_intakeSub = intakeSub;
        addRequirements(intakeSub);
    }

    @Override
    public void initialize() {
        m_intakeSub.toggleRollDirection();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
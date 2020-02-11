package com.systemmeltdown.robot.commands;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.controls.GunnerControls;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Auto command that shoots power cells into the low goal. Will continue to shoot until the button that
 * calls this command is let go of. NOTE: This command is incomplete, as there is no code telling the
 * hood to move to the low goal.
 * 
 * @category Turret
 */
public class ShootLowGoal extends CommandBase {
    private ShooterSubsystem m_shooterSubsystem;
    private GunnerControls m_gunnerControls;

    /**
     * @param shootersub The {@link ShooterSubsystem}.
     * @param gunnerControls The {@link GunnerControls}.
     */
    public ShootLowGoal (ShooterSubsystem shootersub, GunnerControls gunnerControls) {
        m_shooterSubsystem = shootersub;
        m_gunnerControls = gunnerControls;
        addRequirements(m_shooterSubsystem);
    }

    @Override
    public void execute() {
        //Add the code to move the hood to the low goal shooting position.
        //There is no reflective tape on the low goal for the camera to see so we will need
        //to set the hood.
        m_shooterSubsystem.runMotor(m_gunnerControls.getTriggerValue(Hand.kRight));
    }

    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.runMotor(0.0);
    }
}

package com.systemmeltdown.robot.commands;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.controls.GunnerControls;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootLowGoal extends CommandBase {
    private ShooterSubsystem m_shooterSubsystem;
    private GunnerControls m_gunnerControls;

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
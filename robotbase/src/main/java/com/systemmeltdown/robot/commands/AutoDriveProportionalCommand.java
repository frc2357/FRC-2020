package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robotlib.subsystems.drive.SkidSteerDriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoDriveProportionalCommand extends CommandBase {
  private SkidSteerDriveSubsystem m_driveSub;
  private double m_speed;
  private double m_turn;

  public AutoDriveProportionalCommand(SkidSteerDriveSubsystem driveSub, double speed, double turn) {
    m_driveSub = driveSub;
    addRequirements(driveSub);
  }

  @Override
  public void initialize() {
    super.initialize();
    m_driveSub.driveProportional(m_speed, m_turn);
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    m_driveSub.driveProportional(0, 0);
  }

  @Override
  public boolean isFinished() {
      return false;
  }
}

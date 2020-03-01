package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousShootCommand extends SequentialCommandGroup {
  public AutonomousShootCommand(
    StorageSubsystem storageSubsystem,
    TurretSubsystem turretSubsystem,
    FeederSubsystem feederSubsystem,
    ShooterSubsystem shooterSubsystem,
    TogglableLimelightSubsystem limelightSubsystem,
    int shooterSpeed,
    double shootSeconds
  ) {
    addCommands(
      new TrackTargetCommand(turretSubsystem, limelightSubsystem, true),
      new ShootCommandGroup(
        storageSubsystem,
        feederSubsystem,
        shooterSubsystem,
        limelightSubsystem,
        shooterSpeed,
        shootSeconds)
    );
  }
}

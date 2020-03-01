package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class ShooterVisionCommandGroup extends ParallelRaceGroup {
    public ShooterVisionCommandGroup(
        final TurretSubsystem turretSub,
        final StorageSubsystem storageSub,
        final FeederSubsystem feederSub,
        final ShooterSubsystem shooterSub,
        final TogglableLimelightSubsystem visionSub
    ) {
        this(turretSub, storageSub, feederSub, shooterSub, visionSub, -1);
    }

    public ShooterVisionCommandGroup(
        final TurretSubsystem turretSub,
        final StorageSubsystem storageSub,
        final FeederSubsystem feederSub,
        final ShooterSubsystem shooterSub,
        final TogglableLimelightSubsystem visionSub,
        final double shootSeconds
    ) {
        addCommands(
            new TrackTargetCommand(turretSub, visionSub),
            new VisionChangePipelineCommand(visionSub),
            new ShooterVisionCommand(shooterSub, visionSub),
            new SequentialCommandGroup(
                new WaitCommand(Constants.FEEDER_SHOOT_DELAY),
                new WaitForFeederToClearCommand(feederSub),
                new FeedToShooterCommand(feederSub)
            ),
            new SequentialCommandGroup(
                new WaitCommand(Constants.CAROUSEL_SHOOT_DELAY),
                new RotateStorageContinuous(storageSub, Constants.STORAGE_CAROUSEL_SHOOTER_ROTATION_SPEED, false)
            )
        );

        if (shootSeconds > 0) {
            addCommands(new WaitCommand(shootSeconds));
        }
    }
}

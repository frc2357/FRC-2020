package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class AutoShootCommand extends ParallelCommandGroup {
    public AutoShootCommand(
        final StorageSubsystem storageSub,
        final FeederSubsystem feederSub,
        final ShooterSubsystem shooterSub
    ) {
        addCommands(
            new ShootCommand(shooterSub),
            new SequentialCommandGroup(
                new WaitCommand(Constants.CAROUSEL_SHOOT_DELAY),
                new RotateStorageContinuous(storageSub, Constants.STORAGE_CAROUSEL_SHOOTER_ROTATION_SPEED)
            ),
            new SequentialCommandGroup(
                new WaitCommand(Constants.FEEDER_SHOOT_DELAY),
                new FeedToShooterCommand(feederSub)
            )
        );
    }
}

package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoShoot extends SequentialCommandGroup {
    public class RotateBetween extends CommandBase {
        StorageSubsystem m_storageSubsystem;
        public RotateBetween(StorageSubsystem storageSubsystem) {
            m_storageSubsystem = storageSubsystem;
            
            addRequirements(storageSubsystem);
        }

        @Override
        public boolean isFinished() {
            return(m_storageSubsystem.getEncoderValue() == 0.1);
        }
    }

    public AutoShoot(StorageSubsystem storageSub, FeederSubsystem feederSub, ShooterSubsystem shooterSub, double rotateSpeed) {
        ParallelCommandGroup startUpCommandGroup = new ParallelCommandGroup(
            new SequentialCommandGroup(
                new ParallelDeadlineGroup(new RotateBetween(storageSub), new RotateStorageContinuous(storageSub, rotateSpeed)),
                new FeedToShooterCommand(feederSub)
            ),
            new ShootCommand(shooterSub)
        );
        
        ParallelCommandGroup fireCommandGroup = new ParallelCommandGroup(
            new FeedToShooterCommand(feederSub),
            new ShootCommand(shooterSub),
            new RotateStorageContinuous(storageSub, rotateSpeed)
        );

        addCommands(startUpCommandGroup, fireCommandGroup);        
    }
}

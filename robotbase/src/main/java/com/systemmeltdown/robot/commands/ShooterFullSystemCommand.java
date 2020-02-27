package com.systemmeltdown.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;

public class ShooterFullSystemCommand extends ParallelCommandGroup {

    public ShooterFullSystemCommand(StorageSubsystem storageSub, FeederSubsystem feederSub, ShooterSubsystem shooterSub, GunnerControls gunnerControls, double storageSpeed) {
        addCommands(new ParallelDeadlineGroup(
                    new ShootVariableCommand(shooterSub, gunnerControls),
                    new SequentialCommandGroup(
                        new WaitCommand(1),
                        new ParallelRaceGroup(
                            new FeedToShooterCommand(feederSub),
                            new RotateStorageContinuous(storageSub, storageSpeed))
        )));     
    }

}
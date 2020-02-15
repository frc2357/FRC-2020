package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * A parallel command group that runs the intake pickup ball command and the rotate carousel command
 * at the same time so that the balls can be put into the carousel.
 */
class PickupCellsAndRotateCarousel extends ParallelCommandGroup {

    /**
     * @param intakeSub      The {@link IntakeSubsystem}.
     * @param gunnerControls The {@link GunnerControls}.
     * @param storageSub     The {@link StorageSubsystem}.
     */
    public PickupCellsAndRotateCarousel(IntakeSubsystem intakeSub, GunnerControls gunnerControls,
                                        StorageSubsystem storageSub) {
        addCommands(
            new IntakePickupBallCommand(intakeSub, gunnerControls),

            new RotateStorageContinuous(storageSub)
        );
    }
}
package com.systemmeltdown.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;

public class IntakePickupCellsParallelCommand extends ParallelCommandGroup {
    public IntakePickupCellsParallelCommand(
        IntakeSubsystem intakeSub,
        StorageSubsystem storageSub,
        GunnerControls gunnerControls
    )
    {
        addCommands(
            new IntakePickupCellCommand(intakeSub, Constants.INTAKE_ROTATION_SPEED),
            new RotateStorageContinuous(storageSub, Constants.STORAGE_CAROUSEL_INTAKE_ROTATION_SPEED)
        );
    }    
}

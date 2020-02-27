package com.systemmeltdown.robot.commands;


import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;


public class IntakePickupCellsParallelCommand extends ParallelRaceGroup {

    public IntakePickupCellsParallelCommand(IntakeSubsystem intakeSub, StorageSubsystem storageSub,GunnerControls gunnerControls, double storageSpeed)
    {
        addCommands(
            new IntakePickupCellCommand(intakeSub, gunnerControls),
            new RotateStorageContinuous(storageSub, storageSpeed)
            );
    }    
}
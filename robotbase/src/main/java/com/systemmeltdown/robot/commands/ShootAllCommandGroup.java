package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * A parallel command group that rotates the carousel, feeds power cells to the shooter,
 * and shoots the power cells until the robot is empty (of power cells).
 * 
 * @category Turret/Shooting
 */
class ShootAllCommandGroup extends ParallelCommandGroup {

    /**
     * @param storageSub     The {@link StorageSubsystem}.
     * @param intakeSub      The {@link IntakeSubsystem}.
     * @param feederSub      The {@link FeederSubsystem}.
     * @param shootSub       The {@link ShooterSubsystem}.
     * @param gunnerControls The {@link GunnerControls}.
     */
    public ShootAllCommandGroup(StorageSubsystem storageSub, IntakeSubsystem intakeSub,
                                FeederSubsystem  feederSub, ShooterSubsystem shootSub,
                                GunnerControls gunnerControls) {

        while (storageSub.getNumOfCells() != 0 && intakeSub.getCount() != 0) {
            addCommands(

                new RotateStorageSingleCell(storageSub),

                new InputToShooterCommand(feederSub),

                new ShootCommand(shootSub, gunnerControls)

            );
        }
    }
}
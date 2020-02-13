package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;

/**
 * This command turns the storage carousel one revolution and counts the cells.
 * This will set the cell count in the storage system when it's finished.
 * 
 * @category Shuffleboard
 */
public class RecountCellsCommand extends SequentialCommandGroup {
    private int m_cellCount;
    private StorageSubsystem m_storageSubsystem;

    /**
     * @param storageSub The {@link StorageSubsystem}.
     */
    public RecountCellsCommand(StorageSubsystem storageSubsystem, FeederSubsystem feederSubsystem) {
        m_storageSubsystem = storageSubsystem;

        Command nextCell = new RotateStorageSingleCell(m_storageSubsystem);
        Command countCell = new ConditionalCommand(
            new InstantCommand(() -> ++m_cellCount),
            new InstantCommand(() -> {}),
            feederSubsystem::isFeedSensorBlocked);

        // NOTE: The command group might throw an error because we're reusing these commands
        // If that happens just move the command creation into the loop.
        for(int ii = 0; ii < Constants.STORAGE_CAROUSEL_SEGMENTS; ++ii) {
            addCommands(nextCell, countCell);
        }
    }

    @Override
    public void initialize() {
        super.initialize();
        m_cellCount = 0;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_storageSubsystem.setNumOfCells(m_cellCount);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.addDoubleProperty("cellCount", () -> m_cellCount, null);
    }
}
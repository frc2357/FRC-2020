package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.commands.RotateStorageSingleCell.StorageState;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * This command turns the storage carousel one revolution and counts the cells.
 * This will set the cell count in the storage system when it's finished.
 * 
 * @category Shuffleboard
 */
public class RecountCellsCommand extends CommandBase {
    public enum StorageState {
        onCell, betweenCells
    }

    private StorageSubsystem m_storageSubsystem;
    private FeederSubsystem m_feederSub;
    private int m_cellCount = 0;
    private int m_numOfPasses = 0;
    private StorageState m_storageState;

    /**
     * @param storageSub The {@link StorageSubsystem}.
     */
    public RecountCellsCommand(StorageSubsystem storageSubsystem, FeederSubsystem feederSubsystem) {
        m_storageSubsystem = storageSubsystem;
        m_feederSub = feederSubsystem;
        addRequirements(storageSubsystem, feederSubsystem);
    }

    @Override
    public void initialize() {
        if (m_feederSub.isFeedSensorBlocked()) {
            m_storageState = StorageState.onCell;
        } else {
            m_storageState = StorageState.betweenCells;
        }
    }

    @Override
    public void execute() {
        m_storageSubsystem.setRotationSpeed(Constants.STORAGE_CAROUSEL_ROTATION_SPEED);
        if (m_feederSub.isFeedSensorBlocked()) {
            if (m_storageState == StorageState.betweenCells) {
                m_storageState = StorageState.onCell;
            }
        } else {
            if (m_storageState == StorageState.onCell) {
                m_storageState = StorageState.betweenCells;
                m_cellCount++;
            }
        }
        // if (m_storageSubsystem.isAlignedForShooting()) {
        //     m_numOfPasses++;
        // }
    }

    @Override
    public void end(boolean interrupted) {
        m_storageSubsystem.setNumOfCells(m_cellCount);
    }

    @Override
    public boolean isFinished() {
        return (m_numOfPasses >= 5);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.addDoubleProperty("cellCount", () -> m_cellCount, null);
    }
}
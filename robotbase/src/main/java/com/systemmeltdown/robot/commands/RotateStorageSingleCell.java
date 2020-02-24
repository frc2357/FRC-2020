package com.systemmeltdown.robot.commands;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;

import frc.robot.Constants;

/*
 * This command will rotate the carousel by one cell
 * 
 * @category Storage
 */
public class RotateStorageSingleCell extends CommandLoggerBase {
    public enum StorageState {
        onPreviousCell, betweenCells, onNextCell
    }

    private StorageSubsystem m_storageSubsystem;
    private FeederSubsystem m_feederSub;
    private StorageState m_storageState = StorageState.onPreviousCell;

    /**
     * @param storageSubsystem The {@link StorageSubsystem}.
     */
    public RotateStorageSingleCell(StorageSubsystem storageSubsystem, FeederSubsystem feederSub) {
        m_storageSubsystem = storageSubsystem;
        m_feederSub = feederSub;
        addRequirements(m_storageSubsystem);
    }

    @Override
    public void execute() {
        m_storageSubsystem.setRotationSpeed(Constants.STORAGE_CAROUSEL_ROTATION_SPEED);

        if (m_storageState == StorageState.onPreviousCell) {
            if (!m_feederSub.isFeedSensorBlocked()) {
                m_storageState = StorageState.betweenCells;
            }
        } else if (m_storageState == StorageState.betweenCells) {
            if (m_feederSub.isFeedSensorBlocked()) {
                m_storageState = StorageState.onNextCell;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_storageState = StorageState.onPreviousCell;
        m_storageSubsystem.setRotationSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return (m_storageState == StorageState.onNextCell);
    }
}

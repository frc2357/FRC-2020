package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.StorageSubsystem;

import frc.robot.Constants;

/*
 * This command will rotate the storage carousel continuously
 * 
 * @category Storage
 */
public class RotateStorageContinuous extends CommandLoggerBase {
    private StorageSubsystem m_storageSubsystem;

    /**
     * @param storageSubsystem The {@link StorageSubsystem}.
     */
    public RotateStorageContinuous(StorageSubsystem storageSubsystem) {
        m_storageSubsystem = storageSubsystem;
        addRequirements(m_storageSubsystem);
    }

    @Override
    public void execute() {
        super.execute();
        m_storageSubsystem.setRotationSpeed(Constants.STORAGE_CAROUSEL_ROTATION_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_storageSubsystem.setRotationSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

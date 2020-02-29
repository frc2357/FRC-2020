package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.StorageSubsystem;
/*
 * This command will rotate the storage carousel continuously
 * 
 * @category Storage
 */
public class RotateStorageContinuous extends CommandLoggerBase {
    private StorageSubsystem m_storageSubsystem;
    private double m_rotationSpeed;
    private boolean m_settleMode;

    /**
     * @param storageSubsystem The {@link StorageSubsystem}.
     */
    public RotateStorageContinuous(StorageSubsystem storageSubsystem, double rotationSpeed, boolean settleMode) {
        m_storageSubsystem = storageSubsystem;
        m_rotationSpeed = rotationSpeed;
        m_settleMode = settleMode;
        addRequirements(m_storageSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
        m_storageSubsystem.setSettleMode(m_settleMode);
        m_storageSubsystem.setRotationSpeed(m_rotationSpeed);
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

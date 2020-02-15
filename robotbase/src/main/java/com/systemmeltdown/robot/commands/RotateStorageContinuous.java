package com.systemmeltdown.robot.commands;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/*
 * This command will rotate the storage carousel continuously (until interrupted).
 * 
 * @category Storage
 */
public class RotateStorageContinuous extends CommandBase {
    private StorageSubsystem m_storageSubsystem;

    /**
     * @param storageSubsystem The {@link StorageSubsystem}.
     */
    public RotateStorageContinuous(StorageSubsystem storageSubsystem) {
        m_storageSubsystem = storageSubsystem;
        addRequirements(m_storageSubsystem);
    }

    @Override
    public void initialize() {
        m_storageSubsystem.setRotationSpeed(Constants.STORAGE_CAROUSEL_ROTATION_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        m_storageSubsystem.setRotationSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

package com.systemmeltdown.robot.commands;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/*
 * This command will rotate the carousel one cell for single cell shooting
 * 
 * @category Storage
 */
public class RotateStorageSingleCell extends CommandBase {
    private StorageSubsystem m_storageSubsystem;

    /**
     * @param storageSubsystem The {@link StorageSubsystem}.
     */
    public RotateStorageSingleCell (StorageSubsystem storageSubsystem) {
        m_storageSubsystem = storageSubsystem;
        addRequirements(m_storageSubsystem);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

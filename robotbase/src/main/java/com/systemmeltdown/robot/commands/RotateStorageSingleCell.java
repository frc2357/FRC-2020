package com.systemmeltdown.robot.commands;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/*
 * This command will rotate the carousel by one cell
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
    public void execute() {
        m_storageSubsystem.setRotationSpeed(Constants.STORAGE_CAROUSEL_ROTATION_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        m_storageSubsystem.setRotationSpeed(0);
    }

    @Override
    public boolean isFinished() {
        System.out.println(m_storageSubsystem.getEncoderValue());
        if (Math.abs(m_storageSubsystem.getEncoderValue()) < .015) {
            System.out.println("Easy Exit");
            return false;
        }
        return m_storageSubsystem.isAlignedForShooting();
    }
}

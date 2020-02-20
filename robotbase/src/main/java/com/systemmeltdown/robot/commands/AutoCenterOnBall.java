package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * A command that should automatically run at the beginning of every match.
 * This command will rotate the carousel until a ball is detected by the 
 * infrared sensor in the storage. When a ball is detected, the Encoder in
 * the storage will be reset so that it centers on the point the carousel
 * was stopped at.
 */
class AutoCenterOnBall extends CommandBase {
    private DigitalInput m_infraredSensor;
    private StorageSubsystem m_storageSub;

    /**
     * @param storageSub The {@link storageSub}. Needed so this command can rotate
     *                   the carousel.
     * 
     * @param port       The port the infrared sensor in the storage is plugged into.
     */
    public AutoCenterOnBall(StorageSubsystem storageSub, int port) {
        m_infraredSensor = new DigitalInput(port);

        m_storageSub = storageSub;
    }

    @Override
    public void initialize() {
        m_storageSub.setRotationSpeed(Constants.STORAGE_CAROUSEL_ROTATION_SPEED);
        boolean ballDetected = m_infraredSensor.get();

        while (!ballDetected) {/*Nothing*/}

        m_storageSub.setRotationSpeed(0.0);
        m_storageSub.resetEncoderPos();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
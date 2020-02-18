package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem.Configuration;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

class AutoCenterOnBall extends CommandBase {
    // I don't know what port the sensor is being plugged into, so I have to do this for now.
    private DigitalInput m_infraredSensor;
    
    private StorageSubsystem m_storageSub;

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
}
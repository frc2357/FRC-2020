package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class StorageSubsystem extends ClosedLoopSubsystem {
    private int m_numOfCells = 3;

    /** This sensor will be activated when the carousel is in feeding position */
    private DigitalInput m_alignmentSensor;

    private WPI_TalonSRX m_rotateMotor;

    public StorageSubsystem(DigitalInput alignmentSensor, WPI_TalonSRX rotateMotor) {
        m_alignmentSensor = alignmentSensor;
        m_rotateMotor = rotateMotor;

        addChild("alignmentSensor", m_alignmentSensor);
        addChild("rotationMotor", m_rotateMotor);

        /* TODO set the rotation motor encoder to not continuous, configure PID on the Talon for position control, and use that to advance the carousel one cell at a time */
        /*
        // setup example
        m_rotateMotor.configFeedbackNotContinuous(true, 2000);
        m_rotateMotor.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.QuadEncoder, 0, 2000);
        m_rotateMotor.config_kP(0, 1);

        // position example, assuming the encoder has 4096 ticks per rotation
        m_rotateMotor.set(ControlMode.Position, (m_rotateMotor.getSelectedSensorPosition() + (4096/Constants.STORAGE_CAROUSEL_SEGMENTS)) % 4096);
        */
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.addDoubleProperty(
            ".cellCount",
            this::getNumOfCells,
            (double value) -> setNumOfCells((int)value));
    }  

    public boolean isAlignedForShooting() {
        return m_alignmentSensor.get();
    }

    /** Set the rotation motor percent output */
    public void setRotationSpeed(double speed) {
        m_rotateMotor.set(ControlMode.PercentOutput, speed);
    }

    public void setNumOfCells(int numOfCells) {
        m_numOfCells = numOfCells;
    }
 
    public int getNumOfCells() {
        return m_numOfCells;
    }
}
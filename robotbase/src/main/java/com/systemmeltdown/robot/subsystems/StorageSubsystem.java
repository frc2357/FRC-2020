package com.systemmeltdown.robot.subsystems;

import com.systemmeltdown.robot.shuffleboard.CellNumberWidget; //Imported for the javadoc

import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlog.topics.BooleanTopic;
import com.systemmeltdown.robotlog.topics.DoubleTopic;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * The subsystem for the power cell storage.
 * 
 * @category Storage
 * @category Subsystems
 */
public class StorageSubsystem extends ClosedLoopSubsystem {
    public static class Configuration {
        public double m_distancePerRotationInches;

        /** Range in which the through bore encoder is reset */
        public double m_throughBoreEncoderResetLow = 0.19;
        public double m_throughBoreEncoderResetHigh = 0.21;

        /** If the rotate motor current exceeds this threshold, reverse direction */
        public double m_rotateMotorCurrentThreshold = 5;

        /** Don't reverse direction again within this time period */
        public double m_flipDeadTimeMs = 500;
        
        /** Zone in which the system is aligned for shooting */
        public double m_encoderAlignZoneDegrees = 0.02;
    }

    private int m_numOfCells = 3;

    private WPI_TalonSRX m_rotateMotor;

    private DutyCycleEncoder m_throughBoreEncoder;

    private boolean m_rotatePositive = true;

    private Configuration m_config = new Configuration();

    private long m_lastFlipTime = System.currentTimeMillis();

    /* RobotLog Topics */
    //private final StringTopic errorTopic = new StringTopic("Storage Sub Error");
    
    private final BooleanTopic m_isJammedTopic = new BooleanTopic("Is Jammed");
    private final DoubleTopic  m_motorCurrentTopic = new DoubleTopic("Motor Current", 0.25);

    /**
     * @param feedSensor The sensor mounted in the storage. This sensor is used to
     *                   count the number of power cells in the robot and should be
     *                   the type of {@link DigitalInput}.
     * 
     * @param throughBoreEncoder The encoder that go spin
     */
    public StorageSubsystem(WPI_TalonSRX rotateMotor, DutyCycleEncoder throughBoreEncoder) {
        m_rotateMotor = rotateMotor;
        m_throughBoreEncoder = throughBoreEncoder;

        addChild("rotationMotor", m_rotateMotor);
        addChild("throughBoreEncoder", m_throughBoreEncoder);

        /*
         * TODO set the rotation motor encoder to not continuous, configure PID on the
         * Talon for position control, and use that to advance the carousel one cell at
         * a time
         */
        /*
         * // setup example m_rotateMotor.configFeedbackNotContinuous(true, 2000);
         * m_rotateMotor.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.
         * QuadEncoder, 0, 2000); m_rotateMotor.config_kP(0, 1);
         * 
         * // position example, assuming the encoder has 4096 ticks per rotation
         * m_rotateMotor.set(ControlMode.Position,
         * (m_rotateMotor.getSelectedSensorPosition() +
         * (4096/Constants.STORAGE_CAROUSEL_SEGMENTS)) % 4096);
         */
    }

    public void configure(Configuration config) {
        m_config = config;
        m_throughBoreEncoder.setDistancePerRotation(config.m_distancePerRotationInches);
        m_throughBoreEncoder.reset();
    }

    @Override
    public void periodic() {
        double encoderValue = Math.abs(m_throughBoreEncoder.get());
        if (encoderValue >= m_config.m_throughBoreEncoderResetLow &&
            encoderValue <= m_config.m_throughBoreEncoderResetHigh) {
            m_throughBoreEncoder.reset();
        }

        if (Math.abs(m_rotateMotor.getStatorCurrent()) > m_config.m_rotateMotorCurrentThreshold) {
            if (m_lastFlipTime < System.currentTimeMillis() - m_config.m_flipDeadTimeMs) {
                m_lastFlipTime = System.currentTimeMillis();
                m_rotatePositive = !m_rotatePositive;
            } 
        } else {
            m_isJammedTopic.log(false);
        }
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.addDoubleProperty(".cellCount", this::getNumOfCells, (double value) -> setNumOfCells((int) value));
    }

    public double getMotorCurrent() {
        return m_rotateMotor.getStatorCurrent();
    }

    // public boolean isAlignedForShooting() {
    //     final double encoderOffsetDegrees = m_throughBoreEncoder.get();
    //     if (encoderOffsetDegrees > 0) {
    //         return encoderOffsetDegrees < m_config.m_encoderAlignZoneDegrees;
    //     } else if (encoderOffsetDegrees < 0) {
    //         return encoderOffsetDegrees > -m_config.m_encoderAlignZoneDegrees;
    //     } else {
    //         return true;
    //     }
    // }

    public double getEncoderValue() {
        return m_throughBoreEncoder.get();
    }

    /** Set the rotation motor percent output */
    public void setRotationSpeed(double speed) {
        if (!m_rotatePositive) {
            speed *= -1;
        }
        m_rotateMotor.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Sets the number of cells. This value will also be displayed on the
     * {@link CellNumberWidget}.
     * 
     * @param numOfCells The value to set the number of cells to.
     */
    public void setNumOfCells(int numOfCells) {
        m_numOfCells = numOfCells;
    }

    public int getNumOfCells() {
        return m_numOfCells;
    }
}
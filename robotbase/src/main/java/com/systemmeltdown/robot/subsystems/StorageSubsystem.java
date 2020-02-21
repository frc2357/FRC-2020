package com.systemmeltdown.robot.subsystems;

import com.systemmeltdown.robot.shuffleboard.CellNumberWidget; //Imported for the javadoc

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlog.topics.BooleanTopic;
import com.systemmeltdown.robotlog.topics.StringTopic;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * The subsystem for the power cell storage.
 * 
 * @category Storage
 * @category Subsystems
 */
public class StorageSubsystem extends ClosedLoopSubsystem {
    public static class Configuration {
        public double distancePerRotationInches;
    }

    private final StringTopic errorTopic = new StringTopic("Storage Sub Error");
    private final StringTopic infoTopic = new StringTopic("Storage Sub Info");
    private final StringTopic debugTopic = new StringTopic("Storage Sub Debug");
    
    private final BooleanTopic isJammedTopic = new BooleanTopic("Is Jammed");

    private int m_numOfCells = 3;

    private WPI_TalonSRX m_rotateMotor;

    private DutyCycleEncoder m_throughBoreEncoder;

    private boolean rotatePositive = true;

    private long lastFlipTime = System.currentTimeMillis();

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
        addChild("throughBoreMotor", m_throughBoreEncoder);

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
        m_throughBoreEncoder.setDistancePerRotation(config.distancePerRotationInches);
        m_throughBoreEncoder.reset();
    }

    @Override
    public void periodic() {
        double encoderValue = Math.abs(m_throughBoreEncoder.get());
        if ((encoderValue >= .19) && (encoderValue <= .21)) {
            m_throughBoreEncoder.reset();
        }

        if (Math.abs(m_rotateMotor.getStatorCurrent()) > 6.0) {
            isJammedTopic.log(true);
            if (lastFlipTime < System.currentTimeMillis() - 500) {
                lastFlipTime = System.currentTimeMillis();
                rotatePositive = !rotatePositive;
            } 
        } else {
            isJammedTopic.log(false);
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

    public boolean isAlignedForShooting() {
        double encoderThresholdDegrees = .02;
        double encoderOffsetDegrees = m_throughBoreEncoder.get();
        if (encoderOffsetDegrees > 0) {
            return (encoderOffsetDegrees < encoderThresholdDegrees);
        } else if (encoderOffsetDegrees < 0) {
            return (encoderOffsetDegrees > -encoderThresholdDegrees);
        } else {
            return true;
        }
    }

    public double getEncoderValue() {
        return m_throughBoreEncoder.get();
    }

    /** Set the rotation motor percent output */
    public void setRotationSpeed(double speed) {
        if (!rotatePositive) {
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
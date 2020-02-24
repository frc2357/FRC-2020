package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * This subsystem is responsible for the components used in climbing.
 * 
 * This assumes that the winch motors have a ramp set.
 * 
 * @category Climb
 * @category Subsystems
 */
public class ClimbSubsystem extends ClosedLoopSubsystem {
    private enum ClimbDirection {
        Up, Left, Right, Stop
    }

    public static class Configuration {
        /** Target motor output setting for level climb, [0, 1] */
        public double m_climbSpeed = 0.1;

        /**
         * Target motor output setting for the climbing side when moving laterally, [0,
         * 1]
         */
        public double m_lateralSpeed = 0.1;

        /**
         * Settings for levelling PID, P units are <motor output>/degrees
         */
        public double m_P = 0.1;
        public double m_I = 0;
        public double m_D = 0;

        /** This is the difference in motor output required to keep the robot level */
        public double m_F = 0;
    }

    private Solenoid m_scissorExtendSolenoid;
    private PigeonIMU m_gyro;
    private WPI_TalonSRX m_leftWinchMotor;
    private WPI_TalonSRX m_rightWinchMotor;

    private boolean m_keepLevel;
    private ClimbDirection m_climbDirection;
    private PIDController m_rollController;

    private Configuration m_config;

    /**
     * @param solenoidLeft The left scissor solenoid.
     * @param solenoidRight The right scissor solenoid.
     * @param gyro The gyro sensor.
     * @param leftWinchMotor The left winch motor responsible for pulling the robot up.
     * @param rightWinchMotor The right winch motor responsible for pulling the robot up.
     */
    public ClimbSubsystem(Solenoid solenoid, PigeonIMU gyro, 
            WPI_TalonSRX leftWinchMotor, WPI_TalonSRX rightWinchMotor) {
        m_scissorExtendSolenoid = solenoid;
        m_gyro = gyro;
        m_leftWinchMotor = leftWinchMotor;
        m_rightWinchMotor = rightWinchMotor;

        m_keepLevel = false;
        m_climbDirection = ClimbDirection.Stop;
        m_rollController = new PIDController(0, 0, 0);
        m_rollController.setIntegratorRange(-1, 1);
        m_rollController.setSetpoint(0);

        setConfiguration(new Configuration());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.addDoubleProperty("leftWinchCurrent", this::getLeftWinchCurrent, null);
        builder.addDoubleProperty("rightWinchCurrent", this::getRightWinchCurrent, null);
    }  

    @Override
    public void periodic() {
        double leftOutput = 0;
        double rightOutput = 0;

        switch (m_climbDirection) {
        case Up:
            leftOutput = m_config.m_climbSpeed;
            rightOutput = m_config.m_climbSpeed;
            break;
        case Left:
            leftOutput = m_config.m_lateralSpeed;
            break;
        case Right:
            rightOutput = m_config.m_lateralSpeed;
            break;
        case Stop:
            break;
        }

        if (m_keepLevel && isClosedLoopEnabled()) {
            double roll = getRoll();
            double rollDelta = m_rollController.calculate(roll);
            rollDelta += m_config.m_F;
            // apply to motors evenly
            rollDelta /= 2;

            // TODO verify roll is positive towards the left side
            leftOutput += rollDelta;
            rightOutput -= rollDelta;
        }

        m_leftWinchMotor.set(ControlMode.PercentOutput, leftOutput);
        m_rightWinchMotor.set(ControlMode.PercentOutput, rightOutput);
    }

    //===================
    //     SCISSOR
    //===================

    /**
     * Extends the scissor lift.
     */
    public void extendScissor() {
        m_scissorExtendSolenoid.set(true);
    }

    /**
     * Deactivates the scissor solenoids.
     */
    public void releaseScissor() {
        m_scissorExtendSolenoid.set(false);
    }

    /**
     * @return True if the scissor solenoid is active, false if not.
     */
    public boolean isScissorExtending() {
        return m_scissorExtendSolenoid.get();
    }

    //===================
    //  CLIMB[DIRECTION]
    //===================

    public void climbUp() {
        m_climbDirection = ClimbDirection.Up;
    }

    public void climbRight() {
        m_climbDirection = ClimbDirection.Right;
    }

    public void climbLeft() {
        m_climbDirection = ClimbDirection.Left;
    }

    public void stopClimb() {
        m_climbDirection = ClimbDirection.Stop;
    }

    //===================
    //     GETTERS
    //===================

    /**
     * @return The roll value from the gyro.
     */
    public double getRoll() {
        double[] ypr = new double[3];
        m_gyro.getYawPitchRoll(ypr);
        return ypr[2];
    }

    /**
     * @return The value of m_keepLevel.
     */
    public boolean getKeepLevel() {
        return m_keepLevel;
    }

    /**
     * @return current draw of the left winch motor in amps
     */
    public double getLeftWinchCurrent() {
        return m_leftWinchMotor.getStatorCurrent();
    }

    /**
     * @return current draw of the right winch motor in amps
     */
    public double getRightWinchCurrent() {
        return m_rightWinchMotor.getStatorCurrent();
    }

    //===================
    //     SETTERS
    //===================

    public void setKeepLevel(boolean keepLevel) {
        m_keepLevel = keepLevel;
    }

    public void setConfiguration(Configuration configuration) {
        m_config = configuration;

        m_rollController.setP(m_config.m_P);
        m_rollController.setI(m_config.m_I);
        m_rollController.setD(m_config.m_D);
    }
}
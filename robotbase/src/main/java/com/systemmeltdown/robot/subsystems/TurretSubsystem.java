package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlib.util.Utility;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.controller.PIDController;

/**
 * Subsystem for the turret.
 * 
 * @category Turret
 * @category Subsystems
 */
public class TurretSubsystem extends ClosedLoopSubsystem {
    private WPI_TalonSRX m_rotateMotor;
    private PIDController m_horzAimController;
    private double m_horzAimMeasurement;
    private boolean m_horzAimControllerActive;

    private Configuration m_configuration;

    static public class Configuration
    {
        public double m_turretAimP = 1e-3;
        public double m_turretAimI = 0;
        public double m_turretAimD = 0;      
    }

    /**
     * @param rotateMotor Motor that rotates the turret.
     */
    public TurretSubsystem(WPI_TalonSRX rotateMotor) {
        m_configuration = new Configuration();
        m_rotateMotor = rotateMotor;
        m_horzAimController = new PIDController(0, 0, 0);
        m_horzAimControllerActive = false;
        m_horzAimMeasurement = 0;

        configurePidControllers();

        // add dashboard controls for children
        addChild("rotateMotor", m_rotateMotor);
        addChild("horzAimPID", m_horzAimController);
    }

    @Override
    public void periodic() {
        if(m_horzAimControllerActive && isClosedLoopEnabled()) {
            double cv = m_horzAimController.calculate(m_horzAimMeasurement);
            double turretRotateSpeed = Utility.clamp(cv, -1, 1);
            setTurretMotor(turretRotateSpeed);
        }
    }

    /**
     * Set the turret motor control
     * @param percentOutput [-1, 1] positive values turn clockwise when looking
     *                      from the top of the robot
     * 
     * TODO when the turret motor is installed update the code to use the correct rotation direction
     */
    public void setTurretMotor(double percentOutput) {
        m_rotateMotor.set(ControlMode.PercentOutput, percentOutput);
    }

    /**
     * Set values for the horizontal aim closed loop controller. This needs to be
     * called every time step for the loop to be effective.
     * @param setpoint target value for the horizontal aim
     * @param measurement current value of the horizontal aim
     */
    public void setHorizontalAimClosedLoop(double setpoint, double measurement) {
        m_horzAimControllerActive = true;
        m_horzAimMeasurement = measurement;
        m_horzAimController.setSetpoint(setpoint);
    }

    /**
     * Disable closed loop control for the horizontal aiming
     */
    public void disableHorizontalAimClosedLoop() {
        m_horzAimControllerActive = false;
    }

    public void setConfiguration(Configuration configuration) {
        m_configuration = configuration;
        configurePidControllers();
    }

    private void configurePidControllers() {
        m_horzAimController.setP(m_configuration.m_turretAimP);
        m_horzAimController.setI(m_configuration.m_turretAimI);
        m_horzAimController.setD(m_configuration.m_turretAimD);
    }
}
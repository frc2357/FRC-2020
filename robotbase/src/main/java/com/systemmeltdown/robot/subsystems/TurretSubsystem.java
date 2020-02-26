package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlib.util.Utility;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.controller.PIDController;

/**
 * Subsystem for the turret.
 * 
 * @category Turret
 * @category Subsystems
 */
public class TurretSubsystem extends ClosedLoopSubsystem {
    // This is really a servo
    private PWM m_rotateServo;
    private PIDController m_horzAimController;
    private double m_horzAimMeasurement;
    private boolean m_horzAimControllerActive;

    private Servo m_hoodServo1;
    private Servo m_hoodServo2;

    private Configuration m_configuration;

    static public class Configuration {
        public double m_turretAimP = 1e-3;
        public double m_turretAimI = 0;
        public double m_turretAimD = 0;      
    }

    /**
     * @param rotateServo Servo that rotates the turret.
     * @param hoodServo   Servo controlling the hood.
     */
    public TurretSubsystem(PWM rotateServo, Servo hoodServo1, Servo hoodServo2) {
        m_configuration = new Configuration();
        m_rotateServo = rotateServo;
        m_hoodServo1 = hoodServo1;
        m_hoodServo2 = hoodServo2;
        m_horzAimController = new PIDController(0, 0, 0);
        m_horzAimControllerActive = false;
        m_horzAimMeasurement = 0;

        configurePidControllers();

        // add dashboard controls for children
        addChild("rotateServo", m_rotateServo);
        addChild("hoodServo1", m_hoodServo1);
        addChild("hoodServo2", m_hoodServo2);
        addChild("horzAimPID", m_horzAimController);
    }

    @Override
    public void periodic() {
        if(m_horzAimControllerActive && isClosedLoopEnabled()) {
            double cv = m_horzAimController.calculate(m_horzAimMeasurement);
            System.out.println(cv);
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
        m_rotateServo.setSpeed(percentOutput);
    }

    public void setHoodAngle(double degrees) {
        m_hoodServo1.setAngle(degrees);
        m_hoodServo2.setAngle(degrees);
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
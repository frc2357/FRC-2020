package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
    private Solenoid m_scissorExtendSolenoid;

    private SpeedControllerGroup m_leftTalons;
    private SpeedControllerGroup m_rightTalons;

    public ClimbSubsystem(Solenoid solenoid, PigeonIMU gyro) {
        m_scissorExtendSolenoid = solenoid;
        m_leftTalons = new SpeedControllerGroup(new WPI_TalonSRX(Constants.DRIVE_MOTOR_LEFT_1), new WPI_TalonSRX(Constants.DRIVE_MOTOR_LEFT_2));
        m_rightTalons = new SpeedControllerGroup(new WPI_TalonSRX(Constants.DRIVE_MOTOR_RIGHT_1), new WPI_TalonSRX(Constants.DRIVE_MOTOR_RIGHT_2));
    }

    @Override
    public void periodic() {
        //Nothing
    }

    public void toggleScissor() {
        m_scissorExtendSolenoid.set(m_scissorExtendSolenoid.get());
    }

    public void runRightMotors(double speed) {
        m_rightTalons.set(speed);
    }

    public void runLeftMotors(double speed) {
        m_leftTalons.set(speed);
    }
}
package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.systemmeltdown.robotlib.subsystems.drive.controllerGroups.TalonGroup;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
    private Solenoid m_scissorExtendSolenoid;

    private TalonGroup m_leftTalons;
    private TalonGroup m_rightTalons;

    public ClimbSubsystem(Solenoid solenoid, TalonGroup rightTalons, TalonGroup leftTalons, PigeonIMU gyro) {
        m_scissorExtendSolenoid = solenoid;
        m_rightTalons = rightTalons;
        m_leftTalons = leftTalons;
    }

    @Override
    public void periodic() {
        //Nothing
    }

    public void toggleScissor() {
        m_scissorExtendSolenoid.set(m_scissorExtendSolenoid.get());
    }

    public void runRightMotors(double speed) {
        m_rightTalons.set(ControlMode.PercentOutput, speed);
    }

    public void runLeftMotors(double speed) {
        m_leftTalons.set(ControlMode.PercentOutput, speed);
    }
}
package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase {
    private WPI_TalonSRX m_feederMotor;

    public FeederSubsystem(int feederMotorID) {
        m_feederMotor = new WPI_TalonSRX(feederMotorID);
    }

    public void runFeederMotor(double speed) {
        m_feederMotor.set(ControlMode.Position, speed);
    }

}
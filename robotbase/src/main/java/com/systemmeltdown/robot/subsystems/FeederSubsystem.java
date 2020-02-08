package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;

public class FeederSubsystem extends ClosedLoopSubsystem {
    private WPI_TalonSRX m_feederMotor;
    private DigitalInput m_feedSensor;
    
    public FeederSubsystem(int feederMotorID, int feedSensorID) {
        m_feederMotor = new WPI_TalonSRX(feederMotorID);
        m_feedSensor = new DigitalInput(feedSensorID);

        addChild("feederMotor", m_feederMotor);
        addChild("feedSensor", m_feedSensor);
    }

    public void runFeederMotor(double speed) {
        m_feederMotor.set(ControlMode.Position, speed);
    }

    public boolean isFeedSensorBlocked() {
        return m_feedSensor.get();
    }
}
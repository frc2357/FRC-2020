package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.util.ClosedLoopSystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase implements ClosedLoopSystem {
    private WPI_TalonSRX m_feederMotor;
    private boolean m_useClosedLoop;

    public FeederSubsystem(int feederMotorID) {
        m_feederMotor = new WPI_TalonSRX(feederMotorID);
    }

    public void runFeederMotor(double speed) {
        m_feederMotor.set(ControlMode.Position, speed);
    }

    @Override
    public boolean isClosedLoopEnabled() {
        return m_useClosedLoop;
    }

    @Override
    public void setClosedLoopEnabled(boolean ClosedLoopEnabled) {
        m_useClosedLoop = ClosedLoopEnabled;

    }
}
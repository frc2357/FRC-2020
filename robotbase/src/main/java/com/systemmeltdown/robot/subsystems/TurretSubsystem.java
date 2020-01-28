package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.util.ClosedLoopSystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase implements ClosedLoopSystem {
    private boolean m_useClosedLoop;
    private WPI_TalonSRX m_rotateMotor;

    public TurretSubsystem(int rotateMotorID) {
        m_rotateMotor = new WPI_TalonSRX(rotateMotorID);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void runMotor(WPI_TalonSRX rotatorMotor, double degreesToRotate) {
        rotatorMotor.set(ControlMode.Position, degreesToRotate);
    }

    public int getSensorPosition() {
        return m_rotateMotor.getSelectedSensorPosition();
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
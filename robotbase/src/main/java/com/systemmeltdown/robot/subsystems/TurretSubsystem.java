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

        // add dashboard controls for children
        addChild("rotateMotor", m_rotateMotor);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
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
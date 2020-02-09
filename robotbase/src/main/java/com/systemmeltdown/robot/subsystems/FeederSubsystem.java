package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

/**
 * The subsystem that contains the motor that feeds balls to the turret.
 * 
 * @category Turret
 * @category Subsystems
 * 
 *  TODO: Verify which motor this subsystem controls. (And update the javadoc accordingly.)
 */
public class FeederSubsystem extends ClosedLoopSubsystem {
    private WPI_TalonSRX m_feederMotor;
    
    /**
     * @param feederMotorID The ID of the feeder motor.
     */
    public FeederSubsystem(int feederMotorID) {
        m_feederMotor = new WPI_TalonSRX(feederMotorID);
    }

    /**
     * Runs the feeder motor.
     * @param speed Speed to run the feeder motor at.
     */
    public void runFeederMotor(double speed) {
        m_feederMotor.set(ControlMode.Position, speed);
    }
}
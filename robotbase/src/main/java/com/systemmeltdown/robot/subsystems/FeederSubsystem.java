package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The subsystem that contains the motor that feeds cells to the turret.
 * 
 * @category Turret
 * @category Subsystems
 * 
 *  TODO: Verify which motor this subsystem controls. (And update the javadoc accordingly.)
 */
public class FeederSubsystem extends ClosedLoopSubsystem {
    private WPI_TalonSRX m_feederMotor;
    private DigitalInput m_feedSensor;
    
    /**
     * @param feederMotorID The ID of the feeder motor.
     * @param feederSensorID The ID of the infrared sensor that detects if there is a cell in the slot before the feeder
     */
    public FeederSubsystem(int feederMotorID, int feedSensorID) {
        m_feederMotor = new WPI_TalonSRX(feederMotorID);
        m_feedSensor = new DigitalInput(feedSensorID);

        addChild("feederMotor", m_feederMotor);
        addChild("feedSensor", m_feedSensor);
    }

    /**
     * Runs the feeder motor.
     * @param speed Speed to run the feeder motor at.
     */
    public void runFeederMotor(double speed) {
        m_feederMotor.set(ControlMode.PercentOutput, speed);
    }

    public boolean isFeedSensorBlocked() {
        return m_feedSensor.get();
    }
}
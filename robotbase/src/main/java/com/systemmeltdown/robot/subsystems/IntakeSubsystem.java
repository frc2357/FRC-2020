package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.arduino.ArduinoUSBController;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;

public class IntakeSubsystem extends ClosedLoopSubsystem {
    // private DoubleSolenoid m_intakeSolenoid;
    private WPI_TalonSRX m_intakeTalon;
    private ArduinoUSBController m_arduinoUSB;
    private boolean m_rollIntoBot = true;

    public IntakeSubsystem(int intakeTalonID, int forwardChannel, int reverseChannel) {
        // m_intakeSolenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
        m_intakeTalon = new WPI_TalonSRX(intakeTalonID);

        m_arduinoUSB = new ArduinoUSBController(Constants.ARDUINO_DEVICE_NAME);

        m_arduinoUSB.start();
        
        setTOFRange(Constants.TOF_LOW_RANGE, Constants.TOF_HIGH_RANGE);
    }

    /**
     * @param percentPowerOutput -1 = reverse | 0 = stop | 1 = foward
     */
    public void triggerIntakeRoller(double percentPowerOutput) {
        if (!m_rollIntoBot) {
            percentPowerOutput = -percentPowerOutput;
        }
        m_intakeTalon.set(ControlMode.PercentOutput, percentPowerOutput);
    }

    public void toggleRollDirection() {
        m_rollIntoBot = !m_rollIntoBot;
    }

    public void changeArmPosition() {
    //     switch (m_intakeSolenoid.get()) {
    //         case kForward: {
    //             m_intakeSolenoid.set(Value.kReverse);
    //             break;
    //         } 
    //         default: {
    //             m_intakeSolenoid.set(Value.kForward);
    //         }
    //     }
    }

    /**
	 * Get the count number of powercells in the intake.
	 */
	public int getCount() {
		// Check if the arduino is connected before getting values.
		if (!m_arduinoUSB.isConnected()) {
			return -1;
		}
		return m_arduinoUSB.getDeviceFieldInt("intakeCounter", "cells");
    }

    /**
     * Sets the middle range of the TOF sensors
     * @param lowRange Sets the low end of the middle range
     * @param highRange Sets the high end of the middle range
     */
    public void setTOFRange(int lowRange, int highRange) {
        // Check if the arduino is connected before getting values.
		if (!m_arduinoUSB.isConnected()) {
			return;
        }
        
        m_arduinoUSB.setDeviceField("intakeCounter", "lowRange", lowRange);
        m_arduinoUSB.setDeviceField("intakeCounter", "highRange", highRange);
    }
}

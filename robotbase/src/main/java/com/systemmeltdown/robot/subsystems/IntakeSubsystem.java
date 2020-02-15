package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.arduino.ArduinoUSBController;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;

/**
 * The subsystem for the intake.
 * 
 * @category Intake
 * @category Subsystems
 */
public class IntakeSubsystem extends ClosedLoopSubsystem {
    private DoubleSolenoid m_intakeSolenoid;
    private WPI_TalonSRX m_intakeTalon;
    private ArduinoUSBController m_arduinoUSB;
    private boolean m_rollIntoBot = true;

    /**
     * @param intakeTalonID The ID for the Talon on the intake.
     * @param forwardChannel The forward channel number on the PCM on the Double Solenoid.
     * @param reverseChannel The reverse channel number on the PCM on the Double Solenoid.
     */
    public IntakeSubsystem(int intakeTalonID, int forwardChannel, int reverseChannel) {
        m_intakeSolenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
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

    /**
     * Sets the roll direction to false if true, and vice versa.
     */
    public void toggleRollDirection() {
        m_rollIntoBot = !m_rollIntoBot;
    }

    /**
     * Changes the arm position.
     */
    public void changeArmPosition() {
        switch (m_intakeSolenoid.get()) {
            case kForward: {
                m_intakeSolenoid.set(Value.kReverse);
                break;
            } 
            case kReverse: {
                m_intakeSolenoid.set(Value.kForward);
                break;
            }
            default: {
                m_intakeSolenoid.set(Value.kForward);
            }
        }
    }

    /**
	 * Get the count number of powercells in the intake.
	 */
	public int getNumOfPowerCells() {
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

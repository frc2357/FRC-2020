package com.systemmeltdown.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.arduino.ArduinoUSBController;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlog.topics.BooleanTopic;
import com.systemmeltdown.robotlog.topics.DoubleTopic;

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

    /* RobotLog Topics */
    //private final StringTopic m_intakeSubErrorTopic = new StringTopic("Intake Sub Error");
    // /\ Unused /\
    private final DoubleTopic motorCurrentTopic = new DoubleTopic("Intake Motor Current", 0.25);
    private final BooleanTopic m_rollIntoBotTopic = new BooleanTopic("Rolling into Bot");

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

        resetArduino();

        m_rollIntoBotTopic.log(m_rollIntoBot);
    }

    @Override
    public void periodic() {
        motorCurrentTopic.log(m_intakeTalon.getStatorCurrent());
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
        m_rollIntoBotTopic.log(m_rollIntoBot);
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
     * Below is everything that handles the Arduino connected to the TOFs.
     * Also, in order to work properly after a redeploy, the TOF's need to 
     * sense an update(Wave your hand in front of them or something).
     */

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
        
        Map<String, Object> rangeMap = new HashMap<String, Object>();

        rangeMap.put("lowRange", lowRange);
        rangeMap.put("highRange", highRange);

        // m_arduinoUSB.setDeviceField("intakeCounter", rangeMap);
    }

    public void setTOFRangeLow(int lowRange) {
        // Check if the arduino is connected before getting values.
		if (!m_arduinoUSB.isConnected()) {
			return;
        }
        m_arduinoUSB.setDeviceField("intakeCounter", "lowRange", lowRange);
    }

    public void setTOFRangeHigh(int highRange) {
        // Check if the arduino is connected before getting values.
		if (!m_arduinoUSB.isConnected()) {
			return;
        }
        m_arduinoUSB.setDeviceField("intakeCounter", "highRange", highRange);
    }

    /**
     * Resets arduino by sending a boolean to tell it to rerun it's setup method.
     * Required to be called after redeploy.
     */
    public void resetArduino() {
        m_arduinoUSB.setDeviceField("intakeCounter", "needsReset", true);
    }
}

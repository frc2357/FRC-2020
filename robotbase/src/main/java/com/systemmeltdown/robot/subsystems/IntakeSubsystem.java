package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The subsystem for the intake.
 * 
 * @category Intake
 * @category Subsystems
 */
public class IntakeSubsystem extends ClosedLoopSubsystem {
    // private DoubleSolenoid m_intakeSolenoid;
    private WPI_TalonSRX m_intakeTalon;
    private boolean m_rollIntoBot = true;

    /**
     * @param intakeTalonID The ID for the Talon on the intake.
     * @param forwardChannel The forward channel number on the PCM on the Double Solenoid.
     * @param reverseChannel The reverse channel number on the PCM on the Double Solenoid.
     */
    public IntakeSubsystem(int intakeTalonID, int forwardChannel, int reverseChannel) {
        // m_intakeSolenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
        m_intakeTalon = new WPI_TalonSRX(intakeTalonID);
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
}

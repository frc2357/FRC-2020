package com.systemmeltdown.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

public class IntakeSub extends ClosedLoopSubsystem {
    private Solenoid m_intakeSolenoid;
    private WPI_TalonSRX m_intakeTalon;
    private boolean m_rollIntoBot = true;

    public IntakeSub(int intakeTalonID, int forwardChannel, int reverseChannel) {
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

    public void toggleRollDirection() {
        m_rollIntoBot = !m_rollIntoBot;
    }

    public void changeArmPosition() {
        // switch (m_intakeSolenoid.get()) {
        // case kForward: {
        //     m_intakeSolenoid.set(Value.kReverse);
        //     break;
        // }
        // default: {
        //     m_intakeSolenoid.set(Value.kForward);
        // }
        // }
    }
}

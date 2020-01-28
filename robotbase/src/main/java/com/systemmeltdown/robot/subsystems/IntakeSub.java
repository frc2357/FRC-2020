package com.systemmeltdown.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.util.ClosedLoopSystem;

public class IntakeSub extends SubsystemBase implements ClosedLoopSystem {
    private boolean m_useClosedLoop;

    private Solenoid m_intakeSolenoid;
    private WPI_TalonSRX m_intakeTalon;
    private boolean m_isArmOut = false;

    public IntakeSub(int channel, int intakeTalonID) {
        m_intakeSolenoid = new Solenoid(channel);
        m_intakeTalon = new WPI_TalonSRX(intakeTalonID);
    }

    @Override
    public void periodic() {
        // nothing
    }

    /**
     *
     * @param percentPowerOutput -1 = reverse | 0 = stop | 1 = foward
     */
    public void triggerIntakeRoller(double percentPowerOutput) {
        m_intakeTalon.set(ControlMode.PercentOutput, percentPowerOutput);
    }

    // Current values are most likley incorrect, actual values will be figured out
    // through testing.
    public void changeArmPosition() {

        if (m_isArmOut) {
            m_intakeSolenoid.set(false);
            m_isArmOut = false;
        } else {
            m_intakeSolenoid.set(true);
            m_isArmOut = true;
        }
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
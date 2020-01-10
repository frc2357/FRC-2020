package com.systemmeltdown.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeSub extends SubsystemBase {

    private Solenoid m_intakeSolenoid;
    private WPI_TalonSRX m_intakeTalon;
    private boolean m_isArmUp = true;

    public IntakeSub(int channel, int intakeTalonID) {

        m_intakeSolenoid = new Solenoid(channel);
        m_intakeTalon = new WPI_TalonSRX(intakeTalonID);
 
    }

    @Override
    public void periodic() {
        // nothing
    }

    //percentPowerOuput defintion: -1 = reverse | 0 = stop | 1 = foward
    public void triggerIntakeRoller(double percentPowerOutput) {
        m_intakeTalon.set(ControlMode.PercentOutput, percentPowerOutput);   
    }

    //Current values are most likley incorrect, actual values will be figured out through testing.
    public void changeArmPosition() {

        if(m_isArmUp) {
            m_intakeSolenoid.set(false);
            m_isArmUp = false;

        } else {
            m_intakeSolenoid.set(true);
            m_isArmUp = true;
        }
        
    }

    
}

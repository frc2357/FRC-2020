package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
    
    private WPI_TalonSRX m_leftReelTalon;
    private WPI_TalonSRX m_rightReelTalon;

    private PigeonIMU m_gyro;

    public ClimbSubsystem(int talonRightID,int talonLeftID, int gyroID) {

        m_leftReelTalon = new WPI_TalonSRX(talonLeftID);
        m_rightReelTalon = new WPI_TalonSRX(talonRightID);
        m_gyro = new PigeonIMU(gyroID);
    }

    @Override
    public void periodic() {

    }
    //rename position to what unit it is
    public void runMotor(WPI_TalonSRX reelTalon, double inchesToExtend) {
        reelTalon.set(ControlMode.Position, inchesToExtend);
    }

}
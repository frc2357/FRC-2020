package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax m_shooterMotor;
  private TalonSRX m_inputMotor;

  public ShooterSubsystem(int shooterMotorID, int inputMotorID) {
    m_shooterMotor = new CANSparkMax(shooterMotorID,  MotorType.kBrushless);
    m_inputMotor = new TalonSRX(inputMotorID);
  private CANSparkMax m_shooterMotor1;
  private CANSparkMax m_shooterMotor2;

  public ShooterSubsystem(int shooterMotorID1, int shooterMotorID2) {
    m_shooterMotor1 = new CANSparkMax(shooterMotorID1,  MotorType.kBrushless);
    m_shooterMotor2 = new CANSparkMax(shooterMotorID2,  MotorType.kBrushless);
    m_shooterMotor2.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runMotor(double speed) {
    m_shooterMotor1.set(speed);
    m_shooterMotor2.set(speed);
  }

  public void runInputMotor(double speed) {
    m_inputMotor.set(ControlMode.Position, speed);
  }
}
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
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runMotor(CANSparkMax shootMotor, double speed) {
    shootMotor.set(speed);
  }

  public void runInputMotor(double speed) {
    m_inputMotor.set(ControlMode.Position, speed);
  }
}
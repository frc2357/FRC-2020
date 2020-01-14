package com.systemmeltdown.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
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
}

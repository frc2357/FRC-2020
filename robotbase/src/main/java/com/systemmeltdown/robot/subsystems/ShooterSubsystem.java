package com.systemmeltdown.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private CANSparkMax m_shooterMotor;

  public ShooterSubsystem(int shooterMotorID) {
    m_shooterMotor = new CANSparkMax(shooterMotorID,  MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public  void runMotor(CANSparkMax shootMotor, double speed) {
    shootMotor.set(speed);
  }
}
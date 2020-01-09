package com.systemmeltdown.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  
  private CANSparkMax m_shooterMotor;

  public ExampleSubsystem(int shooterMotorID) {
    m_shooterMotor = new sparkMax(shooterMotorID,  MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public  void runMotor(CANSparkMax shootMotor) {
    shootMotor.set(ControlMode.Position, position);
  }
}

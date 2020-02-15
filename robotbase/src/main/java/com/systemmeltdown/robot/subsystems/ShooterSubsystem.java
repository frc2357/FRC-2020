package com.systemmeltdown.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj.Servo;

public class ShooterSubsystem extends ClosedLoopSubsystem {
  private CANSparkMax m_shooterMotor1;
  private CANSparkMax m_shooterMotor2;

  // TODO replace with the specific class for the motor
  private Servo m_hoodServo;

  public ShooterSubsystem(int shooterMotorID1, int shooterMotorID2, Servo hoodServo) {
    m_shooterMotor1 = new CANSparkMax(shooterMotorID1,  MotorType.kBrushless);
    m_shooterMotor2 = new CANSparkMax(shooterMotorID2,  MotorType.kBrushless);
    m_shooterMotor2.setInverted(true);
    m_hoodServo = hoodServo;
    addChild("hoodServo", m_hoodServo);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runMotor(double speed) {
    m_shooterMotor1.set(speed);
    m_shooterMotor2.set(speed);
  }

  public void setHoodAngle(double degrees) {
    m_hoodServo.setAngle(degrees);
}
}
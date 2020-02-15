package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj.Servo;

/**
 * The subsystem for the turret.
 * 
 * @category Turret
 * @category Subsystems
 */
public class ShooterSubsystem extends ClosedLoopSubsystem {
  private WPI_TalonFX m_shooterMotor1;
  private WPI_TalonFX m_shooterMotor2;

  // TODO replace with the specific class for the motor
  private Servo m_hoodServo;

  /**
   * @param shooterMotorID1 The first motor controlling the turret.
   * @param shooterMotorID2 The second motor controlling the turret.
   * @param hoodServo       the servo that moves the hood
   */
  public ShooterSubsystem(int shooterMotorID1, int shooterMotorID2, Servo hoodServo) {
    m_shooterMotor1 = new WPI_TalonFX(shooterMotorID1);
    m_shooterMotor2 = new WPI_TalonFX(shooterMotorID2);
    m_shooterMotor2.setInverted(true);
    m_hoodServo = hoodServo;
    addChild("hoodServo", m_hoodServo);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Sets the motors to the speed that is passed into it.
   * 
   * @param speed The speed to set the motors to.
   */
  public void runMotor(double speed) {
    m_shooterMotor1.set(speed);
    m_shooterMotor2.set(speed);
  }

  public void setHoodAngle(double degrees) {
    m_hoodServo.setAngle(degrees);
  }
}
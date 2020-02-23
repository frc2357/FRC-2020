package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;

/**
 * The subsystem for the turret.
 * 
 * @category Turret
 * @category Subsystems
 */
public class ShooterSubsystem extends ClosedLoopSubsystem {
  private WPI_TalonFX m_shooterMotor1;
  private WPI_TalonFX m_shooterMotor2;

  /**
   * @param shooterMotorID1 The first motor controlling the turret.
   * @param shooterMotorID2 The second motor controlling the turret.
   */
  public ShooterSubsystem(int shooterMotorID1, int shooterMotorID2) {
    m_shooterMotor1 = new WPI_TalonFX(shooterMotorID1);
    m_shooterMotor2 = new WPI_TalonFX(shooterMotorID2);

    addChild("motor1", m_shooterMotor1);
    addChild("motor2", m_shooterMotor2);

    // reset motor configs to known state
    m_shooterMotor1.configFactoryDefault(Constants.TIMEOUT_MS);
    m_shooterMotor2.configFactoryDefault(Constants.TIMEOUT_MS);

    m_shooterMotor2.setInverted(true);
    m_shooterMotor2.follow(m_shooterMotor1);

    m_shooterMotor1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
                                                 0,
                                                 Constants.TIMEOUT_MS);

    // >>> Change this if positive motor output gives negative encoder feedback <<<
    m_shooterMotor1.setSensorPhase(true);

    // Configure output range
    m_shooterMotor1.configNominalOutputForward(0, Constants.TIMEOUT_MS);
    m_shooterMotor1.configNominalOutputReverse(0, Constants.TIMEOUT_MS);
    m_shooterMotor1.configPeakOutputForward(Constants.SHOOTER_MOTOR_PEAK_OUTPUT, Constants.TIMEOUT_MS);
    m_shooterMotor1.configPeakOutputReverse(0, Constants.TIMEOUT_MS); // don't run the motors in reverse

    m_shooterMotor1.config_kP(0, Constants.SHOOTER_P, Constants.TIMEOUT_MS);
    m_shooterMotor1.config_kI(0, Constants.SHOOTER_I, Constants.TIMEOUT_MS);
    m_shooterMotor1.config_kD(0, Constants.SHOOTER_D, Constants.TIMEOUT_MS);
    m_shooterMotor1.config_kF(0, Constants.SHOOTER_F, Constants.TIMEOUT_MS);

    setMotorSpeed(4000);
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);

    // this might be included in the motor child control
    builder.addDoubleProperty("motorSpeed", this::getMotorSpeed, this::setMotorSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Set the motor to a percent output. This bypasses closed-loop control.
   * @param output
   */
  public void runMotorOpenLoop(double output)
  {
    m_shooterMotor1.set(ControlMode.PercentOutput, output);
  }

  /**
   * Set the motor speed using closed-loop control
   * @param rpm rotations per minute
   */
  public void setMotorSpeed(double rpm)
  {
    double nativeSpeed = rpm * Constants.FALCON_ENCODER_CPR / Constants.MINUTES_TO_100_MS;
    m_shooterMotor1.set(ControlMode.Velocity, nativeSpeed);
  }

  /**
   * @return current motor velocity in rpm
   */
  public double getMotorSpeed()
  {
    return m_shooterMotor1.getSelectedSensorPosition(0) * Constants.MINUTES_TO_100_MS / Constants.FALCON_ENCODER_CPR;
  }
}

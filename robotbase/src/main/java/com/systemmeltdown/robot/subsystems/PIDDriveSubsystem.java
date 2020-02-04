package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PIDDriveSubsystem extends FalconTrajectoryDriveSubsystem {
    FalconTrajectoryDriveSubsystem.Configuration config = new FalconTrajectoryDriveSubsystem.Configuration();
    config.m_isRightInverted = true;

    private static WPI_TalonFX leftFalconMaster = new WPI_TalonFX(Constants.DRIVE_MOTOR_LEFT_1);
    private static WPI_TalonFX[] leftFalconSlaves = new WPI_TalonFX[] { new WPI_TalonFX(Constants.DRIVE_MOTOR_LEFT_2) };
    private static WPI_TalonFX rightFalconMaster = new WPI_TalonFX(Constants.DRIVE_MOTOR_RIGHT_1);
    private static WPI_TalonFX[] rightFalconSlaves = new WPI_TalonFX[] { new WPI_TalonFX(Constants.DRIVE_MOTOR_RIGHT_2) };
    private static PigeonIMU gyro = new PigeonIMU(Constants.GYRO_ID);

    public PIDDriveSubsystem() {
        super(leftFalconMaster, leftFalconSlaves, rightFalconMaster, rightFalconSlaves, 
        gyro, Constants.ENCODER_DISTANCE_PER_PULSE);
        FalconTrajectoryDriveSubsystem.configure(config);
    }
}
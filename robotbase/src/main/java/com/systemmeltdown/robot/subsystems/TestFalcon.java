package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TestFalcon extends FalconTrajectoryDriveSubsystem {
    NetworkTableEntry m_xEntry;
    NetworkTableEntry m_yEntry;

    static WPI_TalonFX leftFalconMaster = new WPI_TalonFX(Constants.DRIVE_MOTOR_LEFT_1);
    static WPI_TalonFX[] leftFalconSlaves = new WPI_TalonFX[] { new WPI_TalonFX(Constants.DRIVE_MOTOR_LEFT_2) };
    static WPI_TalonFX rightFalconMaster = new WPI_TalonFX(Constants.DRIVE_MOTOR_RIGHT_1);
    static WPI_TalonFX[] rightFalconSlaves = new WPI_TalonFX[] { new WPI_TalonFX(Constants.DRIVE_MOTOR_RIGHT_2) };
    // The gyro sensor
    private static PigeonIMU m_gyro = new PigeonIMU(Constants.GYRO_ID);
    private boolean m_isGyroReversed;

    // Odometry class for tracking robot pose
    private final DifferentialDriveOdometry m_odometry;

    public TestFalcon() {
        super(leftFalconMaster, leftFalconSlaves, rightFalconMaster, rightFalconSlaves, m_gyro,
                Constants.ENCODER_DISTANCE_PER_PULSE);
        m_xEntry = NetworkTableInstance.getDefault().getTable("troubleshooting").getEntry("X");
        m_yEntry = NetworkTableInstance.getDefault().getTable("troubleshooting").getEntry("Y");

        m_gyro.configFactoryDefault();
        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    }

    public void periodic() {
        // Update the odometry in the periodic block
        m_odometry.update(Rotation2d.fromDegrees(getHeading()), getLeftDistance(), getRightDistance());

        var translation = m_odometry.getPoseMeters().getTranslation();
        m_xEntry.setNumber(translation.getX());
        m_yEntry.setNumber(translation.getY());
        System.out.println("X = "+translation.getX());
        System.out.println("Y = "+translation.getY());
    }
}
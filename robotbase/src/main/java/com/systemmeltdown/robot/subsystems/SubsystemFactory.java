package com.systemmeltdown.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedTalonDriveSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem.PipelineIndex;
import com.systemmeltdown.robotlib.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Solenoid;

import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedFalconDriveSubsystem;

import frc.robot.Constants;

/**
 * This class is a factory that creates subsystems.
 * 
 * @category Subsystems
 */
public class SubsystemFactory {
    /**
     * Constructor
     */
    public SubsystemFactory() {
    }

    public SingleSpeedTalonDriveSubsystem CreateSingleSpeedTalonDriveSubsystem() {
        SingleSpeedTalonDriveSubsystem.Configuration config = new SingleSpeedTalonDriveSubsystem.Configuration();
        config.m_isRightInverted = true;

        WPI_TalonSRX leftTalonMaster = new WPI_TalonSRX(Constants.DRIVE_MOTOR_LEFT_1);
        WPI_TalonSRX[] leftTalonSlaves = new WPI_TalonSRX[] { new WPI_TalonSRX(Constants.DRIVE_MOTOR_LEFT_2) };
        WPI_TalonSRX rightTalonMaster = new WPI_TalonSRX(Constants.DRIVE_MOTOR_RIGHT_1);
        WPI_TalonSRX[] rightTalonSlaves = new WPI_TalonSRX[] { new WPI_TalonSRX(Constants.DRIVE_MOTOR_RIGHT_2) };
        SingleSpeedTalonDriveSubsystem subsystem = new SingleSpeedTalonDriveSubsystem(leftTalonMaster, leftTalonSlaves,
                rightTalonMaster, rightTalonSlaves);
        subsystem.configure(config);
        return subsystem;
    }

    public SingleSpeedFalconDriveSubsystem CreateSingleSpeedFalconDriveSubsystem() {
        SingleSpeedFalconDriveSubsystem.Configuration config = new SingleSpeedFalconDriveSubsystem.Configuration();
        config.m_isRightInverted = true;

        WPI_TalonFX leftFalconMaster = new WPI_TalonFX(Constants.DRIVE_MOTOR_LEFT_1);
        WPI_TalonFX[] leftFalconSlaves = new WPI_TalonFX[] { new WPI_TalonFX(Constants.DRIVE_MOTOR_LEFT_2) };
        WPI_TalonFX rightFalconMaster = new WPI_TalonFX(Constants.DRIVE_MOTOR_RIGHT_1);
        WPI_TalonFX rightFalconSlave1 =  new WPI_TalonFX(Constants.DRIVE_MOTOR_RIGHT_2) ;
        rightFalconSlave1.setInverted(true);
        WPI_TalonFX[] rightFalconSlaves = new WPI_TalonFX[] { rightFalconSlave1 };
        SingleSpeedFalconDriveSubsystem subsystem = new SingleSpeedFalconDriveSubsystem(leftFalconMaster,
                leftFalconSlaves, rightFalconMaster, rightFalconSlaves);
        subsystem.configure(config);
        return subsystem;
    }

    public FalconTrajectoryDriveSubsystem CreateFalconTrajectoryDriveSubsystem() {
        FalconTrajectoryDriveSubsystem.Configuration config = new FalconTrajectoryDriveSubsystem.Configuration();
        config.m_isRightInverted = true;

        WPI_TalonFX leftFalconMaster = new WPI_TalonFX(Constants.DRIVE_MOTOR_LEFT_1);
        WPI_TalonFX[] leftFalconSlaves = new WPI_TalonFX[] { new WPI_TalonFX(Constants.DRIVE_MOTOR_LEFT_2) };
        WPI_TalonFX rightFalconMaster = new WPI_TalonFX(Constants.DRIVE_MOTOR_RIGHT_1);
        WPI_TalonFX[] rightFalconSlaves = new WPI_TalonFX[] { new WPI_TalonFX(Constants.DRIVE_MOTOR_RIGHT_2) };
        PigeonIMU gyro = new PigeonIMU(Constants.GYRO_ID);
        FalconTrajectoryDriveSubsystem subsystem = new FalconTrajectoryDriveSubsystem(leftFalconMaster,
                leftFalconSlaves, rightFalconMaster, rightFalconSlaves, gyro, Constants.ENCODER_DISTANCE_PER_PULSE);
        subsystem.configure(config);
        return subsystem;
    }

    public ClimbSubsystem CreateClimbSubsystem() {
        Solenoid solenoid = new Solenoid(Constants.SCISSOR_SOLENOID_LEFT);
        //CANSparkMax winchMotor = new CANSparkMax(Constants.WINCH_MOTOR, MotorType.kBrushless);
        CANSparkMax winchMotor = null;
        ClimbSubsystem subsystem = new ClimbSubsystem(solenoid, winchMotor);

        ClimbSubsystem.Configuration config = new ClimbSubsystem.Configuration();
        subsystem.setConfiguration(config);

        return subsystem;
    }

    public ControlPanelSubsystem CreateControlPanelSub() {
        // Need device IDs
        throw new UnsupportedOperationException();
        /*
         * ControlPanelSub subsystem = new ControlPanelSub(-1, -1); return subsystem;
         */
    }

    public FeederSubsystem CreateFeederSubsystem() {
        FeederSubsystem subsystem = new FeederSubsystem(Constants.FEEDER_MOTOR, Constants.FEED_SENSOR_CHANNEL);
        return subsystem;
    }

    public IntakeSubsystem CreateIntakeSubsystem() {
        IntakeSubsystem subsystem = new IntakeSubsystem(Constants.INTAKE_MOTOR_ID,
                Constants.INTAKE_SOLENOID_CHANNEL_FORWARD, Constants.INTAKE_SOLENOID_CHANNEL_REVERSE);
        return subsystem;
    }

    public StorageSubsystem CreateStorageSubsystem() {
        StorageSubsystem.Configuration config = new StorageSubsystem.Configuration();
        config.m_distancePerRotationInches = Constants.STORAGE_DISTANCE_PER_ROTATION_DEGREES;

        WPI_TalonSRX rotationMotor = new WPI_TalonSRX(Constants.STORAGE_CAROUSEL_MOTOR);
        DutyCycleEncoder throughBoreEncoder = new DutyCycleEncoder(Constants.STORAGE_CAROUSEL_ENCODER_CHANNEL);
        StorageSubsystem subsystem = new StorageSubsystem(rotationMotor, throughBoreEncoder);

        subsystem.configure(config);
        return subsystem;
    }

    public ShooterSubsystem CreateShooterSubsystem() {
        ShooterSubsystem subsystem = new ShooterSubsystem(Constants.SHOOT_MOTOR_1, Constants.SHOOT_MOTOR_2);
        // TODO: add more config if needed
        return subsystem;
    }

    public TurretSubsystem CreateTurretSubsystem() {
        PWM rotateServo = new PWM(Constants.TURRET_ROTATE_MOTOR);
        TurretSubsystem subsystem = new TurretSubsystem(rotateServo);
        TurretSubsystem.Configuration config = new TurretSubsystem.Configuration();
        subsystem.setConfiguration(config);
        return subsystem;
    }

    public TogglableLimelightSubsystem CreateLimelightSubsystem() {
        TogglableLimelightSubsystem subsystem = new TogglableLimelightSubsystem(false);
        subsystem.setPipeline(PipelineIndex.HUMAN_VIEW);
        subsystem.setStream(false);
        LimelightSubsystem.Configuration config = new LimelightSubsystem.Configuration();
        config.m_LimelightMountingAngle = Constants.LIMELIGHT_MOUNTING_ANGLE;
        config.m_LimelightMountingHeightInches = Constants.LIMELIGHT_MOUNTING_HEIGHT;
        config.m_TargetWidth = Constants.VISION_TARGET_WIDTH;
        config.m_TargetHeight = Constants.VISION_TARGET_HEIGHT;
        subsystem.setConfiguration(config);
        return subsystem;
    }
}
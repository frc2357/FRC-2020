package com.systemmeltdown.robot.subsystems;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedFalconDriveSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedTalonDriveSubsystem;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.Constants;

/**
 * This class is a factory that creates subsystems.
 */
public class SubsystemFactory
{
    Map<String, Object> m_configMap;

    /**
     * Constructor
     * 
     * @param configMap configuration for subsystems
     */
    public SubsystemFactory(Map<String, Object> configMap)
    {
        m_configMap = configMap;
    }

    public SingleSpeedTalonDriveSubsystem CreateSingleSpeedTalonDriveSubsystem()
    {

        WPI_TalonSRX leftMasterTalon = new WPI_TalonSRX(Constants.DRIVE_MOTOR_LEFT_1);
        WPI_TalonSRX[] leftSlaveTalons = {new WPI_TalonSRX(Constants.DRIVE_MOTOR_LEFT_2)};
        WPI_TalonSRX rightMasterTalon = new WPI_TalonSRX(Constants.DRIVE_MOTOR_RIGHT_1);
        WPI_TalonSRX[] rightSlaveTalons = {new WPI_TalonSRX(Constants.DRIVE_MOTOR_RIGHT_2)};
        SingleSpeedTalonDriveSubsystem subsystem = new SingleSpeedTalonDriveSubsystem(leftMasterTalon, leftSlaveTalons, rightMasterTalon, rightSlaveTalons);
        subsystem.configure(m_configMap);
        return subsystem;
    }

    public SingleSpeedFalconDriveSubsystem CreateSingleSpeedFalconDriveSubsystem()
    {
        WPI_TalonFX leftMasterFalcon = new WPI_TalonFX(Constants.DRIVE_MOTOR_LEFT_1);
        WPI_TalonFX[] leftSlaveFalcons = {new WPI_TalonFX(Constants.DRIVE_MOTOR_LEFT_2)};
        WPI_TalonFX rightMasterFalcon = new WPI_TalonFX(Constants.DRIVE_MOTOR_RIGHT_1);
        WPI_TalonFX[] rightSlaveFalcons = {new WPI_TalonFX(Constants.DRIVE_MOTOR_RIGHT_2)};
        SingleSpeedFalconDriveSubsystem subsystem = new SingleSpeedFalconDriveSubsystem(leftMasterFalcon, leftSlaveFalcons, rightMasterFalcon, rightSlaveFalcons);
        subsystem.configure(m_configMap);
        return subsystem;
    }

    public ClimbSubsystem CreateClimbSubsystem()
    {
        // Need device IDs
        throw new UnsupportedOperationException();
        /*
        Solenoid solenoid = new Solenoid(-1);
        TalonGroup rightTalonGroup = new TalonGroup(-1, new int[]{});
        TalonGroup leftTalonGroup = new TalonGroup(-1, new int[]{});
        PigeonIMU imu = new PigeonIMU(-1);
        ClimbSubsystem subsystem = new ClimbSubsystem(solenoid, rightTalonGroup, leftTalonGroup, imu);
        return subsystem;
        */
    }

    public ControlPanelSub CreateControlPanelSub()
    {
        // Need device IDs
        throw new UnsupportedOperationException();
        /*
        ControlPanelSub subsystem = new ControlPanelSub(-1, -1);
        return subsystem;
        */
    }

    public IntakeSub CreateIntakeSub()
    {
        // Need device IDs
        throw new UnsupportedOperationException();
        /*
        IntakeSub subsystem = new IntakeSub(-1, -1);
        return subsystem;
        */
    }

    public ShooterSubsystem CreateShooterSubsystem()
    {
        // Need device IDs
        throw new UnsupportedOperationException();
        /*
        ShooterSubsystem subsystem = new ShooterSubsystem(-1);
        return subsystem;
        */
    }

    public TurretSubsystem CreateTurretSubsystem()
    {
        // Need device IDs
        throw new UnsupportedOperationException();
        /*
        TurretSubsystem subsystem = new TurretSubsystem(-1);
        return subsystem;
        */
    }
}
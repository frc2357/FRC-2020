package com.systemmeltdown.robot.subsystems;

import java.util.Map;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedTalonDriveSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedFalconDriveSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.controllerGroups.TalonGroup;
import com.systemmeltdown.robotlib.subsystems.drive.controllerGroups.FalconGroup;
import edu.wpi.first.wpilibj.Solenoid;
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
        TalonGroup rightTalonGroup = new TalonGroup(Constants.DRIVE_MOTOR_RIGHT_1, Constants.DRIVE_MOTOR_RIGHT_SLAVES);
        TalonGroup lefTalonGroup = new TalonGroup(Constants.DRIVE_MOTOR_LEFT_1, Constants.DRIVE_MOTOR_LEFT_SLAVES);
        SingleSpeedTalonDriveSubsystem subsystem = new SingleSpeedTalonDriveSubsystem(rightTalonGroup, lefTalonGroup);
        subsystem.configure(m_configMap);
        return subsystem;
    }

    public SingleSpeedFalconDriveSubsystem CreateSingleSpeedFalconDriveSubsystem()
    {
        FalconGroup rightFalconGroup = new FalconGroup(Constants.DRIVE_MOTOR_RIGHT_1, Constants.DRIVE_MOTOR_RIGHT_SLAVES);
        FalconGroup leftFalconGroup = new FalconGroup(Constants.DRIVE_MOTOR_LEFT_1, Constants.DRIVE_MOTOR_LEFT_SLAVES);
        SingleSpeedFalconDriveSubsystem subsystem = new SingleSpeedFalconDriveSubsystem(rightFalconGroup, leftFalconGroup);
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
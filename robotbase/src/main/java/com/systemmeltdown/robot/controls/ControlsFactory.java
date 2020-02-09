package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robot.commands.IntakePickupBallCommand;
import com.systemmeltdown.robot.commands.IntakeToggleDirectionCommand;
import com.systemmeltdown.robot.commands.InvertDriveCommand;
import com.systemmeltdown.robot.commands.ShootCommand;
import com.systemmeltdown.robot.commands.VisionChangePipelineCommand;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.ControlPanelSubsystem;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;
import com.systemmeltdown.robotlib.commands.DriveProportionalCommand;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

public class ControlsFactory {
    private ClimbSubsystem climbSub = null;
    private ControlPanelSubsystem controlPanelSub = null;
    private FeederSubsystem feederSub = null;
    private IntakeSubsystem intakeSub = null;
    private ShooterSubsystem shooterSub = null;
    private StorageSubsystem storageSub = null;
    private TogglableLimelightSubsystem visionSub = null;
    private TurretSubsystem turretSub = null;
    private FalconTrajectoryDriveSubsystem driveSub = null;

    public ControlsFactory(ClosedLoopSubsystem[] subsystems) {
        for (ClosedLoopSubsystem subsystem : subsystems) {
            if (subsystem.getClass() == ClimbSubsystem.class) {
                climbSub = (ClimbSubsystem) subsystem;
            }
            if (subsystem.getClass() == ControlPanelSubsystem.class) {
                controlPanelSub = (ControlPanelSubsystem) subsystem;
            }
            if (subsystem.getClass() == FeederSubsystem.class) {
                feederSub = (FeederSubsystem) subsystem;
            }
            if (subsystem.getClass() == IntakeSubsystem.class) {
                intakeSub = (IntakeSubsystem) subsystem;
            }
            if (subsystem.getClass() == ShooterSubsystem.class) {
                shooterSub = (ShooterSubsystem) subsystem;
            }
            if (subsystem.getClass() == StorageSubsystem.class) {
                storageSub = (StorageSubsystem) subsystem;
            }
            if (subsystem.getClass() == TogglableLimelightSubsystem.class) {
                visionSub = (TogglableLimelightSubsystem) subsystem;
            }
            if (subsystem.getClass() == TurretSubsystem.class) {
                turretSub = (TurretSubsystem) subsystem;
            }
            if (subsystem.getClass() == FalconTrajectoryDriveSubsystem.class) {
                driveSub = (FalconTrajectoryDriveSubsystem) subsystem;
            }
        }
    }

    public InvertDriveControls buildDriveControls(InvertDriveControls driverControls) {
        if (climbSub != null) {

        }
        if (controlPanelSub != null) {

        }
        if (driveSub != null) {
            driveSub.setDefaultCommand(new DriveProportionalCommand(driveSub, driverControls));
            if (visionSub != null) {
                driverControls.m_invertButton.whenPressed(new InvertDriveCommand(visionSub, driverControls));
            }
        }
        if (feederSub != null) {

        }
        if (intakeSub != null) {

        }
        if (shooterSub != null) {

        }
        if (storageSub != null) {

        }
        if (turretSub != null) {

        }
        if (visionSub != null) {
            driverControls.m_changePipelineButton.whileHeld(new VisionChangePipelineCommand(visionSub));
        }
        return driverControls;
    }

    public GunnerControls buildGunnerControls(GunnerControls gunnerControls) {
        if (climbSub != null) {

        }
        if (controlPanelSub != null) {

        }
        if (driveSub != null) {

        }
        if (feederSub != null) {

        }
        if (intakeSub != null) {
            gunnerControls.m_leftTrigger.whileActiveContinuous(new IntakePickupBallCommand(intakeSub, gunnerControls));
            gunnerControls.m_yButton.whenPressed(new IntakeToggleDirectionCommand(intakeSub));
        }
        if (shooterSub != null) {
            gunnerControls.m_rightTrigger.whileActiveContinuous(new ShootCommand(shooterSub, gunnerControls));
        }
        if (storageSub != null) {

        }
        if (turretSub != null) {

        }
        if (visionSub != null) {
        }
        return gunnerControls;
    }
}
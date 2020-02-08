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
    private InvertDriveControls m_driverControls;
    private GunnerControls m_gunnerControls;

    private ClimbSubsystem climbSub = null;
    private ControlPanelSubsystem controlPanelSub = null;
    private FeederSubsystem feederSub = null;
    private IntakeSubsystem intakeSub = null;
    private ShooterSubsystem shooterSub = null;
    private StorageSubsystem storageSub = null;
    private TogglableLimelightSubsystem visionSub = null;
    private TurretSubsystem turretSub = null;
    private FalconTrajectoryDriveSubsystem driveSub = null;


    public ControlsFactory(InvertDriveControls driverControls, GunnerControls gunnerControls, ClosedLoopSubsystem[] subsystems) {
        m_driverControls = driverControls;
        m_gunnerControls = gunnerControls;
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

    public InvertDriveControls buildDriveControls() {
        if (climbSub != null) {

        }
        if (controlPanelSub != null) {

        }
        if (driveSub != null) {
            driveSub.setDefaultCommand(new DriveProportionalCommand(driveSub, m_driverControls));
            if (visionSub != null) {
                this.m_driverControls.m_invertButton
                        .whenPressed(new InvertDriveCommand(visionSub, this.m_driverControls));
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
            this.m_driverControls.m_changePipelineButton
                    .whileHeld(new VisionChangePipelineCommand(visionSub));
        }
        return this.m_driverControls;
    }

    public GunnerControls buildGunnerControls() {
        if (climbSub != null) {

        }
        if (controlPanelSub != null) {

        }
        if (driveSub != null) {

        }
        if (feederSub != null) {

        }
        if (intakeSub != null) {
            this.m_gunnerControls.m_leftTrigger
                    .whileActiveContinuous(new IntakePickupBallCommand(intakeSub, this.m_gunnerControls));
            this.m_gunnerControls.m_yButton.whenPressed(new IntakeToggleDirectionCommand(intakeSub));
        }
        if (shooterSub != null) {
            this.m_gunnerControls.m_rightTrigger
                    .whileActiveContinuous(new ShootCommand(shooterSub, this.m_gunnerControls));
        }
        if (storageSub != null) {

        }
        if (turretSub != null) {

        }
        if (visionSub != null) {
        }
        return this.m_gunnerControls;
    }
}
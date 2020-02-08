package com.systemmeltdown.robot.controls;

import java.util.Map;

import com.systemmeltdown.robot.commands.IntakePickupBallCommand;
import com.systemmeltdown.robot.commands.IntakeToggleDirectionCommand;
import com.systemmeltdown.robot.commands.InvertDriveCommand;
import com.systemmeltdown.robot.commands.ShootCommand;
import com.systemmeltdown.robot.commands.VisionChangePipelineCommand;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.ControlPanelSub;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.IntakeSub;
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

    public ControlsFactory(InvertDriveControls driverControls, GunnerControls gunnerControls) {
        this.m_driverControls = driverControls;
        this.m_gunnerControls = gunnerControls;
    }

    public InvertDriveControls buildDriveControls(Map<String, ClosedLoopSubsystem> subsystems) {
        if (subsystems.containsKey("ClimbSub")) {

        }
        if (subsystems.containsKey("ControlPanelSub")) {

        }
        if (subsystems.containsKey("DriveSub")) {
            FalconTrajectoryDriveSubsystem driveSub = (FalconTrajectoryDriveSubsystem) subsystems.get("DriveSub");
            // driveSub.setDefaultCommand(new DriveProportionalCommand(driveSub, m_driverControls));
            if (subsystems.containsKey("VisionSub")) {
                TogglableLimelightSubsystem visionSub = (TogglableLimelightSubsystem) subsystems.get("VisionSub");
                this.m_driverControls.m_invertButton
                        .whenPressed(new InvertDriveCommand(visionSub, this.m_driverControls));
            }
        }
        if (subsystems.containsKey("FeederSub")) {

        }
        if (subsystems.containsKey("IntakeSub")) {

        }
        if (subsystems.containsKey("ShooterSub")) {

        }
        if (subsystems.containsKey("StorageSub")) {

        }
        if (subsystems.containsKey("TurretSub")) {

        }
        if (subsystems.containsKey("VisionSub")) {
            TogglableLimelightSubsystem visionSub = (TogglableLimelightSubsystem) subsystems.get("VisionSub");
            this.m_driverControls.m_changePipelineButton.whileHeld(new VisionChangePipelineCommand(visionSub));
        }
        return this.m_driverControls;
    }

    public GunnerControls buildGunnerControls(Map<String, ClosedLoopSubsystem> subsystems) {
        if (subsystems.containsKey("ClimbSub")) {

        }
        if (subsystems.containsKey("ControlPanelSub")) {

        }
        if (subsystems.containsKey("DriveSub")) {

        }
        if (subsystems.containsKey("FeederSub")) {

        }
        if (subsystems.containsKey("IntakeSub")) {
            IntakeSub intakeSub = (IntakeSub) subsystems.get("IntakeSub");
            this.m_gunnerControls.m_leftTrigger
                    .whileActiveContinuous(new IntakePickupBallCommand(intakeSub, this.m_gunnerControls));
            this.m_gunnerControls.m_yButton.whenPressed(new IntakeToggleDirectionCommand(intakeSub));
        }
        if (subsystems.containsKey("ShooterSub")) {
            ShooterSubsystem shooterSub = (ShooterSubsystem) subsystems.get("ShooterSub");
            this.m_gunnerControls.m_rightTrigger
                    .whileActiveContinuous(new ShootCommand(shooterSub, this.m_gunnerControls));
        }
        if (subsystems.containsKey("StorageSub")) {

        }
        if (subsystems.containsKey("TurretSUb")) {

        }
        if (subsystems.containsKey("VisionSub")) {

        }
        return this.m_gunnerControls;
    }
}
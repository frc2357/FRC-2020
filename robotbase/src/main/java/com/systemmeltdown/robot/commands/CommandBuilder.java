package com.systemmeltdown.robot.commands;

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
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

public class CommandBuilder {
    private FalconTrajectoryDriveSubsystem m_driveSub;
    private TogglableLimelightSubsystem m_visionSub;
    private InvertDriveControls m_driverControls;
    private GunnerControls m_gunnerControls;
    private ClimbSubsystem m_climbSub;
    private ControlPanelSub m_controlPanelSub;
    private FeederSubsystem m_feederSub;
    private IntakeSub m_intakeSub;
    private ShooterSubsystem m_shooterSub;
    private StorageSubsystem m_storageSub;
    private TurretSubsystem m_turretSub;

    public CommandBuilder(InvertDriveControls driverControls, GunnerControls gunnerControls) {
        m_driverControls = driverControls;
        m_gunnerControls = gunnerControls;
    }

    public CommandBuilder withDriveSub(FalconTrajectoryDriveSubsystem driveSub) {
        m_driveSub = driveSub;
        return this;
    }

    public CommandBuilder withClimbSub(ClimbSubsystem climbSub) {
        m_climbSub = climbSub;
        return this;
    }

    public CommandBuilder withControlPanelSub(ControlPanelSub controlPanelSub) {
        m_controlPanelSub = controlPanelSub;
        return this;
    }

    public CommandBuilder withFeederSub(FeederSubsystem feederSub) {
        m_feederSub = feederSub;
        return this;
    }

    public CommandBuilder withIntakeSub(IntakeSub intakeSub) {
        m_intakeSub = intakeSub;
        return this;
    }

    public CommandBuilder withShooterSub(ShooterSubsystem shooterSubsystem) {
        m_shooterSub = shooterSubsystem;
        return this;
    }

    public CommandBuilder withStorageSub(StorageSubsystem storageSub) {
        m_storageSub = storageSub;
        return this;
    }

    public CommandBuilder withTurretSub(TurretSubsystem turretSub) {
        m_turretSub = turretSub;
        return this;
    }

    public CommandBuilder withVisionSub(TogglableLimelightSubsystem visonSub) {
        m_visionSub = visonSub;
        return this;
    }

    public void build() {
        if (m_climbSub != null) {

        }
        if (m_controlPanelSub != null) {

        }
        if (m_driveSub != null) {

            if (m_visionSub != null) {
                m_driverControls.m_invertButton.whenPressed(new InvertDriveCommand(m_visionSub, m_driverControls));
            }
        }
        if (m_feederSub != null) {

        }
        if (m_intakeSub != null) {
            m_gunnerControls.m_leftTrigger
                    .whileActiveContinuous(new IntakePickupBallCommand(m_intakeSub, m_gunnerControls));
            m_gunnerControls.m_yButton.whenPressed(new IntakeToggleDirectionCommand(m_intakeSub));
        }
        if (m_shooterSub != null) {
            m_gunnerControls.m_rightTrigger.whileActiveContinuous(new ShootCommand(m_shooterSub, m_gunnerControls));
        }
        if (m_storageSub != null) {

        }
        if (m_turretSub != null) {

        }
        if (m_visionSub != null) {
            m_driverControls.m_changePipelineButton.whileHeld(new VisionChangePipelineCommand(m_visionSub));
        }
    }
}
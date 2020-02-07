package com.systemmeltdown.robot.shuffleboard;

import java.util.ArrayList;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.ControlPanelSub;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.IntakeSub;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

public class ShuffleboardBuilder {
    private FalconTrajectoryDriveSubsystem m_driveSub;
    private ClimbSubsystem m_climbSub;
    private ControlPanelSub m_controlPanelSub;
    private FeederSubsystem m_feederSub;
    private IntakeSub m_intakeSub;
    private ShooterSubsystem m_shooterSub;
    private StorageSubsystem m_storageSub;
    private TurretSubsystem m_turretSub;
    private TogglableLimelightSubsystem m_visionSub;

    public ShuffleboardBuilder withDriveSub(FalconTrajectoryDriveSubsystem driveSub) {
        m_driveSub = driveSub;
        return this;
    }

    public ShuffleboardBuilder withClimbSub(ClimbSubsystem climbSub) {
        m_climbSub = climbSub;
        return this;
    }

    public ShuffleboardBuilder withControlPanelSub(ControlPanelSub controlPanelSub) {
        m_controlPanelSub = controlPanelSub;
        return this;
    }

    public ShuffleboardBuilder withFeederSub(FeederSubsystem feederSub) {
        m_feederSub = feederSub;
        return this;
    }

    public ShuffleboardBuilder withIntakeSub(IntakeSub intakeSub) {
        m_intakeSub = intakeSub;
        return this;
    }

    public ShuffleboardBuilder withShooterSub(ShooterSubsystem shooterSubsystem) {
        m_shooterSub = shooterSubsystem;
        return this;
    }

    public ShuffleboardBuilder withStorageSub(StorageSubsystem storageSub) {
        m_storageSub = storageSub;
        return this;
    }

    public ShuffleboardBuilder withTurretSub(TurretSubsystem turretSub) {
        m_turretSub = turretSub;
        return this;
    }

    public ShuffleboardBuilder withVisionSub(TogglableLimelightSubsystem visonSub) {
        m_visionSub = visonSub;
        return this;
    }

    public void build() {
        ArrayList<ClosedLoopSubsystem> subsystems = new ArrayList<>();
        if (m_climbSub != null) {
            subsystems.add(m_climbSub);
        }
        if (m_controlPanelSub != null) {
            subsystems.add(m_controlPanelSub);
        }
        if (m_driveSub != null) {
            subsystems.add(m_driveSub);
        }
        if (m_feederSub != null) {
            subsystems.add(m_feederSub);
        }
        if (m_intakeSub != null) {
            subsystems.add(m_intakeSub);
        }
        if (m_shooterSub != null) {
            subsystems.add(m_shooterSub);
        }
        if (m_storageSub != null) {
            subsystems.add(m_storageSub);
            new CellNumberWidget("Robot", m_storageSub);
        }
        if (m_turretSub != null) {
            subsystems.add(m_turretSub);
        }
        if (m_visionSub != null) {
            subsystems.add(m_visionSub);
        }
        if (!subsystems.isEmpty()) {
            new FailsafeButtonWidget("tabTitle", subsystems);
        }
        for (int i = 0; i < 4; i++) {
            new AutoWaitTimeAndChooser("AUTO", i);
        }
    }
}
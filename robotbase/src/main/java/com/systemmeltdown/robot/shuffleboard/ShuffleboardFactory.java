package com.systemmeltdown.robot.shuffleboard;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.ControlPanelSubsystem;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

public class ShuffleboardFactory {
    public void build(ClosedLoopSubsystem[] subsystems) {
        for (ClosedLoopSubsystem subsystem : subsystems) {
            if (subsystem == null) {
                System.err.println("Null subsystem.");
            } else if (subsystem.getClass() == ClimbSubsystem.class) {

            } else if (subsystem.getClass() == ControlPanelSubsystem.class) {

            } else if (subsystem.getClass() == FeederSubsystem.class) {

            } else if (subsystem.getClass() == IntakeSubsystem.class) {

            } else if (subsystem.getClass() == ShooterSubsystem.class) {

            } else if (subsystem.getClass() == StorageSubsystem.class) {
                new CellNumberWidget("Robot", (StorageSubsystem) subsystem);
            } else if (subsystem.getClass() == TogglableLimelightSubsystem.class) {

            } else if (subsystem.getClass() == TurretSubsystem.class) {

            } else if (subsystem.getClass() == FalconTrajectoryDriveSubsystem.class) {

            } else {
                System.err.println("Unrecognized subsystem.");
            }
        }
        if (subsystems.length > 0) {
            new FailsafeButtonWidget("Robot", subsystems);
        }
        for (int i = 0; i < 4; i++) {
            new AutoWaitTimeAndChooser("AUTO", i);
        }
    }
}
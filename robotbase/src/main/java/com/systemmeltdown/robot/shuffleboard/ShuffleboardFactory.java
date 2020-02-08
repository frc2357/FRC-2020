package com.systemmeltdown.robot.shuffleboard;

import java.util.ArrayList;
import java.util.Map;

import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;

public class ShuffleboardFactory {
    public void build(Map<String, ClosedLoopSubsystem> subsystems) {
        
        if (subsystems.containsKey("ClimbSub")) {

        }
        if (subsystems.containsKey("ControlPanelSub")) {

        }
        if (subsystems.containsKey("DriveSub")) {
            
        }
        if (subsystems.containsKey("FeederSub")) {

        }
        if (subsystems.containsKey("IntakeSub")) {

        }
        if (subsystems.containsKey("ShooterSub")) {

        }
        if (subsystems.containsKey("StorageSub")) {
            new CellNumberWidget("Robot", (StorageSubsystem) subsystems.get("StorageSub"));
        }
        if (subsystems.containsKey("TurretSub")) {

        }
        if (subsystems.containsKey("VisionSub")) {

        }  
        if (!subsystems.isEmpty()) {
            new FailsafeButtonWidget("Robot", new ArrayList<ClosedLoopSubsystem>(subsystems.values()));
        }
        for (int i = 0; i < 4; i++) {
            new AutoWaitTimeAndChooser("AUTO", i);
        }
    }
}
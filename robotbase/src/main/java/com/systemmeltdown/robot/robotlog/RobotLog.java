package com.systemmeltdown.robot.robotlog;

import com.systemmeltdown.robotlog.topics.StringTopic;

import edu.wpi.first.wpilibj2.command.Command;

class RobotLog {

    //=====================
    //   CLIMB SUBSYSTEM
    //=====================
    public static class ClimbSubLogging {
        public static final StringTopic climbSubErrorTopic = new StringTopic("Climb Sub Error");
        public static final StringTopic climbSubInfoTopic = new StringTopic("Climb Sub Info");
        public static final StringTopic climbSubDebugTopic = new StringTopic("Climb Sub Debug");

        private static final StringTopic climbSubCommandTopic = new StringTopic("ClimbSub Commands");

        public static void logCommand(Command cmd) {
            climbSubCommandTopic.log(cmd.getName());
        }
    }

    //===============================
    //   CONTROL PANEL SUBSYSTEM
    //===============================
    public static class ControlPanelSubLogging {
        public static final StringTopic controlPanelSubErrorTopic = 
            new StringTopic("Control Panel Sub Error");
            
        public static final StringTopic controlPanelSubInfoTopic = 
            new StringTopic("Control Panel Sub Info");

        public static final StringTopic controlPanelSubDebugTopic = 
            new StringTopic("Control Panel Sub Debug");


        private static final StringTopic controlPanelSubCommandTopic = 
            new StringTopic("Control Panel Sub Commands");

        public static void logCommand(Command cmd) {
            controlPanelSubCommandTopic.log(cmd.getName());
        }
    }

    //========================
    //   FEEDER SUBSYSTEM
    //========================

    public static class FeederSubLogging {
        public static final StringTopic feederSubErrorTopic   = new StringTopic("Feeder Sub Error");
        public static final StringTopic feederSubInfoTopic    = new StringTopic("Feeder Sub Info");
        public static final StringTopic feederSubDebugTopic   = new StringTopic("Feeder Sub Debug");

        private static final StringTopic feederSubCommandTopic= new StringTopic("Feeder Sub Commands");

        public static void logCommand(Command cmd) {
            feederSubCommandTopic.log(cmd.getName());
        }
    }

    //=====================
    //  INTAKE SUBSYSTEM
    //=====================

    public static class IntakeSubLogging {
        public static final StringTopic intakeSubErrorTopic    = new StringTopic("Intake Sub Error");
        public static final StringTopic intakeSubInfoTopic     = new StringTopic("Intake Sub Info");
        public static final StringTopic intakeSubDebugTopic    = new StringTopic("Intake Sub Debug");

        private static final StringTopic intakeSubCommandTopic = new StringTopic("Intake Sub Commands");

        public static void logCommand(Command cmd) {
            intakeSubCommandTopic.log(cmd.getName());
        }
    }

    //====================
    // SHOOTER SUBSYSTEM
    //====================

    public static class ShooterSubLogging {
        public static final StringTopic shooterSubErrorTopic = new StringTopic("Shooter Sub Error");
        public static final StringTopic shooterSubInfoTopic  = new StringTopic("Shooter Sub Info");
        public static final StringTopic shooterSubDebugTopic = new StringTopic("Shooter Sub Debug");

        private static final StringTopic shooterSubCommandTopic = new StringTopic("Shooter Sub Commands");

        public static void logCommand(Command cmd) {
            shooterSubCommandTopic.log(cmd.getName());
        }
    }

    //=======================
    //   STORAGE SUBSYSTEM
    //=======================

    public static class StorageSubLogging {
        public static final StringTopic storageSubErrorTopic = new StringTopic("Storage Sub Error");
        public static final StringTopic storageSubInfoTopic  = new StringTopic("Storage Sub Info");
        public static final StringTopic storageSubDebugTopic = new StringTopic("Storage Sub Debug");

        private static final StringTopic storageSubCommandTopic = new StringTopic("Storage Sub Commands");

        public static void logCommand(Command cmd) {
            storageSubCommandTopic.log(cmd.getName());
        }
    }

    //====================================
    //   TOGGLABLE LIMELIGHT SUBSYSTEM
    //====================================

    public static class TogglableLimelightSubLogging {
        public static final StringTopic togglableLimelightSubErrorTopic = 
            new StringTopic("Togglable Limelight Sub Error");
        public static final StringTopic togglableLimelightSubInfoTopic  = 
            new StringTopic("Togglable Limelight Sub Info");
        public static final StringTopic togglableLimelightSubDebug =
            new StringTopic("Togglable Limelight Sub Debug");

        private static final StringTopic togglableLimelightSubCommand =
            new StringTopic("Togglable Limelight Sub Commands");
        
        public static void logCommand(Command cmd) {
            togglableLimelightSubCommand.log(cmd.getName());
        }
    }

    //=====================
    //  TURRET SUBSYSTEM
    //=====================

    public static class TurretSubLogging {
        public static final StringTopic turretSubErrorTopic = new StringTopic("Turret Sub Error");
        public static final StringTopic turretSubInfoTopic  = new StringTopic("Turret Sub Info");
        public static final StringTopic turretSubDebugTopic = new StringTopic("Turret Sub Debug");

        private static final StringTopic turretSubCommandTopic = new StringTopic("Turret Sub Commands");

        public static void logCommand(Command cmd) {
            turretSubCommandTopic.log(cmd.getName());
        }
    }
}
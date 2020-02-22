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
        public static final StringTopic controlPanelSubError = 
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
}
package com.systemmeltdown.robotlog.subsystems;

import com.systemmeltdown.robotlog.topics.StringTopic;

import edu.wpi.first.wpilibj2.command.Command;

abstract class SubsystemLogger {
    public static void logError(String str, StringTopic errorTopic) {
        errorTopic.log();
    }

    public static void logError(String str) {
        StringTopic topic = new StringTopic("Default Error");
        topic.log(str);
    }

    public static void logInfo(String str) {
    }

    public static void logDebug(String str) {
    }

    public static void logCommand(Command cmd) {
    }
}
package com.systemmeltdown.robotlog.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem; // Imported for javadoc (for now)
import com.systemmeltdown.robotlog.topics.BooleanTopic;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * A logging class to log the commands used by the {@link ClimbSubsystem}.
 * 
 * @category Climbing
 * 
 * @category Logging
 */
class ClimbCommandsLogger {
    private static BooleanTopic ClimbCommandsTopic = new BooleanTopic("Climb Commands");

    public static void logCommand(Command cmnd, boolean value) {
        ClimbCommandsTopic.log(value);
    }
}
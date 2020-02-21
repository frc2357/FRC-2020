package com.systemmeltdown.robotlog.subsystems;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem; // Imported for javadoc (for now)
import com.systemmeltdown.robotlog.topics.StringTopic;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * A logging class to log the commands used by the {@link ClimbSubsystem}.
 * 
 * @category Climbing
 * 
 * @category Logging
 */
class ClimbCommandsLogger {
    private static StringTopic ClimbCommandsTopic = new StringTopic("Climb Commands");

    public static void logCommand(Command cmnd) {
        ClimbCommandsTopic.log(cmnd.getName());
    }
}
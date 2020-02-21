package com.systemmeltdown.robotlog.subsystems;

import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem; // Imported for javadoc (for now)
import com.systemmeltdown.robotlog.topics.StringTopic;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * A class used for logging the commads used by the {@link TogglableLimelightSubsystem}.
 * 
 * @category Limelight
 * 
 * @category Logging
 */
class TogglableLimelightLogger {
    private static StringTopic ClimbCommandsTopic = new StringTopic("Climb Commands");

    public static void logCommand(Command cmd) {
        ClimbCommandsTopic.log(cmd.getName());
    }
}
package com.systemmeltdown.robotlog.subsystems;

import com.systemmeltdown.robot.subsystems.TurretSubsystem; // Imported for javadoc (for now)

/**
 * A class for logging the commands used by the {@link TurretSubsystem}.
 * 
 * @category Turret
 * 
 * @category Logging
 */
class TurretLogger {
    private static StringTopic ClimbCommandsTopic = new StringTopic("Climb Commands");

    public static void logCommand(Command cmd) {
        ClimbCommandsTopic.log(cmd.getName());
    }
}
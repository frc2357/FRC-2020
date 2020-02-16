package com.systemmeltdown.robot.util;

import frc.robot.Constants;

/**
 * A class containing some useful functions that are not subsystem specific,
 * and might not be quite library worthy.
 */
public class Utility {
    /**
     * Calculate the shooting velocity given the distance to target and shooting angle
     * @param distanceToTarget distance to target, meters
     * @param shootingAngle shooting angle from horizontal, degrees
     * @return shooting velocity, meters per second
     */
    public static double calculateShootingVelocity(double distanceToTarget, double shootingAngle) {
        final double angleRad = Math.toRadians(shootingAngle);
        final double targetHeight = Constants.HIGH_TARGET_HEIGHT - Constants.SHOOTER_HEIGHT;
        final double cosAngle = Math.cos(angleRad);

        double term = -Constants.GRAVITY * distanceToTarget * distanceToTarget / (
            2 * (targetHeight - distanceToTarget * Math.tan(angleRad)) * cosAngle * cosAngle
        );
        return Math.sqrt(term);
    }
}

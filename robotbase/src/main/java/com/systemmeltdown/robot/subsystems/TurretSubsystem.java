package com.systemmeltdown.robot.subsystems;

import com.systemmeltdown.robot.util.VisionTargetSupplier;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlib.subsystems.LimelightSubsystem.VisionTarget;
import com.systemmeltdown.robotlib.util.Utility;
import com.systemmeltdown.robotlog.topics.BooleanTopic;
import com.systemmeltdown.robotlog.topics.StringTopic;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.Constants;

/**
 * Subsystem for the turret.
 * 
 * @category Turret
 * @category Subsystems
 */
public class TurretSubsystem extends ClosedLoopSubsystem {
    private StringTopic m_warningTopic = new StringTopic("Turret Warning");
    private BooleanTopic m_targetedTopic = new BooleanTopic("Targeted");
    private BooleanTopic m_targetLockedTopic = new BooleanTopic("Target Locked");

    private PWM m_rotateServo;
    private PIDController m_horzAimController;
    private VisionTargetSupplier m_targetSupplier;
    private VisionTarget m_currentTarget;

    private Configuration m_configuration;

    static public class Configuration {
        public double m_turretAimP = Constants.TURRET_AIM_P;
        public double m_turretAimI = Constants.TURRET_AIM_I;
        public double m_turretAimD = Constants.TURRET_AIM_D;
        public double m_xTargetSetpoint = 0; // The center of the camera view is zero.
        public double m_xTargetTolerance = Constants.TURRET_AIM_TOLERANCE;
    }

    /**
     * @param rotateServo Servo that rotates the turret.
     * @param hoodServo   Servo controlling the hood.
     */
    public TurretSubsystem(PWM rotateServo) {
        super.setClosedLoopEnabled(false);
        m_rotateServo = rotateServo;
        m_horzAimController = null;
        m_currentTarget = null;

        // add dashboard controls for children
        addChild("rotateServo", m_rotateServo);
        addChild("horzAimPID", m_horzAimController);

        m_targetedTopic.log(false);
        m_targetLockedTopic.log(false);
    }

    public boolean hasTarget() {
        return m_currentTarget != null;
    }

    public boolean isTargetLocked() {
        if (!isClosedLoopEnabled()) {
            return false;
        }
        return m_horzAimController != null && m_horzAimController.atSetpoint();
    }

    @Override
    public void setClosedLoopEnabled(boolean enabled) {
        if (enabled) {
            m_warningTopic.log("Enabling via setClosedLoopEnabled directly not allowed");
            return;
        }
        disableClosedLoop();
    }

    public void enableClosedLoop(VisionTargetSupplier targetSupplier) {
        System.out.println("----ENABLE----");
        m_targetSupplier = targetSupplier;
        m_horzAimController = new PIDController(
            m_configuration.m_turretAimP,
            m_configuration.m_turretAimI,
            m_configuration.m_turretAimD
        );
        m_horzAimController.setSetpoint(m_configuration.m_xTargetSetpoint);
        m_horzAimController.setTolerance(m_configuration.m_xTargetTolerance);

        super.setClosedLoopEnabled(true);
    }

    public void disableClosedLoop() {
        super.setClosedLoopEnabled(false);
        m_targetSupplier = null;
        m_horzAimController = null;
        m_rotateServo.setSpeed(0);
    }

    public void closedLoopPeriodic() {
        m_currentTarget = m_targetSupplier.getAsVisionTarget();

        if (m_currentTarget != null) {
            if (m_horzAimController != null) {
                double measurement = calculateXSimple(m_currentTarget);
                double output = m_horzAimController.calculate(measurement);
                m_rotateServo.setSpeed(Utility.clamp(output, -1, 1));
            }

            m_targetedTopic.log(true);
            if (isTargetLocked()) {
                m_targetLockedTopic.log(false);
            }
        } else {
            m_targetedTopic.log(false);
        }
    }

    @Override
    public void periodic() {
        if (isClosedLoopEnabled()) {
            closedLoopPeriodic();
        }
    }

    /**
     * Set the turret motor control manually.
     * @param percentOutput [-1, 1] positive values turn clockwise when looking
     *                      from the top of the robot
     */
    public void setTurretMotorSpeed(double percentOutput) {
        disableClosedLoop();
        m_rotateServo.setSpeed(percentOutput);
    }

    public void setConfiguration(Configuration configuration) {
        m_configuration = configuration;
    }

    /**
     * This just simply centers on the target based on the x value.
     * @param target The vision target.
     * @return desired 
     */
    private double calculateXSimple(VisionTarget target) {
        return target.getX();
    }

    /**
     * To aim at the hole behind the target, we need the target to be offset from
     * the center of the camera. This function uses the law of cosines to determine
     * the correct offset angle.
     * 
     * TODO check that the target rotation sign is positive to the left of the robot
     * 
     * @param target The target; Type: {@link LimelightSubsystem.VisionTarget}.
     * @return desired vision target offset in degrees
     */
    private double calculateLawOfSinesDesiredXRotationAngle(VisionTarget target) {
        System.out.println("x:" + target.getX());
        System.out.println("y:" + target.getY());
        System.out.println("target rotation degrees:" + target.getTargetRotationDegrees());

        final double theta = Math.toRadians(target.getTargetRotationDegrees());
        System.out.println("theta:" + theta);
        final double targetDist = target.getInchesFromTarget();
        System.out.println("targetDist:" + targetDist);
        final double recessDist = Constants.VISION_DISTANCE_TO_HOLE;
        System.out.println("recessDist:" + recessDist);

        // cos(pi - theta) = -cos(theta)
        double distanceToHoleSq = recessDist * recessDist + targetDist * targetDist
                + 2 * recessDist * targetDist * Math.cos(theta);
        System.out.println("distanceToHoleSq:" + distanceToHoleSq);
        double phi = Math.acos(recessDist * recessDist - distanceToHoleSq - targetDist * targetDist
                + 2 * Math.sqrt(distanceToHoleSq) * targetDist);
        System.out.println("phi:" + phi);
        return Math.toDegrees(phi);
    }

    /**
     * Get the vertical aiming angle for the target distance
     * NOTE: This method is not complete.
     * 
     * @return 0, until the method is complete.
     */
    private double getVerticalTargetAngle(VisionTarget target) {
        // TODO
        return 0;
    }
}
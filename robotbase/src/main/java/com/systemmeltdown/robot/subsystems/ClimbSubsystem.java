package com.systemmeltdown.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlog.topics.BooleanTopic;
import com.systemmeltdown.robotlog.topics.StringTopic;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;

/**
 * This subsystem is responsible for the components used in climbing.
 * 
 * This assumes that the winch motors have a ramp set.
 * 
 * @category Climb
 * @category Subsystems
 */
public class ClimbSubsystem extends ClosedLoopSubsystem {
    private enum ClimbDirection {
        Up, Stop, Release
    }

    public static class Configuration {
        public IdleMode m_winchIdleMode = IdleMode.kBrake;
        public double m_climbSpeed = Constants.CLIMBER_CLIMB_SPEED;
        public double m_releaseSpeed = Constants.CLIMBER_RELEASE_SPEED;
        public int m_winchStallCurrentLimit = Constants.CLIMBER_STALL_LIMIT_AMPS;
        public int m_winchFreeCurrentLimit = Constants.CLIMBER_FREE_LIMIT_AMPS;
    }

    private Solenoid m_scissorExtendSolenoid;
    private CANSparkMax m_winchMotor;

    private ClimbDirection m_climbDirection;
    private Configuration m_config;

    /* RobotLog Topics */
    private final StringTopic m_climbSubInfo = new StringTopic("Climb Sub Info");
    private final BooleanTopic m_climbSubScissorExtending = new BooleanTopic("Climb Sub Scissor Extending");

    public ClimbSubsystem(Solenoid solenoid, CANSparkMax winchMotor) {
        m_scissorExtendSolenoid = solenoid;
        m_winchMotor = winchMotor;
        setConfiguration(new Configuration());

        m_climbDirection = ClimbDirection.Stop;
    }

    public void setConfiguration(Configuration configuration) {
        m_config = configuration;

        m_winchMotor.setInverted(true);
        m_winchMotor.setIdleMode(configuration.m_winchIdleMode);
        m_winchMotor.setSmartCurrentLimit(configuration.m_winchStallCurrentLimit, configuration.m_winchFreeCurrentLimit);
    }

    // ===================
    // SCISSOR
    // ===================

    /**
     * Extends the scissor lift.
     */
    public void extendScissor() {
        System.out.println("EXTEND SCISSOR");
        //m_scissorExtendSolenoid.set(true);
        m_climbSubScissorExtending.log(true);
    }

    /**
     * Deactivates the scissor solenoids.
     */
    public void releaseScissor() {
        System.out.println("RELEASE SCISSOR");
        //m_scissorExtendSolenoid.set(false);
        m_climbSubScissorExtending.log(false);
    }

    /**
     * @return True if the scissor solenoid is active, false if not.
     */
    public boolean isScissorExtending() {
        return m_scissorExtendSolenoid.get();
    }

    // ===================
    // CLIMB[DIRECTION]
    // ===================

    public boolean isClimbing() {
        return m_climbDirection == ClimbDirection.Up;
    }

    public boolean isReleasing() {
        return m_climbDirection == ClimbDirection.Release;
    }

    public boolean isStopped() {
        return m_climbDirection == ClimbDirection.Stop;
    }

    public void climb() {
        m_climbSubInfo.log("Started Climbing");
        m_climbDirection = ClimbDirection.Up;
        System.out.println("START CLIMB");
        //m_winchMotor.set(m_config.m_climbSpeed);
    }

    public void release() {
        System.out.println("RELEASING CLIMBER");
        m_climbSubInfo.log("RELEASING CLIMBER");
        m_climbDirection = ClimbDirection.Release;
        System.out.println("RELEASE CLIMBER");
        //m_winchMotor.set(-m_config.m_releaseSpeed);
    }

    public void stop() {
        m_climbSubInfo.log("Stopped Climbing");
        m_climbDirection = ClimbDirection.Stop;
        System.out.println("STOP CLIMB");
        m_winchMotor.set(0);
    }
}

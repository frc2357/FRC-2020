package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Constants;

/**
 * This command runs the winches and stops when the winch motors 
 */
public class ClimbReelWinchCommand extends CommandLoggerBase {

    /**
     * Calculate first-order derivative of winch current. Records if the
     * current derivative reaches the threshold.
     */
    static private class Derivative {
        private double m_lastValue = Double.NaN;
        private double m_derivative = 0;
        private boolean m_spiked = false;

        public void updateValue(double value) {
            if(!Double.isNaN(m_lastValue)) {
                m_derivative = (value - m_lastValue) / Constants.UPDATE_PERIOD;
                if(m_derivative >= Constants.WINCH_CURRENT_SPIKE_THRESHOLD) {
                    m_spiked = true;
                }
            }
        }

        public double getDerivative() {
            return m_derivative;
        }

        public boolean getSpiked() {
            return m_spiked;
        }
    }

    private ClimbSubsystem m_climbSubsystem;
    private Derivative m_leftDerivative;
    private Derivative m_rightDerivative;

    public ClimbReelWinchCommand(ClimbSubsystem climbSubsystem) {
        m_climbSubsystem = climbSubsystem;
        addRequirements(m_climbSubsystem);

        m_leftDerivative = new Derivative();
        m_rightDerivative = new Derivative();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.addDoubleProperty("leftWinchCurrentDerivative", m_leftDerivative::getDerivative, null);
        builder.addDoubleProperty("rightWinchCurrentDerivative", m_rightDerivative::getDerivative, null);
    }
  
    @Override
    public void initialize() {
        super.initialize();
        m_climbSubsystem.setKeepLevel(false);
        m_climbSubsystem.climbUp();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        m_climbSubsystem.stopClimb();
        m_climbSubsystem.setKeepLevel(false);
    }

    @Override
    public boolean isFinished() {
        m_leftDerivative.updateValue(m_climbSubsystem.getLeftWinchCurrent());
        m_rightDerivative.updateValue(m_climbSubsystem.getRightWinchCurrent());
        return m_leftDerivative.getSpiked() && m_rightDerivative.getSpiked();
    }
}
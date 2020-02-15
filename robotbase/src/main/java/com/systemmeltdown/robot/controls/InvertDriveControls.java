package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.commands.InvertDriveCommand; //This import is used for the javadoc, sorry

import com.systemmeltdown.robotlib.controllers.DriverControls;
import com.systemmeltdown.robotlib.util.Utility;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

import com.systemmeltdown.robotlib.util.XboxRaw;

import com.systemmeltdown.robot.commands.VisionChangePipelineCommand;

/**
 * These extend {@link DriverControls} so these are the Driver's controls, adapted to support the
 * {@link InvertDriveCommand}.
 * 
 * @category Drive
 */
public class InvertDriveControls extends DriverControls {
    public final JoystickButton m_invertButton;
    public final JoystickButton m_changePipelineButton;
    private boolean m_isToggled = false;
    private XboxController m_controller;
    private int m_lastEncoderSpeed;

    public InvertDriveControls(InvertDriveControlsBuilder builder) {
        super(builder.m_controller, builder.m_deadband);
        m_invertButton = new JoystickButton(builder.m_controller, XboxRaw.A.value);
        m_changePipelineButton = new JoystickButton(builder.m_controller, XboxRaw.Back.value);
        m_lastEncoderSpeed = 0;
    }

    /**
     * Changes the value of m_isToggled from true to false or vice versa
     */
    public void invert() {
        m_isToggled = !m_isToggled;
    }
    
    @Override
    public double getSpeed() {
        double speed = super.getSpeed();
        speed = inputCurve(speed, 2);
        return m_isToggled ? speed : -speed;
    }

    @Override
    public double getTurn() {
        return -inputCurve(super.getTurn(), 3);
    }

    //I would put a javadoc here, but I really don't understand it. If you do, please delete this and put a javadoc here.
    public double inputCurve(double input, int curveFactor) {
        return Math.signum(input) * Math.abs(Math.pow(input, curveFactor));
    }

  public int getEncoderTurnDifferential() {
    int turn = 0;

    double input = Utility.deadband(m_controller.getX(Hand.kRight), Constants.DRIVE_STICK_DEADBAND);
    int encoderTurn = Constants.DRIVER_ENCODER_TURN_RATE;
    turn = (int)(input * encoderTurn);

    turn = Utility.clamp(turn, -Constants.DRIVER_ENCODER_TURN_RATE, Constants.DRIVER_ENCODER_TURN_RATE);

    return turn;
  }

  public int getEncoderSpeed() {
    int speed = 0;

    double input = Utility.deadband(m_controller.getY(Hand.kLeft), Constants.DRIVE_STICK_DEADBAND);
    int encoderSpeed =  Constants.DRIVER_ENCODER_SPEED;
    
    speed = (int)(-input * encoderSpeed);

    speed = Utility.clamp(speed, - Constants.DRIVER_ENCODER_SPEED, Constants.DRIVER_ENCODER_SPEED);

    // Limit the input speed on forward motion (to avoid tipping)
    double limitFactor = Constants.DRIVER_ENCODER_MAX_FORWARD_LIMIT_FACTOR;

    // Default is max from zero forward (reverse accel doesn't matter)
    int maxDiff = Constants.DRIVER_ENCODER_MAX_DIFF;

    if (speed - m_lastEncoderSpeed > maxDiff) {
      // Forward accel is too fast.
      int max = maxDiff;

      if (m_lastEncoderSpeed > 0) {
        // Limit forward acceleration.
        max = (int)(m_lastEncoderSpeed * limitFactor);
      } else if (m_lastEncoderSpeed < 0) {
        // Limit reverse deceleration.
        max = (int)(m_lastEncoderSpeed / limitFactor);
        max = max > -maxDiff ? 0 : max;
      }

      if (speed > max) {
        speed = max;
      }
    }

    m_lastEncoderSpeed = speed;
    return speed;
  }
    /**
     * The builder for the InvertDriveControls
     */
    public static class InvertDriveControlsBuilder {
        private XboxController m_controller = null;
        private double m_deadband = 0.0;
        private FalconTrajectoryDriveSubsystem m_driveSubsystem = null;
        private TogglableLimelightSubsystem m_visionSubsystem = null;

        /**
         * @param controller The driver's {@link XboxController}.
         * @param deadband The deadband for the driver's controller.
         */
        public InvertDriveControlsBuilder(XboxController controller, double deadband) {
            this.m_controller = controller;
            this.m_deadband = deadband;
        }

        public InvertDriveControlsBuilder withDriveSub(FalconTrajectoryDriveSubsystem driveSubsystem){
            this.m_driveSubsystem = driveSubsystem;
            return this;
        }

        public InvertDriveControlsBuilder withVisionSub(TogglableLimelightSubsystem visionSubsystem) {
            this.m_visionSubsystem = visionSubsystem;
            return this;
        }

        public InvertDriveControls build() {
            InvertDriveControls m_driverControls = new InvertDriveControls(this);
            if (m_driveSubsystem != null && m_visionSubsystem != null) {
                m_driverControls.m_invertButton.whenPressed(new InvertDriveCommand(m_visionSubsystem, m_driverControls));
                m_driverControls.m_changePipelineButton.whileHeld(new VisionChangePipelineCommand(m_visionSubsystem));
            }
            return m_driverControls;
        }
    }
}
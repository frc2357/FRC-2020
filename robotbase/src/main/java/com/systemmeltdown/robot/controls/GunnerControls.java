package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robot.commands.ClimbCommandSequence;
import com.systemmeltdown.robot.commands.ClimbLeftCommand;
import com.systemmeltdown.robot.commands.ClimbRaiseScissorCommand;
import com.systemmeltdown.robot.commands.ClimbRightCommand;
import com.systemmeltdown.robot.commands.IntakePickupBallCommand;
import com.systemmeltdown.robot.commands.IntakeToggleDirectionCommand;
import com.systemmeltdown.robot.commands.PivotIntakeCommand;
import com.systemmeltdown.robot.commands.ShootCommand;
import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robotlib.triggers.AxisThresholdTrigger;
import com.systemmeltdown.robotlib.util.XboxRaw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * These are the controls for the gunner.
 * 
 * @category Drive
 */
public class GunnerControls {
    XboxController m_controller;

    public AxisThresholdTrigger m_rightTrigger;
    public AxisThresholdTrigger m_leftTrigger;
    public JoystickButton m_DpadUp;
    public JoystickButton m_yButton;
    public JoystickButton m_xButton;
    public Trigger m_aButton;
    public Trigger m_leftBumper;
    public Trigger m_rightBumper;
    public Trigger m_bumperChord;

    /**
     * @param builder The GunnerControlsBuilder object
     */
    public GunnerControls(GunnerControlsBuilder builder) {
        m_controller = builder.m_controller;
        m_rightTrigger = new AxisThresholdTrigger(builder.m_controller, Hand.kRight, .1);
        m_leftTrigger = new AxisThresholdTrigger(builder.m_controller, Hand.kLeft, .1);
        m_yButton = new JoystickButton(builder.m_controller, XboxRaw.Y.value);
        m_xButton = new JoystickButton(builder.m_controller, XboxRaw.X.value);
        m_aButton = new JoystickButton(builder.m_controller, XboxRaw.A.value)
                                        .and(new POVButton(builder.m_controller, 0));
        m_leftBumper = new JoystickButton(builder.m_controller, XboxRaw.BumperLeft.value)
                                            .and(new POVButton(builder.m_controller, 0));
        m_rightBumper = new JoystickButton(builder.m_controller, XboxRaw.BumperRight.value)
                                            .and(new POVButton(builder.m_controller, 0));

        m_bumperChord = new JoystickButton(builder.m_controller, XboxRaw.BumperRight.value)
                                            .and(new JoystickButton(builder.m_controller, XboxRaw.BumperLeft.value))
                                            .and(new POVButton(builder.m_controller, 0));
    }

    /**
     * Gets the current trigger value from the hand on the left or right.
     * 
     * @param hand Which hand you want to get a value from.
     * 
     * @return The trigger value from the left or right hand.
     */
    public double getTriggerValue(Hand hand) {
        return m_controller.getTriggerAxis(hand);
    }

    /**
     * Class for building GunnerControls
     */
    public static class GunnerControlsBuilder {
        private XboxController m_controller = null;
        private IntakeSubsystem m_intakeSub = null;
        private ShooterSubsystem m_shooterSub = null;
        private ClimbSubsystem m_climbSub = null;

        /**
         * @param controller the controller of the gunner controls
         */
        public GunnerControlsBuilder(XboxController controller) {
            this.m_controller = controller;
        }

        public GunnerControlsBuilder withIntakeSub(IntakeSubsystem intakeSub) {
            this.m_intakeSub = intakeSub;
            return this;
        }

        public GunnerControlsBuilder withShooterSubsystem(ShooterSubsystem shooterSub) {
            this.m_shooterSub = shooterSub;
            return this;
        }

        public GunnerControlsBuilder withClimbSubsystem(ClimbSubsystem climbSub) {
            this.m_climbSub = climbSub;
            return this;
        }

        public GunnerControls build() {
            GunnerControls m_gunnerControls = new GunnerControls(this);
            if (m_intakeSub != null) {
                m_gunnerControls.m_leftTrigger.whileActiveContinuous(new IntakePickupBallCommand(m_intakeSub, m_gunnerControls));
                m_gunnerControls.m_yButton.whenPressed(new IntakeToggleDirectionCommand(m_intakeSub));
                m_gunnerControls.m_xButton.whenPressed(new PivotIntakeCommand(m_intakeSub));
            }
            if (m_shooterSub != null){
                m_gunnerControls.m_rightTrigger.whileActiveContinuous(new ShootCommand(m_shooterSub, m_gunnerControls));
            }
            if(m_climbSub != null) {
                m_gunnerControls.m_bumperChord.whenActive(new ClimbCommandSequence(m_climbSub));
                m_gunnerControls.m_leftBumper.whenActive(new ClimbLeftCommand(m_climbSub));
                m_gunnerControls.m_rightBumper.whenActive(new ClimbRightCommand(m_climbSub));
                m_gunnerControls.m_aButton.whenActive(new ClimbRaiseScissorCommand(m_climbSub));
            }
            return m_gunnerControls;
        }
    }
}
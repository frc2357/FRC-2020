package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robot.commands.ClimbCommandSequence;
import com.systemmeltdown.robot.commands.ClimbLeftCommand;
import com.systemmeltdown.robot.commands.ClimbRaiseScissorCommand;
import com.systemmeltdown.robot.commands.ClimbRightCommand;
import com.systemmeltdown.robot.commands.IntakePickupCellCommand;
import com.systemmeltdown.robot.commands.IntakeToggleDirectionCommand;
import com.systemmeltdown.robot.commands.PivotIntakeCommand;
import com.systemmeltdown.robot.commands.RotateStorageContinuous;
import com.systemmeltdown.robot.commands.RotateStorageSingleCell;
import com.systemmeltdown.robot.commands.ShootVariableCommand;
import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
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
    public JoystickButton m_bButton;
    public Trigger m_aButtonandDPadUp;
    public Trigger m_leftBumperandDPadUp;
    public Trigger m_rightBumperandDPadUp;
    public Trigger m_bumperAndDPadUpChord;


    /**
     * @param builder The GunnerControlsBuilder object
     */
    public GunnerControls(GunnerControlsBuilder builder) {
        m_controller = builder.m_controller;
        m_rightTrigger = new AxisThresholdTrigger(builder.m_controller, Hand.kRight, .1);
        m_leftTrigger = new AxisThresholdTrigger(builder.m_controller, Hand.kLeft, .1);
        m_yButton = new JoystickButton(builder.m_controller, XboxRaw.Y.value);
        m_xButton = new JoystickButton(builder.m_controller, XboxRaw.X.value);
        m_bButton = new JoystickButton(builder.m_controller, XboxRaw.B.value);
        m_aButtonandDPadUp = new JoystickButton(builder.m_controller, XboxRaw.A.value)
                .and(new POVButton(builder.m_controller, 0));
        m_leftBumperandDPadUp = new JoystickButton(builder.m_controller, XboxRaw.BumperLeft.value)
                .and(new POVButton(builder.m_controller, 0));
        m_rightBumperandDPadUp = new JoystickButton(builder.m_controller, XboxRaw.BumperRight.value)
                .and(new POVButton(builder.m_controller, 0));

        m_bumperAndDPadUpChord = new JoystickButton(builder.m_controller, XboxRaw.BumperRight.value)
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
        private ClimbSubsystem m_climbSub = null;
        private IntakeSubsystem m_intakeSub = null;
        private ShooterSubsystem m_shooterSub = null;
        private StorageSubsystem m_storageSubsystem = null;

        /**
         * @param controller the controller of the gunner controls
         */
        public GunnerControlsBuilder(XboxController controller) {
            this.m_controller = controller;
        }

        public GunnerControlsBuilder withClimbSubsystem(ClimbSubsystem climbSub) {
            this.m_climbSub = climbSub;
            return this;
        }

        public GunnerControlsBuilder withIntakeSub(IntakeSubsystem intakeSub) {
            this.m_intakeSub = intakeSub;
            return this;
        }

        public GunnerControlsBuilder withShooterSubsystem(ShooterSubsystem shooterSub) {
            this.m_shooterSub = shooterSub;
            return this;
        }

        public GunnerControlsBuilder withStorageSubsystem(StorageSubsystem storageSubsystem) {
            this.m_storageSubsystem = storageSubsystem;
            return this;
        }

        public GunnerControls build() {
            GunnerControls m_gunnerControls = new GunnerControls(this);
            if (m_intakeSub != null) {
                m_gunnerControls.m_leftTrigger
                        .whileActiveContinuous(new IntakePickupCellCommand(m_intakeSub, m_gunnerControls));
                m_gunnerControls.m_yButton.whenPressed(new IntakeToggleDirectionCommand(m_intakeSub));
                m_gunnerControls.m_xButton.whenPressed(new PivotIntakeCommand(m_intakeSub));
            }
            if (m_shooterSub != null) {
                m_gunnerControls.m_rightTrigger.whileActiveContinuous(new ShootVariableCommand(m_shooterSub, m_gunnerControls));
            }
            if (m_climbSub != null) {
                m_gunnerControls.m_bumperAndDPadUpChord.whenActive(new ClimbCommandSequence(m_climbSub));
                m_gunnerControls.m_leftBumperandDPadUp.whenActive(new ClimbLeftCommand(m_climbSub));
                m_gunnerControls.m_rightBumperandDPadUp.whenActive(new ClimbRightCommand(m_climbSub));
                m_gunnerControls.m_aButtonandDPadUp.whenActive(new ClimbRaiseScissorCommand(m_climbSub));
            }
            if (m_storageSubsystem != null) {
                // m_gunnerControls.m_bButton.whileHeld(new RotateStorageContinuous(m_storageSubsystem));
                m_gunnerControls.m_bButton.whenPressed(new RotateStorageSingleCell(m_storageSubsystem));
            }
            return m_gunnerControls;
        }
    }
}
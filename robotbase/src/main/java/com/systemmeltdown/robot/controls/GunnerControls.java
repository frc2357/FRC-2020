package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robot.commands.ShootCommandGroup;
import com.systemmeltdown.robot.commands.ShooterVisionCommandGroup;
import com.systemmeltdown.robot.commands.ClimbRaiseScissorCommand;
import com.systemmeltdown.robot.commands.ClimbReleaseCommand;
import com.systemmeltdown.robot.commands.ClimbUpCommand;
import com.systemmeltdown.robot.commands.FeedToCarouselCommand;
import com.systemmeltdown.robot.commands.FeedToShooterCommand;
import com.systemmeltdown.robot.commands.IntakeRollerCommand;
import com.systemmeltdown.robot.commands.IntakeTogglePivotCommand;
import com.systemmeltdown.robot.commands.IntakePickupCellsParallelCommand;
import com.systemmeltdown.robot.commands.RotateStorageContinuous;
import com.systemmeltdown.robot.commands.TurretRotateCommand;
import com.systemmeltdown.robot.commands.VisionChangePipelineCommand;
import com.systemmeltdown.robot.commands.TrackTargetCommand;
import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;
import com.systemmeltdown.robotlib.triggers.AxisThresholdTrigger;
import com.systemmeltdown.robotlib.util.XboxRaw;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;

/**
 * These are the controls for the gunner.
 * 
 * @category Drive
 */
public class GunnerControls {
    private class ShootHighTrigger extends Trigger {
        @Override
        public boolean get() {
            return
                m_rightTrigger.get() &&
                !m_downDPad.get() &&
                !m_leftDPad.get() &&
                m_rightDPad.get();
        }
    }

    private class ShootLowTrigger extends Trigger {
        @Override
        public boolean get() {
            return
                m_rightTrigger.get() &&
                m_downDPad.get() &&
                !m_leftDPad.get() &&
                !m_rightDPad.get();
        }
    }

    private class ShootInitiationLineTrigger extends Trigger {
        @Override
        public boolean get() {
            return
                m_rightTrigger.get() &&
                !m_downDPad.get() &&
                m_leftDPad.get() &&
                !m_rightDPad.get();
        }
    }

    private class ShootVisionTrigger extends Trigger {
        @Override
        public boolean get() {
            return
                m_rightTrigger.get() &&
                !m_downDPad.get() &&
                !m_leftDPad.get() &&
                !m_rightDPad.get();
        }
    }

    private class FeederUpTrigger extends Trigger {
        @Override
        public boolean get() {
            return
                m_aButton.get()
                && m_rightDPad.get()
                && !m_leftDPad.get()
                && !m_upDPad.get()
                && !m_downDPad.get();
        }
    }

    private class FeederDownTrigger extends Trigger {
        @Override
        public boolean get() {
            return
                m_aButton.get()
                && !m_rightDPad.get()
                && m_leftDPad.get()
                && !m_upDPad.get()
                && !m_downDPad.get();
        }
    }

    XboxController m_controller;

    public AxisThresholdTrigger m_rightTrigger;
    public AxisThresholdTrigger m_leftTrigger;
    public JoystickButton m_backButton;
    public JoystickButton m_startButton;
    public JoystickButton m_leftBumper;
    public JoystickButton m_rightBumper;
    public Trigger m_aButton;
    public Trigger m_bButton;
    public Trigger m_xButton;
    public Trigger m_yButton;
    public Trigger m_yButtonAndLeftDPad;
    public Trigger m_xButtonAndLeftDPad;
    public Trigger m_bButtonAndLeftDPad;
    public Trigger m_bButtonAndRightDPad;
    public Trigger m_aButtonAndDPadRight;
    public Trigger m_aButtonAndDPadUp;
    public Trigger m_bButtonAndDPadUp;
    public Trigger m_startButtonAndDPadUp;

    public POVButton m_upDPad;
    public POVButton m_rightDPad;
    public POVButton m_downDPad;
    public POVButton m_leftDPad;

    public ShootLowTrigger m_shootLowTrigger;
    public ShootHighTrigger m_shootHighTrigger;
    public ShootInitiationLineTrigger m_shootInitiationLineTrigger;
    public ShootVisionTrigger m_shootVisionTrigger;
    public FeederUpTrigger m_feederUpTrigger;
    public FeederDownTrigger m_feederDownTrigger;

    /**
     * @param builder The GunnerControlsBuilder object
     */
    public GunnerControls(GunnerControlsBuilder builder) {
        m_controller = builder.m_controller;
        m_upDPad = new POVButton(builder.m_controller, 0);
        m_rightDPad = new POVButton(builder.m_controller, 90);
        m_downDPad = new POVButton(builder.m_controller, 180);
        m_leftDPad = new POVButton(builder.m_controller, 270);

        m_rightTrigger = new AxisThresholdTrigger(builder.m_controller, Hand.kRight, .1);
        m_leftTrigger = new AxisThresholdTrigger(builder.m_controller, Hand.kLeft, .1);
        m_backButton = new JoystickButton(builder.m_controller, XboxRaw.Back.value);
        m_startButton = new JoystickButton(builder.m_controller, XboxRaw.Start.value);
        m_leftBumper = new JoystickButton(builder.m_controller, XboxRaw.BumperLeft.value);
        m_rightBumper = new JoystickButton(builder.m_controller, XboxRaw.BumperRight.value);
        m_aButton = new JoystickButton(builder.m_controller, XboxRaw.A.value);
        m_bButton = new JoystickButton(builder.m_controller, XboxRaw.B.value);
        m_xButton = new JoystickButton(builder.m_controller, XboxRaw.X.value);
        m_yButton = new JoystickButton(builder.m_controller, XboxRaw.Y.value);
        m_yButtonAndLeftDPad = m_yButton.and(m_leftDPad);
        m_xButtonAndLeftDPad = m_xButton.and(m_leftDPad);
        m_bButtonAndLeftDPad = m_bButton.and(m_leftDPad);
        m_bButtonAndRightDPad = m_bButton.and(m_rightDPad);
        m_aButtonAndDPadRight = m_aButton.and(m_rightDPad);
        m_aButtonAndDPadUp = m_aButton.and(m_upDPad);
        m_bButtonAndDPadUp = m_bButton.and(m_upDPad);
        m_startButtonAndDPadUp = m_startButton.and(m_upDPad);

        m_shootLowTrigger = new ShootLowTrigger();
        m_shootHighTrigger = new ShootHighTrigger();
        m_shootInitiationLineTrigger = new ShootInitiationLineTrigger();
        m_shootVisionTrigger = new ShootVisionTrigger();
        m_feederUpTrigger = new FeederUpTrigger();
        m_feederDownTrigger = new FeederDownTrigger();
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
        private FeederSubsystem m_feederSub = null;
        private IntakeSubsystem m_intakeSub = null;
        private ShooterSubsystem m_shooterSub = null;
        private StorageSubsystem m_storageSub = null;
        private TurretSubsystem m_turretSub = null;
        private TogglableLimelightSubsystem m_visionSubsystem = null;

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

        public GunnerControlsBuilder withFeederSubsystem(FeederSubsystem feederSub) {
            this.m_feederSub = feederSub;
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
            this.m_storageSub = storageSubsystem;
            return this;
        }

        public GunnerControlsBuilder withTurretSub(TurretSubsystem turretSub) {
            this.m_turretSub = turretSub;
            return this;
        }

        public GunnerControlsBuilder withVisionSub(TogglableLimelightSubsystem visionSubsystem) {
            this.m_visionSubsystem = visionSubsystem;
            return this;
        }

        public GunnerControls build() {
            GunnerControls m_gunnerControls = new GunnerControls(this);

            // Left Trigger: Intake combined sequence
            if (m_intakeSub != null && m_storageSub != null) {
                m_gunnerControls.m_leftTrigger.whileActiveOnce(
                    new IntakePickupCellsParallelCommand(
                        m_intakeSub,
                        m_storageSub,
                        m_gunnerControls
                    )
                );
            }

            // Right Trigger: Shooter combined sequence
            if (m_storageSub != null && m_feederSub != null && m_shooterSub != null) {
                m_gunnerControls.m_shootLowTrigger.whileActiveOnce(
                    new ShootCommandGroup(
                        m_storageSub,
                        m_feederSub,
                        m_shooterSub,
                        m_visionSubsystem,
                        Constants.SHOOTER_LOW_GOAL_SPEED_RPM
                    )
                );
                m_gunnerControls.m_shootHighTrigger.whileActiveOnce(
                    new ShootCommandGroup(
                        m_storageSub,
                        m_feederSub,
                        m_shooterSub,
                        m_visionSubsystem,
                        Constants.SHOOTER_END_OF_TRENCH_RPM
                    )
                );
                m_gunnerControls.m_shootInitiationLineTrigger.whileActiveOnce(
                    new ShootCommandGroup(
                        m_storageSub,
                        m_feederSub,
                        m_shooterSub,
                        m_visionSubsystem,
                        Constants.AUTO_SHOOTER_SPEED_RPM
                    )
                );
                m_gunnerControls.m_shootVisionTrigger.whileActiveOnce(
                    new ShooterVisionCommandGroup(
                        m_turretSub,
                        m_storageSub,
                        m_feederSub,
                        m_shooterSub,
                        m_visionSubsystem
                    )
                );
            }

            // Manual turret movement
            if (m_turretSub != null) {
                m_gunnerControls.m_leftBumper.whileActiveOnce(new TurretRotateCommand(m_turretSub, true));
                m_gunnerControls.m_rightBumper.whileActiveOnce(new TurretRotateCommand(m_turretSub, false));
            }

            // Auto turret tracking
            if (m_turretSub != null && m_visionSubsystem != null) {
                m_gunnerControls.m_backButton.whileActiveOnce(
                    new ParallelCommandGroup(
                        new VisionChangePipelineCommand(m_visionSubsystem),
                        new TrackTargetCommand(m_turretSub, m_visionSubsystem)
                    )
                );
            }

            // Intake Mode Bindings
            if (m_intakeSub != null) {
                m_gunnerControls.m_yButtonAndLeftDPad.whileActiveOnce(
                    new IntakeRollerCommand(m_intakeSub, Constants.INTAKE_REVERSE_SPEED)
                );
                m_gunnerControls.m_xButtonAndLeftDPad.whileActiveOnce(new IntakeTogglePivotCommand(m_intakeSub));
            }
            if (m_storageSub != null) {
                m_gunnerControls.m_bButtonAndLeftDPad.whileActiveOnce(
                    new RotateStorageContinuous(m_storageSub, Constants.STORAGE_CAROUSEL_INTAKE_ROTATION_SPEED, true)
                );
            }

            // Shooter Mode Bindings
            if (m_feederSub != null) {
                m_gunnerControls.m_feederUpTrigger.whileActiveOnce(new FeedToShooterCommand(m_feederSub));
                m_gunnerControls.m_feederDownTrigger.whileActiveOnce(new FeedToCarouselCommand(m_feederSub));
            }
            if (m_storageSub != null) {
                m_gunnerControls.m_bButtonAndRightDPad.whileActiveOnce(
                    new RotateStorageContinuous(m_storageSub, Constants.STORAGE_CAROUSEL_SHOOTER_ROTATION_SPEED, false)
                );
            }

            // Climber Mode Bindings
            if (m_climbSub != null) {
                m_gunnerControls.m_aButtonAndDPadUp.whileActiveOnce(new ClimbRaiseScissorCommand(m_climbSub));
                m_gunnerControls.m_bButtonAndDPadUp.whileActiveOnce(new ClimbUpCommand(m_climbSub));
                m_gunnerControls.m_startButtonAndDPadUp.whileActiveOnce(new ClimbReleaseCommand(m_climbSub));
            }

            return m_gunnerControls;
        }
    }
}
package com.systemmeltdown.robot.controls;

import com.systemmeltdown.robot.commands.IntakePickupBallCommand;
import com.systemmeltdown.robot.commands.IntakeToggleDirectionCommand;
import com.systemmeltdown.robot.commands.ShootCommand;
import com.systemmeltdown.robot.subsystems.IntakeSub;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robotlib.triggers.AxisThresholdTrigger;
import com.systemmeltdown.robotlib.util.XboxRaw;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class GunnerControls {
    XboxController m_controller;

    public AxisThresholdTrigger m_rightTrigger;
    public AxisThresholdTrigger m_leftTrigger;
    public JoystickButton m_yButton;

    public GunnerControls(GunnerControlsBuilder builder) {
        m_controller = builder.m_controller;
        m_rightTrigger = new AxisThresholdTrigger(builder.m_controller, Hand.kRight, .1);
        m_leftTrigger = new AxisThresholdTrigger(builder.m_controller, Hand.kLeft, .1);
        m_yButton = new JoystickButton(builder.m_controller, XboxRaw.Y.value);
    }

    public double getTriggerValue(Hand hand) {
        return m_controller.getTriggerAxis(hand);
    }

    public static class GunnerControlsBuilder {
        private XboxController m_controller = null;
        private IntakeSub m_intakeSub = null;
        private ShooterSubsystem m_shooterSub = null;

        public GunnerControlsBuilder(XboxController controller) {
            this.m_controller = controller;
        }

        public GunnerControlsBuilder withIntakeSub(IntakeSub intakeSub) {
            this.m_intakeSub = intakeSub;
            return this;
        }

        public GunnerControlsBuilder withShooterSubsystem(ShooterSubsystem shooterSub) {
            this.m_shooterSub = shooterSub;
            return this;
        }

        public GunnerControls build() {
            GunnerControls m_gunnerControls = new GunnerControls(this);
            if (m_intakeSub != null) {
                m_gunnerControls.m_leftTrigger.whileActiveContinuous(new IntakePickupBallCommand(m_intakeSub, m_gunnerControls));
                m_gunnerControls.m_yButton.whenPressed(new IntakeToggleDirectionCommand(m_intakeSub));
            }
            if (m_shooterSub != null){
                m_gunnerControls.m_rightTrigger.whileActiveContinuous(new ShootCommand(m_shooterSub, m_gunnerControls));
            }
            return m_gunnerControls;
        }
    }
}
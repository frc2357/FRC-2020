package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class IntakePivotCommandGroup extends SequentialCommandGroup {
  public IntakePivotCommandGroup(IntakeSubsystem intakeSubsystem, DoubleSolenoid.Value value) {
    addCommands(
      new IntakeSetPivotCommand(intakeSubsystem, value),
      new WaitCommand(Constants.INTAKE_PIVOT_ACTUATE_SECONDS),
      new IntakeSetPivotCommand(intakeSubsystem, DoubleSolenoid.Value.kOff)
    );
  }
}

package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robotlib.subsystems.drive.SkidSteerDriveSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class AutonomousMoveOffLineCommand extends ParallelRaceGroup {
  public AutonomousMoveOffLineCommand(SkidSteerDriveSubsystem driveSubsystem) {
    double speed = Constants.AUTO_MOVE_OFF_LINE_SPEED;
    double turn = Constants.AUTO_MOVE_OFF_LINE_TURN;
    double driveSeconds = Constants.AUTO_MOVE_OFF_SECONDS;

    addCommands(
      new AutoDriveProportionalCommand(driveSubsystem, speed, turn),
      new WaitCommand(driveSeconds)
    );
  }
}

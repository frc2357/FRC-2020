package com.systemmeltdown.robot.commands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

import java.util.List;

import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import frc.robot.Constants;



/**
 * This command has been moved out of the RobotContainer. Will be removed once we get our 
 * robot fully characterized.
 * 
 * This command is from WpiLib's Trajectory class, found here: https://docs.wpilib.org/en/latest/docs/software/examples-tutorials/trajectory-tutorial/trajectory-tutorial-overview.html
 * 
 * @category Automode
 */
public class AutoTemporaryCommand extends CommandBase {
    private FalconTrajectoryDriveSubsystem driveSub;

    /**
     * @param driveSub The drive sub.
     */
    public AutoTemporaryCommand(FalconTrajectoryDriveSubsystem driveSub) {
        this.driveSub = driveSub;
    }

    public Command getRamsete() {
        // Create a voltage constraint to ensure we don't accelerate too fast
        var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(Constants.S_VOLTS,
                Constants.V_VOLT_SECONDS_PER_METER, Constants.A_VOLT_SECONDS_SQUARED_PER_METER),
                Constants.DRIVE_KINEMATICS, 10);

        // Create config for trajectory
        TrajectoryConfig config = new TrajectoryConfig(Constants.MAX_SPEED_METERS_PER_SECOND,
                Constants.MAX_ACCELERATION_METERS_PER_SECOND_SQUARED)
                        // Add kinematics to ensure max speed is actually obeyed
                        .setKinematics(Constants.DRIVE_KINEMATICS)
                        // Apply the voltage constraint
                        .addConstraint(autoVoltageConstraint);

        // An example trajectory to follow. All units in meters.
        Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(3, 0, new Rotation2d(0)),
                // Pass config
                config);

        RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, driveSub::getPose,
                new RamseteController(Constants.RAMSETE_B, Constants.RAMSETE_ZETA),
                new SimpleMotorFeedforward(Constants.S_VOLTS, Constants.V_VOLT_SECONDS_PER_METER,
                        Constants.A_VOLT_SECONDS_SQUARED_PER_METER),
                Constants.DRIVE_KINEMATICS, driveSub::getWheelSpeeds, new PIDController(Constants.P_DRIVE_VEL, 0, 0),
                new PIDController(Constants.P_DRIVE_VEL, 0, 0),
                // RamseteCommand passes volts to the callback
                driveSub::setTankDriveVolts, driveSub);

        // Run path following command, then stop at the end.
        return ramseteCommand.andThen(() -> driveSub.setTankDriveVolts(0, 0));
    }
}
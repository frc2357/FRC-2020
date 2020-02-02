/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

//import com.systemmeltdown.robot.commands.ShootCommand;
//import com.systemmeltdown.robot.subsystems.IntakeSub;
//import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
//import com.systemmeltdown.robot.subsystems.StorageSubsystem;
//import com.systemmeltdown.robot.commands.IntakePickupBallCommand;
import com.systemmeltdown.robot.commands.InvertDriveCommand;
import com.systemmeltdown.robot.commands.VisionChangePipelineCommand;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robot.subsystems.SubsystemFactory;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem.PipelineIndex;
import com.systemmeltdown.robotlib.commands.DriveProportionalCommand;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;
import com.systemmeltdown.robot.shuffleboard.CellNumberWidget;
import com.systemmeltdown.robot.shuffleboard.AutoWaitTimeAndChooser;
import com.systemmeltdown.robot.shuffleboard.FailsafeButtonWidget;
import com.systemmeltdown.robot.shuffleboard.LoggerTab;
import java.util.List;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private FalconTrajectoryDriveSubsystem m_driveSub;
  // private final ShooterSubsystem m_shootSub;
  // private final IntakeSub m_intakeSub;
  // private final StorageSubsystem m_storageSub;
  private final TogglableLimelightSubsystem m_visionSub;

  private final InvertDriveControls m_driverControls = new InvertDriveControls(new XboxController(0), .1);
  private final GunnerControls m_gunnerControls = new GunnerControls(new XboxController(1));

  private final AutoWaitTimeAndChooser[] m_waitTimeAndChooser = new AutoWaitTimeAndChooser[3];

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    SubsystemFactory subsystemFactory = new SubsystemFactory();
    m_driveSub = subsystemFactory.CreateFalconTrajectoryDriveSubsystem();
    // m_shootSub = subsystemFactory.CreateShooterSubsystem();
    // m_intakeSub = subsystemFactory.CreateIntakeSub();
    // m_storageSub = subsystemFactory.CreateStorageSubsystem();
    m_visionSub = subsystemFactory.CreateLimelightSubsystem();

    // Configure the button bindings
    configureDriveSub();
    configureButtonBindings();
    configureShuffleboard();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // m_gunnerControls.m_shootButton.whenPressed(command)
    m_driverControls.m_invertButton.whenPressed(new InvertDriveCommand(m_visionSub, m_driverControls));
    m_driverControls.m_changePipelineButton.whileHeld(new VisionChangePipelineCommand(m_visionSub));
    // m_gunnerControls.m_rightTrigger.whileActiveContinuous(new ShootCommand(m_shootSub, m_gunnerControls));
    // m_gunnerControls.m_leftTrigger.whileActiveContinuous(new IntakePickupBallCommand(m_intakeSub, m_gunnerControls));
  }

  private void configureShuffleboard() {
    //CellNumberWidget cellNumberWidget = new CellNumberWidget("Robot", m_storageSub);
    
    // for(int i = 0; i < 4; i++) {
    //  m_waitTimeAndChooser[i] = new AutoWaitTimeAndChooser("AUTO", i);
    // }

    LoggerTab loggerTab = new LoggerTab();
    ClosedLoopSubsystem[] subsystems = {/*m_shootSub, m_intakeSub, m_storageSub*/ (ClosedLoopSubsystem) m_driveSub};
    FailsafeButtonWidget failsafeButton = new FailsafeButtonWidget("Robot", subsystems);
  }

  private void configureDriveSub() {
    m_driveSub.setDefaultCommand(new DriveProportionalCommand(m_driveSub, m_driverControls));
  }

  // Taken from docs.wpilib.org example code
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /*public Command getAutonomousCommand() {

    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(Constants.S_VOLTS,
        Constants.V_VOLT_SECONDS_PER_METER, Constants.A_VOLT_SECONDS_SQUARED_PER_METER), Constants.DRIVE_KINEMATICS,
        10);

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

    /*RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, m_driveSub::getPose,
        new RamseteController(Constants.RAMSETE_B, Constants.RAMSETE_ZETA),
        new SimpleMotorFeedforward(Constants.S_VOLTS, Constants.V_VOLT_SECONDS_PER_METER,
            Constants.A_VOLT_SECONDS_SQUARED_PER_METER),
        Constants.DRIVE_KINEMATICS, m_driveSub::getWheelSpeeds, new PIDController(Constants.P_DRIVE_VEL, 0, 0),
        new PIDController(Constants.P_DRIVE_VEL, 0, 0),
        // RamseteCommand passes volts to the callback
        m_driveSub::setTankDriveVolts, m_driveSub);

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> m_driveSub.setTankDriveVolts(0, 0)); 
  } */
}
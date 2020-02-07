/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.systemmeltdown.robot.commands.ShootCommand;
import com.systemmeltdown.robot.subsystems.IntakeSub;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;
import com.systemmeltdown.robot.commands.AutoTemporaryCommand;
import com.systemmeltdown.robot.commands.CommandBuilder;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.SubsystemFactory;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robotlib.commands.DriveProportionalCommand;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;
import com.systemmeltdown.robot.shuffleboard.ShuffleboardBuilder;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

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
  private final IntakeSub m_intakeSub;
  // private final StorageSubsystem m_storageSub;
  private final TogglableLimelightSubsystem m_visionSub;

  private final InvertDriveControls m_driverControls = new InvertDriveControls(new XboxController(0), .1);
  private final GunnerControls m_gunnerControls = new GunnerControls(new XboxController(1));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    SubsystemFactory subsystemFactory = new SubsystemFactory();
    m_driveSub = subsystemFactory.CreateFalconTrajectoryDriveSubsystem();
    // m_shootSub = subsystemFactory.CreateShooterSubsystem();
    m_intakeSub = subsystemFactory.CreateIntakeSub();
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
    new CommandBuilder(m_driverControls, m_gunnerControls)
      // .withClimbSub(m_climbSub)
      // .withControlPanelSub(m_controlPanelSub)
      .withDriveSub(m_driveSub)
      // .withFeederSub(m_feederSub)
      .withIntakeSub(m_intakeSub)
      // .withShooterSub(m_shooterSubsystem)
      // .withStorageSub(m_storageSub)
      // .withTurretSub(m_turretSub)
      .withVisionSub(m_visionSub)
      .build();
  }

  private void configureShuffleboard() {
    new ShuffleboardBuilder()
    // .withClimbSub(m_climbSub)
    // .withControlPanelSub(m_controlPanelSub)
    .withDriveSub(m_driveSub)
    // .withFeederSub(m_feederSub)
    .withIntakeSub(m_intakeSub)
    // .withShooterSub(m_shooterSubsystem)
    // .withStorageSub(m_storageSub)
    // .withTurretSub(m_turretSub)
    .withVisionSub(m_visionSub)
    .build();
  }

  private void configureDriveSub() {
    m_driveSub.setDefaultCommand(new DriveProportionalCommand(m_driveSub, m_driverControls));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutoTemporaryCommand(m_driveSub).getRamsete();
  }
}

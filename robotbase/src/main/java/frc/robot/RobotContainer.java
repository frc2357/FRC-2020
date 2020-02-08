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
import com.systemmeltdown.robot.commands.IntakePickupBallCommand;
import com.systemmeltdown.robot.commands.IntakeToggleDirectionCommand;
import com.systemmeltdown.robot.commands.InvertDriveCommand;
import com.systemmeltdown.robot.commands.VisionChangePipelineCommand;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.SubsystemFactory;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robot.shuffleboard.LoggerTab;
import com.systemmeltdown.robot.shuffleboard.ShuffleboardFactory;

import edu.wpi.first.wpilibj.XboxController;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private FalconTrajectoryDriveSubsystem m_driveSub;
  // private final ShooterSubsystem m_shootSub;
  private final IntakeSub m_intakeSub;
  // private final StorageSubsystem m_storageSub;
  // private final TogglableLimelightSubsystem m_visionSub;

  // private final InvertDriveControls m_driverControls = new InvertDriveControls(new XboxController(0), .1);
  // private final GunnerControls m_gunnerControls = new GunnerControls(new XboxController(1));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    SubsystemFactory subsystemFactory = new SubsystemFactory();
    // m_driveSub = subsystemFactory.CreateFalconTrajectoryDriveSubsystem();
    // m_shooterSub = subsystemFactory.CreateShooterSubsystem();
    m_intakeSub = subsystemFactory.CreateIntakeSub();
    // m_storageSub = subsystemFactory.CreateStorageSubsystem();
    // m_visionSub = subsystemFactory.CreateLimelightSubsystem();

    // Configure the button bindings
    // configureDriveSub();
    // configureButtonBindings();
    configureShuffleboard();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // // m_gunnerControls.m_shootButton.whenPressed(command)
    // m_driverControls.m_invertButton.whenPressed(new InvertDriveCommand(m_visionSub, m_driverControls));
    // m_driverControls.m_changePipelineButton.whileHeld(new VisionChangePipelineCommand(m_visionSub));
    // // m_gunnerControls.m_rightTrigger.whileActiveContinuous(new ShootCommand(m_shootSub, m_gunnerControls));
    // m_gunnerControls.m_leftTrigger.whileActiveContinuous(new IntakePickupBallCommand(m_intakeSub, m_gunnerControls));
    // m_gunnerControls.m_yButton.whenPressed(new IntakeToggleDirectionCommand(m_intakeSub));
  }

  private void configureShuffleboard() {
    // LoggerTab loggerTab = new LoggerTab();
    Map<String, ClosedLoopSubsystem> subsystems = new HashMap<>();
    // subsystems.put("DriveSub", m_driveSub);
    // subsystems.put("ClimbSub", m_climbSub);
    // subsystems.put("ControlPanelSub", m_controlPanelSub);
    // subsystems.put("FeederSub", m_feederSub);
    subsystems.put("IntakeSub", m_intakeSub);
    // subsystems.put("ShooterSub", m_shooterSub);
    // subsystems.put("StorageSub", m_storageSub);
    // subsystems.put("TurretSub", m_turretSub);
    // subsystems.put("VisionSub", m_visionSub);
    new ShuffleboardFactory().build(subsystems);
  }

  private void configureDriveSub() {
    // m_driveSub.setDefaultCommand(new DriveProportionalCommand(m_driveSub, m_driverControls));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   return new AutoTemporaryCommand(m_driveSub).getRamsete();
  // }
}

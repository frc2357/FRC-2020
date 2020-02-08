/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.ControlPanelSub;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
//import com.systemmeltdown.robot.commands.ShootCommand;
import com.systemmeltdown.robot.subsystems.IntakeSub;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

import com.systemmeltdown.robot.commands.AutoTemporaryCommand;
import com.systemmeltdown.robot.controls.ControlsFactory;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.SubsystemFactory;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;
import com.systemmeltdown.robot.shuffleboard.AutoWaitTimeAndChooser;
import com.systemmeltdown.robot.shuffleboard.FailsafeButtonWidget;
import com.systemmeltdown.robot.shuffleboard.LoggerTab;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.ArrayList;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final FalconTrajectoryDriveSubsystem m_driveSub;
  private final ClimbSubsystem m_climbSub;
  private final ControlPanelSub m_controlPanelSub;
  private final FeederSubsystem m_feederSub;
  private final IntakeSub m_intakeSub;
  private final ShooterSubsystem m_shooterSub;
  private final StorageSubsystem m_storageSub;
  private final TogglableLimelightSubsystem m_visionSub;
  private final TurretSubsystem m_turretSub;

  private InvertDriveControls m_driverControls = new InvertDriveControls(new XboxController(0), .1);
  private GunnerControls m_gunnerControls = new GunnerControls(new XboxController(1));

  private final AutoWaitTimeAndChooser[] m_waitTimeAndChooser = new AutoWaitTimeAndChooser[3];

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    SubsystemFactory subsystemFactory = new SubsystemFactory();
    m_driveSub = subsystemFactory.CreateFalconTrajectoryDriveSubsystem();
    m_climbSub = subsystemFactory.CreateClimbSubsystem();
    m_controlPanelSub = subsystemFactory.CreateControlPanelSub();
    m_feederSub = subsystemFactory.CreateFeederSubsystem();
    m_intakeSub = subsystemFactory.CreateIntakeSub();
    m_shooterSub = subsystemFactory.CreateShooterSubsystem();
    m_storageSub = subsystemFactory.CreateStorageSubsystem();
    m_visionSub = subsystemFactory.CreateLimelightSubsystem();
    m_turretSub = subsystemFactory.CreateTurretSubsystem();

    // Configure the button bindings
    configureButtonBindings();
    // configureShuffleboard();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    ControlsFactory factory = new ControlsFactory(m_driverControls, m_gunnerControls,
        new ClosedLoopSubsystem[] { 
            m_driveSub,
            // m_climbSub,
            // m_controlPanelSub,
            // m_feederSub,
            m_intakeSub,
            // m_shooterSub,
            // m_storageSub,
            m_visionSub,
            // m_turretSub,
        });
    m_driverControls = factory.buildDriveControls();
    m_gunnerControls = factory.buildGunnerControls();
  }

  private void configureShuffleboard() {
    // CellNumberWidget cellNumberWidget = new CellNumberWidget("Robot",
    // m_storageSub);

    // for(int i = 0; i < 4; i++) {
    // m_waitTimeAndChooser[i] = new AutoWaitTimeAndChooser("AUTO", i);
    // }

    LoggerTab loggerTab = new LoggerTab();
    ArrayList<ClosedLoopSubsystem> subsystems = new ArrayList<>();
    // subsystems.add(m_shootSub);
    subsystems.add(m_intakeSub);
    // subsystems.add(m_storageSub);
    // subsystems.add(m_driveSub);
    FailsafeButtonWidget failsafeButton = new FailsafeButtonWidget("Robot", subsystems);
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

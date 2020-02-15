/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;
import com.systemmeltdown.robot.commands.AutoTemporaryCommand;
import com.systemmeltdown.robot.subsystems.ClimbSubsystem;
import com.systemmeltdown.robot.subsystems.ControlPanelSubsystem;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.controls.GunnerControls;
import com.systemmeltdown.robot.controls.InvertDriveControls;
import com.systemmeltdown.robot.subsystems.SubsystemFactory;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;
import com.systemmeltdown.robotlib.subsystems.ClosedLoopSubsystem;
// import com.systemmeltdown.robotlib.sensors.VL53LOXSensorOutput;
// import com.systemmeltdown.robotlib.subsystems.VL53LOXSensorOutputSubsystem;
import com.systemmeltdown.robot.shuffleboard.ShuffleboardFactory;

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
  private final FalconTrajectoryDriveSubsystem m_driveSub;
  private final ClimbSubsystem m_climbSub;
  private final ControlPanelSubsystem m_controlPanelSub;
  private final FeederSubsystem m_feederSub;
  private final IntakeSubsystem m_intakeSub;
  private final ShooterSubsystem m_shooterSub;
  private final StorageSubsystem m_storageSub;
  private final TurretSubsystem m_turretSub;
  private final TogglableLimelightSubsystem m_visionSub;
  

  private final InvertDriveControls m_driverControls;
  private final GunnerControls m_gunnerControls;
  // public final VL53LOXSensorOutput m_sensor = new
  // VL53LOXSensorOutput(Constants.BAUD_RATE, Port.kUSB)

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_driveSub = SubsystemFactory.CreateFalconTrajectoryDriveSubsystem();
    m_climbSub = null; // SubsystemFactory.CreateClimbSubsystem();
    m_controlPanelSub = null; // SubsystemFactory.CreateControlPanelSubsystem();
    m_feederSub = null; // SubsystemFactory.CreateFeederSubsystem();
    m_intakeSub = SubsystemFactory.CreateIntakeSubsystem();
    m_shooterSub = SubsystemFactory.CreateShooterSubsystem();
    m_storageSub = null; // SubsystemFactory.CreateStorageSubsystem();
    m_turretSub = null; // SubsystemFactory.CreateStorageSubsystem();
    m_visionSub = SubsystemFactory.CreateLimelightSubsystem();

    // Configure the button bindings
    m_driverControls = new InvertDriveControls.InvertDriveControlsBuilder(new XboxController(0), .1)
        .withDriveSub(m_driveSub)
        .withVisionSub(m_visionSub)
        .build();

    m_gunnerControls = new GunnerControls.GunnerControlsBuilder(new XboxController(1))
        .withIntakeSub(m_intakeSub)
        .withShooterSubsystem(m_shooterSub)
        .build();

    configureDriveSub();
    configureShuffleboard();
  }

  private void configureShuffleboard() {
    new ShuffleboardFactory().build(new ClosedLoopSubsystem[] {
        m_driveSub,
        m_climbSub,
        // m_controlPanelSub,
        // m_feederSub,
        m_intakeSub,
        // m_shooterSub,
        m_storageSub,
        // m_turretSub,
        // m_visionSub,
    });
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
  // return new AutoTemporaryCommand(m_driveSub).getRamsete();
  // }
}

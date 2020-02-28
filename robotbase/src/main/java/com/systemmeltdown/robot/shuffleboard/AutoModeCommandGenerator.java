package com.systemmeltdown.robot.shuffleboard;

import com.systemmeltdown.robot.commands.AutoDriveProportionalCommand;
import com.systemmeltdown.robot.commands.AutonomousMoveOffLineCommand;
import com.systemmeltdown.robot.commands.AutonomousShootCommand;
import com.systemmeltdown.robot.commands.IntakePivotCommandGroup;
import com.systemmeltdown.robot.commands.TurretRotateCommand;
import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.ShooterSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;
import com.systemmeltdown.robot.subsystems.TogglableLimelightSubsystem;
import com.systemmeltdown.robot.subsystems.TurretSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.SkidSteerDriveSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

/**
 * A Shuffleboard widget that allows editing of wait time and Automode actions.
 * 
 * @category Shuffleboard
 */
public class AutoModeCommandGenerator {
    public enum AutomodeActions {
        NONE,
        MOVE_OFF_LINE,
        SHOOT,
    }

    private class AutoActionChooser {
        protected NetworkTableEntry m_waitWidget;
        protected SendableChooser<AutomodeActions> m_chooser;

        protected AutoActionChooser(int index) {
            // Adds Chooser Widget
            ShuffleboardTab tab = Shuffleboard.getTab(m_tabTitle);
            m_chooser = new SendableChooser<>();

            //Sets options for chooser widget
            m_chooser.setDefaultOption("None", AutomodeActions.NONE);
            m_chooser.addOption("Shoot", AutomodeActions.SHOOT);
            m_chooser.addOption("Move Off Line", AutomodeActions.MOVE_OFF_LINE);
            tab.add("Auto Action " + index, m_chooser);

            // Adds Wait Time Widget
            NetworkTableEntry waitWidget = Shuffleboard.getTab(m_tabTitle)
                .add("Wait Time " + index, 0)
                .withWidget(BuiltInWidgets.kTextView).getEntry();

            m_waitWidget = waitWidget;
        }

        public Command getWaitCommand() {
            double waitTime = m_waitWidget.getDouble(0);
            return new WaitCommand(waitTime);
        }

        public Command getActionCommand() {
            switch (m_chooser.getSelected()) {
                case MOVE_OFF_LINE:
                    System.out.println("ACTION: MOVE OFF LINE");
                    return new AutonomousMoveOffLineCommand(m_driveSubsystem);
                case SHOOT:
                    System.out.println("ACTION: SHOOT");
                    int shooterSpeed = Constants.SHOOTER_MAX_SPEED_RPM;
                    double shootSeconds = Constants.AUTO_SHOOT_SECONDS_3_CELLS;
                    return new AutonomousShootCommand(
                        m_storageSubsystem,
                        m_turretSubsystem,
                        m_feederSubsystem,
                        m_shooterSubsystem,
                        m_limelightSubsystem,
                        shooterSpeed,
                        shootSeconds);
                case NONE:
                default:
                    System.out.println("ACTION: NONE");
                    return new WaitCommand(0);
            }
        }
    }

    private static String m_tabTitle;
    private AutoActionChooser[] choosers;
    private IntakeSubsystem m_intakeSubsystem;
    private SkidSteerDriveSubsystem m_driveSubsystem;
    private StorageSubsystem m_storageSubsystem;
    private TurretSubsystem m_turretSubsystem;
    private FeederSubsystem m_feederSubsystem;
    private ShooterSubsystem m_shooterSubsystem;
    private TogglableLimelightSubsystem m_limelightSubsystem;

    /**
     * @param tabTitle The title of the tab the widget should be added to.
     * @param index The index of the Wait Time widget, since there are more than one being created.
     */
    public AutoModeCommandGenerator(
        String tabTitle,
        IntakeSubsystem intakeSubsystem,
        SkidSteerDriveSubsystem driveSubsystem,
        StorageSubsystem storageSubsystem,
        TurretSubsystem turretSubsystem,
        FeederSubsystem feederSubsystem,
        ShooterSubsystem shooterSubsystem,
        TogglableLimelightSubsystem limelightSubsystem
    ) {
        m_tabTitle = tabTitle;
        m_intakeSubsystem = intakeSubsystem;
        m_driveSubsystem = driveSubsystem;
        m_storageSubsystem = storageSubsystem;
        m_turretSubsystem = turretSubsystem;
        m_feederSubsystem = feederSubsystem;
        m_shooterSubsystem = shooterSubsystem;
        m_limelightSubsystem = limelightSubsystem;

        choosers = new AutoActionChooser[3];
        choosers[0] = new AutoActionChooser(0);
        choosers[1] = new AutoActionChooser(1);
        choosers[2] = new AutoActionChooser(2);
    }

    public static void show() {
        Shuffleboard.selectTab(m_tabTitle);
    }

    public Command generateCommand() {
        return new SequentialCommandGroup(
            new IntakePivotCommandGroup(m_intakeSubsystem, Value.kReverse),
            choosers[0].getWaitCommand(),
            choosers[0].getActionCommand(),
            choosers[1].getWaitCommand(),
            choosers[1].getActionCommand(),
            choosers[2].getWaitCommand(),
            choosers[2].getActionCommand(),
            new ParallelCommandGroup(
                new ParallelRaceGroup(
                    new TurretRotateCommand(m_turretSubsystem, false),
                    new WaitCommand(Constants.TURRET_ROTATE_180_SECONDS)
                ),
                new ParallelRaceGroup(
                    new AutoDriveProportionalCommand(m_driveSubsystem, 0, Constants.AUTO_TURN_SPEED),
                    new WaitCommand(Constants.AUTO_TURN_SECONDS)
                )
            )
        );
    }
}

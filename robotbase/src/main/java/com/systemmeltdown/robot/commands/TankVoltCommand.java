package com.systemmeltdown.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.systemmeltdown.robotlib.controllers.DriverControls;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

public class TankVoltCommand extends CommandBase {
    private FalconTrajectoryDriveSubsystem m_driveSub;
    private DriverControls m_driverController;

    public TankVoltCommand(FalconTrajectoryDriveSubsystem driveSub, DriverControls driverController) {
        m_driveSub = driveSub;
        m_driverController = driverController;
        addRequirements(driveSub);
    }

    @Override
    public void execute() {
       m_driveSub.setTankDriveVolts(1, 1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
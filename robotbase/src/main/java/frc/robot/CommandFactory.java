package frc.robot;

import com.systemmeltdown.robotlib.commands.DriveProportionalCommand;
import com.systemmeltdown.robotlib.controllers.DriverControls;
import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedTalonDriveSubsystem;

public class CommandFactory {
    public static void createDriveProportionalCommand(SingleSpeedTalonDriveSubsystem driveSub, DriverControls driverControls) {
        driveSub.setDefaultCommand(new DriveProportionalCommand(driveSub, driverControls));
    }
}
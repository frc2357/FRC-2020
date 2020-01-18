package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.systemmeltdown.robotlib.subsystems.drive.SingleSpeedTalonDriveSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.TalonGroup;

public class SubsystemFactory {
    public static SingleSpeedTalonDriveSubsystem createDriveSubsystem() {
        SingleSpeedTalonDriveSubsystem driveSub = new SingleSpeedTalonDriveSubsystem(
                new TalonGroup(Constants.DRIVE_MOTOR_RIGHT_1, Constants.DRIVE_MOTOR_RIGHT_SLAVES),
                new TalonGroup(Constants.DRIVE_MOTOR_LEFT_1, Constants.DRIVE_MOTOR_LEFT_SLAVES));
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(SingleSpeedTalonDriveSubsystem.CONFIG_IS_RIGHT_INVERTED, true);
        configMap.put(SingleSpeedTalonDriveSubsystem.CONFIG_IS_LEFT_INVERTED, false);
        driveSub.configure(configMap);
        return driveSub;
    }
}
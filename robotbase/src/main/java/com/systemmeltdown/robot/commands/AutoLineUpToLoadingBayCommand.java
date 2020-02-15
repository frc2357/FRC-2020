package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robotlib.subsystems.LimelightSubsystem;
import com.systemmeltdown.robotlib.subsystems.drive.FalconTrajectoryDriveSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** 
* An auto command to line the robot up with the loading bay.
* 
* @category Automode
*/
public class AutoLineUpToLoadingBayCommand extends CommandBase {
    private LimelightSubsystem m_limeLightSub;
    private FalconTrajectoryDriveSubsystem m_falconTrajectoryDriveSub;

    /**
     * @param limeLightSub The {@link LimelightSubsystem}.
     * @param falconTrajectoryDriveSub The drive subsystem.
     */
    public AutoLineUpToLoadingBayCommand(LimelightSubsystem limeLightSub,
            FalconTrajectoryDriveSubsystem falconTrajectoryDriveSub) {
        m_limeLightSub = limeLightSub;
        m_falconTrajectoryDriveSub = falconTrajectoryDriveSub;

        addRequirements(m_limeLightSub, m_falconTrajectoryDriveSub);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

// limelight, drive subsystem
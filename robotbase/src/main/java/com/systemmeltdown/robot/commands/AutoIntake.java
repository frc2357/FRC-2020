package com.systemmeltdown.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;
import com.systemmeltdown.robot.subsystems.IntakeSubsystem;
import com.systemmeltdown.robot.subsystems.StorageSubsystem;

public class AutoIntake extends SequentialCommandGroup {
    IntakePickupWithoutController m_pickupWithout;
    
    public class countTillFive extends CommandBase {
        StorageSubsystem m_storageSub;
        public countTillFive(StorageSubsystem storageSub) {
            m_storageSub = storageSub;

            addRequirements(storageSub);
        }
        
        @Override
        public boolean isFinished() {
            return(m_storageSub.getNumOfCells() >= 5);
        }
    }

    public AutoIntake(IntakeSubsystem intakeSub, StorageSubsystem storageSub, FeederSubsystem feederSub) {
        addCommands(new IntakePickupWithoutController(intakeSub));

        addCommands(new ParallelDeadlineGroup(new countTillFive(storageSub), new RecountCellsCommand(storageSub, feederSub)));
        
        addRequirements(intakeSub);
    }
}

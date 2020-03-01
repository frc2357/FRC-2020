package com.systemmeltdown.robot.commands;

import com.systemmeltdown.robot.subsystems.FeederSubsystem;

public class WaitForFeederToClearCommand extends CommandLoggerBase {
  private FeederSubsystem m_feederSub;

  public WaitForFeederToClearCommand(FeederSubsystem feederSub) {
    m_feederSub = feederSub;
  }

  @Override
  public boolean isFinished() {
    return !m_feederSub.isFeedSensorBlocked();
  }
}

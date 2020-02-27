package com.systemmeltdown.robot.util;

import com.systemmeltdown.robotlib.subsystems.LimelightSubsystem.VisionTarget;

// TODO: Consider making this more general (not just vision) or including it in the LimelightSubsystem.
public abstract interface VisionTargetSupplier {
  public abstract VisionTarget getAsVisionTarget();
}

package com.systemmeltdown.robot.subsystems;

import com.systemmeltdown.robotlib.subsystems.LimelightSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;

public class TogglableLimelightSubsystem extends LimelightSubsystem {
    public enum PipelineIndex {
        UNKNOWN(-1),
        VISION_TARGET(0),
        HUMAN_VIEW(1);

        public final int index;

        private PipelineIndex(int index) {
            this.index = index;
        }

        public static PipelineIndex getPipelineByIndex(int index) {
            for (PipelineIndex i : PipelineIndex.values()) {
              if (i.index == index) {
                return i;
              }
            }
            return UNKNOWN;
          }
    }

    private NetworkTableEntry m_stream = super.m_Table.getEntry("stream");
    private NetworkTableEntry m_pipeline = super.m_Table.getEntry("pipeline");
    
    public TogglableLimelightSubsystem(boolean isLimelightPrimary) {
        setStream(isLimelightPrimary);
    }

    public void setPipeline(PipelineIndex p) {
        
        m_pipeline.setDouble(p.index);
    }

    public void setStream(boolean isLimelightPrimary) {
        m_stream.setValue(isLimelightPrimary ? 1 : 2);
    }

    public void toggleStream() {
        setStream(m_stream.getDouble(1.0) != 1.0);
    }
}
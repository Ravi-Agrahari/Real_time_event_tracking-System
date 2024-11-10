
package model;

import java.sql.Timestamp;


public class Metric {
    private int metricId;
    private int sessionId;
    private String metricType;
    private int metricValue;
    private Timestamp updatedAt;

    // Constructor, getters, and setters

    public Metric(int metricId, int sessionId, String metricType, int metricValue, Timestamp updatedAt) {
        this.metricId = metricId;
        this.sessionId = sessionId;
        this.metricType = metricType;
        this.metricValue = metricValue;
        this.updatedAt = updatedAt;
    }

    public int getMetricId() {
        return metricId;
    }

    public void setMetricId(int metricId) {
        this.metricId = metricId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public int getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(int metricValue) {
        this.metricValue = metricValue;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
}


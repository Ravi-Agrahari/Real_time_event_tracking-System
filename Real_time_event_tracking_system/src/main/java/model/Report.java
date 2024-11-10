
package model;

import java.sql.Timestamp;


public class Report {
    private int reportId;
    private int eventId;
    private String reportName;
    private Timestamp generatedOn;
    private String description;

    // Constructor, getters, and setters

    public Report(int reportId, int eventId, String reportName, Timestamp generatedOn, String description) {
        this.reportId = reportId;
        this.eventId = eventId;
        this.reportName = reportName;
        this.generatedOn = generatedOn;
        this.description = description;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Timestamp getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(Timestamp generatedOn) {
        this.generatedOn = generatedOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
}


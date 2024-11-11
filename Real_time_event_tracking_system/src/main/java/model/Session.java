package model;

import java.sql.Time;

public class Session {
    private int sessionId;
    private int eventId;
    private String sessionName;
    private Time startTime;
    private Time endTime;
    private String speaker;

    // Constructor, getters, and setters

    public Session(int sessionId, int eventId, String sessionName, Time startTime, Time endTime, String speaker) {
        this.sessionId = sessionId;
        this.eventId = eventId;
        this.sessionName = sessionName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.speaker = speaker;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}

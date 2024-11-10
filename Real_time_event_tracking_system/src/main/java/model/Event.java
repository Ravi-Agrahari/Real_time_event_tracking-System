
package model;

import java.sql.Date;


public class Event {
    private int eventId;
    private String eventName;
    private String location;
    private Date date;
    private String eventOrganizer ; 

    
    // Constructor
    public Event(int eventId, String eventName, String location, Date date, String eventOrganizer) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.eventOrganizer = eventOrganizer;
    }
    
    
    // Getters and setters 

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEventOrganizer() {
        return eventOrganizer;
    }

    public void setEventOrganizer(String eventOrganizer) {
        this.eventOrganizer = eventOrganizer;
    }

    
    
    
    
}

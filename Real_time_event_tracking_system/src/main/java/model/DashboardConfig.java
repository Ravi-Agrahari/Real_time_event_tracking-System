
package model;


public class DashboardConfig {
    private int configId;
    private int eventId;
    private int refreshRate;
    private String theme;

    // Constructor, getters, and setters

    public DashboardConfig(int configId, int eventId, int refreshRate, String theme) {
        this.configId = configId;
        this.eventId = eventId;
        this.refreshRate = refreshRate;
        this.theme = theme;
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    
    
}


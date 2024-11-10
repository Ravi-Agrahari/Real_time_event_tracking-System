
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import utility.DBConnection;


public class EventDAOImpl implements EventDAO {
    private final Connection connection;

    public EventDAOImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public void addEvent(Event event) throws SQLException {
        String sql = "INSERT INTO Event (event_name, location, date, event_organizer) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, event.getEventName());
            statement.setString(2, event.getLocation());
            statement.setDate(3, event.getDate());
            statement.setString(4, event.getEventOrganizer());
            statement.executeUpdate();
        }
    }
    
    
    

    @Override
    public Event getEventById(int eventId) throws SQLException {
        String sql = "SELECT * FROM Event WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, eventId);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                
                // making object of event by passing values in constructor
                Event event = new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getString("location"),
                        rs.getDate("date"),
                        rs.getString("event_organizer")
                        
                );
                
                return event;
            }
        }
        return null;
    }

    @Override
    public List<Event> getAllEvents() throws SQLException {
        
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Event";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                
                // making object of event by passing values in constructor
                Event event = new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_name"),
                        rs.getString("location"),
                        rs.getDate("date"),
                        rs.getString("event_organizer")
                );
                // adding in list ... 
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public void updateEvent(Event event) throws SQLException {
        String sql = "UPDATE Event SET event_name = ?, location = ?, date = ? , event_organizer = ? WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, event.getEventName());
            statement.setString(2, event.getLocation());
            statement.setDate(3, event.getDate());
            statement.setString(4, event.getEventOrganizer()) ; 
            statement.setInt(5, event.getEventId());
            
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteEvent(int eventId) throws SQLException {
        
        String sql = "DELETE FROM Event WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, eventId);
            statement.executeUpdate();
        }
        
    }
}

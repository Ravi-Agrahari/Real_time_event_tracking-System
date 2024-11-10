
package service;

import dao.EventDAO;
import dao.EventDAOImpl;
import java.sql.SQLException;
import java.util.List;
import model.Event;

public class EventService {
    
    private final EventDAO eventDAO ; 
    
    public EventService() throws SQLException{
        this.eventDAO = new EventDAOImpl(); 
    }
    
    
    public void addEvent(Event event) throws SQLException {
       eventDAO.addEvent(event);
    }
    
    
    
    public Event getEventById(int eventId) throws SQLException {
        return eventDAO.getEventById(eventId) ;
    }

    
    
    public List<Event> getAllEvents() throws SQLException {
        return eventDAO.getAllEvents() ; 
    }

   
    public void updateEvent(Event event) throws SQLException {
        eventDAO.updateEvent(event);
    }

   
    public void deleteEvent(int eventId) throws SQLException {
        eventDAO.deleteEvent(eventId);
        
    }
    
}

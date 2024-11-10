
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Event;


public interface EventDAO {
    void addEvent(Event event) throws SQLException;
    Event getEventById(int eventId) throws SQLException;
    List<Event> getAllEvents() throws SQLException;
    void updateEvent(Event event) throws SQLException;
    void deleteEvent(int eventId) throws SQLException;
}

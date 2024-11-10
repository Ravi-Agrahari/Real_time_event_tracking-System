
package dao;


import model.Session;
import java.sql.SQLException;
import java.util.List;

public interface SessionDAO {
    void addSession(Session session) throws SQLException;
    Session getSessionById(int sessionId) throws SQLException;
    List<Session> getAllSessions() throws SQLException;
    List<Session> getSessionsByEventId(int eventId) throws SQLException; // to get sessions for a specific event
    void updateSession(Session session) throws SQLException;
    void deleteSession(int sessionId) throws SQLException;
}

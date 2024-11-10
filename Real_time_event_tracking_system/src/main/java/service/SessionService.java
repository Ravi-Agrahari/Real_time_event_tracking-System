package service;

import dao.SessionDAO;
import dao.SessionDAOImpl;
import model.Session;

import java.sql.SQLException;
import java.util.List;

public class SessionService {
    
    private final SessionDAO sessionDAO;

    public SessionService() throws SQLException {
        this.sessionDAO = new SessionDAOImpl();
    }

    public void addSession(Session session) throws SQLException {
        sessionDAO.addSession(session);
    }

    public Session getSessionById(int sessionId) throws SQLException {
        return sessionDAO.getSessionById(sessionId);
    }

    public List<Session> getAllSessions() throws SQLException {
        return sessionDAO.getAllSessions();
    }

    public List<Session> getSessionsByEventId(int eventId) throws SQLException {
        return sessionDAO.getSessionsByEventId(eventId);
    }

    public void updateSession(Session session) throws SQLException {
        sessionDAO.updateSession(session);
    }

    public void deleteSession(int sessionId) throws SQLException {
        sessionDAO.deleteSession(sessionId);
    }
}

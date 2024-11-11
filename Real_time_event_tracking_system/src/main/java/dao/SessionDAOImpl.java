package dao;


import model.Session;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utility.DBConnection;

public class SessionDAOImpl implements SessionDAO {
    private final Connection connection;

    public SessionDAOImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public boolean addSession(Session session) throws SQLException {
        String sql = "INSERT INTO Session (event_id, session_name, start_time, end_time, speaker) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, session.getEventId());
            statement.setString(2, session.getSessionName());
            statement.setTime(3, session.getStartTime());
            statement.setTime(4, session.getEndTime());
            statement.setString(5, session.getSpeaker());
            int insertedSucessfully  = statement.executeUpdate();
            
            return insertedSucessfully > 0 ; 
        }
    }

    @Override
    public Session getSessionById(int sessionId) throws SQLException {
        String sql = "SELECT * FROM Session WHERE session_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sessionId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Session(
                    rs.getInt("session_id"),
                    rs.getInt("event_id"),
                    rs.getString("session_name"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getString("speaker")
                );
            }
        }
        return null;
    }

    @Override
    public List<Session> getAllSessions() throws SQLException {
        List<Session> sessions = new ArrayList<>();
        String sql = "SELECT * FROM Session";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                sessions.add(new Session(
                    rs.getInt("session_id"),
                    rs.getInt("event_id"),
                    rs.getString("session_name"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getString("speaker")
                ));
            }
        }
        return sessions;
    }

    @Override
    public List<Session> getSessionsByEventId(int eventId) throws SQLException {
        List<Session> sessions = new ArrayList<>();
        String sql = "SELECT * FROM Session WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, eventId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                sessions.add(new Session(
                    rs.getInt("session_id"),
                    rs.getInt("event_id"),
                    rs.getString("session_name"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getString("speaker")
                ));
            }
        }
        return sessions;
    }

    @Override
    public void updateSession(Session session) throws SQLException {
        String sql = "UPDATE Session SET event_id = ?, session_name = ?, start_time = ?, end_time = ?, speaker = ? WHERE session_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, session.getEventId());
            statement.setString(2, session.getSessionName());
            statement.setTime(3, session.getStartTime());
            statement.setTime(4, session.getEndTime());
            statement.setString(5, session.getSpeaker());
            statement.setInt(6, session.getSessionId());
            statement.executeUpdate();
        }
    }

    @Override
    public boolean deleteSession(int sessionId) throws SQLException {
        String sql = "DELETE FROM Session WHERE session_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sessionId);
            int done = statement.executeUpdate();
            
            return done > 0 ; 
        }
    }
}

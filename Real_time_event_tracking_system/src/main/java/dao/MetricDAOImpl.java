package dao;

import model.Metric;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utility.DBConnection;

public class MetricDAOImpl implements MetricDAO {
    private final Connection connection;

    public MetricDAOImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    @Override
    public boolean addMetric(Metric metric) throws SQLException {
        String sql = "INSERT INTO metric (session_id, metric_type, metric_value, updated_at) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, metric.getSessionId());
            stmt.setString(2, metric.getMetricType());
            stmt.setInt(3, metric.getMetricValue());
            stmt.setTimestamp(4, metric.getUpdatedAt());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Metric getMetricById(int metricId) throws SQLException {
        String sql = "SELECT * FROM metric WHERE metric_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, metricId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Metric(
                        rs.getInt("metric_id"),
                        rs.getInt("session_id"),
                        rs.getString("metric_type"),
                        rs.getInt("metric_value"),
                        rs.getTimestamp("updated_at")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Metric> getMetricsBySessionId(int sessionId) throws SQLException {
        String sql = "SELECT * FROM metric WHERE session_id = ?";
        List<Metric> metrics = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    metrics.add(new Metric(
                        rs.getInt("metric_id"),
                        rs.getInt("session_id"),
                        rs.getString("metric_type"),
                        rs.getInt("metric_value"),
                        rs.getTimestamp("updated_at")
                    ));
                }
            }
        }
        return metrics;
    }

    @Override
    public List<Metric> getAllMetrics() throws SQLException {
        String sql = "SELECT * FROM metric";
        List<Metric> metrics = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                metrics.add(new Metric(
                    rs.getInt("metric_id"),
                    rs.getInt("session_id"),
                    rs.getString("metric_type"),
                    rs.getInt("metric_value"),
                    rs.getTimestamp("updated_at")
                ));
            }
        }
        return metrics;
    }

    @Override
    public boolean updateMetric(Metric metric) throws SQLException {
        String sql = "UPDATE metric SET session_id = ?, metric_type = ?, metric_value = ?, updated_at = ? WHERE metric_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, metric.getSessionId());
            stmt.setString(2, metric.getMetricType());
            stmt.setInt(3, metric.getMetricValue());
            stmt.setTimestamp(4, metric.getUpdatedAt());
            stmt.setInt(5, metric.getMetricId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteMetric(int metricId) throws SQLException {
        String sql = "DELETE FROM metric WHERE metric_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, metricId);
            return stmt.executeUpdate() > 0;
        }
    }
}

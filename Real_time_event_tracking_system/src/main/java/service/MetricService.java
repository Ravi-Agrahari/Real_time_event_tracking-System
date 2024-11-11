package service;

import dao.MetricDAO;
import dao.MetricDAOImpl;
import model.Metric;

import java.sql.SQLException;
import java.util.List;

public class MetricService {
    
    private final MetricDAO metricDAO;

    public MetricService() throws SQLException {
        this.metricDAO = new MetricDAOImpl();
    }

    public boolean addMetric(Metric metric) throws SQLException {
        return metricDAO.addMetric(metric);
    }

    public Metric getMetricById(int metricId) throws SQLException {
        return metricDAO.getMetricById(metricId);
    }

    public List<Metric> getMetricsBySessionId(int sessionId) throws SQLException {
        return metricDAO.getMetricsBySessionId(sessionId);
    }

    public List<Metric> getAllMetrics() throws SQLException {
        return metricDAO.getAllMetrics();
    }

    public boolean updateMetric(Metric metric) throws SQLException {
        return metricDAO.updateMetric(metric);
    }

    public boolean deleteMetric(int metricId) throws SQLException {
        return metricDAO.deleteMetric(metricId);
    }
}

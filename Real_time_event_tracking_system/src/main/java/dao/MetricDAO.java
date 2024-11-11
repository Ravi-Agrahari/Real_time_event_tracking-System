
package dao;

import model.Metric;
import java.sql.SQLException;
import java.util.List;

public interface MetricDAO {
    boolean addMetric(Metric metric) throws SQLException;
    Metric getMetricById(int metricId) throws SQLException;
    List<Metric> getMetricsBySessionId(int sessionId) throws SQLException;
    List<Metric> getAllMetrics() throws SQLException;
    boolean updateMetric(Metric metric) throws SQLException;
    boolean deleteMetric(int metricId) throws SQLException;
}

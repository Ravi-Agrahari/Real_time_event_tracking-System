
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Metric;


public interface MetricDAO {
    void addMetric(Metric metric) throws SQLException;
    Metric getMetricById(int metricId) throws SQLException;
    List<Metric> getAllMetrics() throws SQLException;
    void updateMetric(Metric metric) throws SQLException;
    void deleteMetric(int metricId) throws SQLException;
}

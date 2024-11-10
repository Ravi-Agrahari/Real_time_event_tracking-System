
package dao;

import java.sql.SQLException;
import java.util.List;
import model.Report;


public interface ReportDAO {
    void generateReport(Report report) throws SQLException;
    List<Report> getReportsByEvent(int eventId) throws SQLException;
}


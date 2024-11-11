package dao;

import model.Report;

import java.sql.SQLException;
import java.util.List;

public interface ReportDAO {

    // Create a new report
    void addReport(Report report) throws SQLException;

    // Get a report by its ID
    Report getReportById(int reportId) throws SQLException;

    // Get all reports
    List<Report> getAllReports() throws SQLException;

    // Get reports by event ID
    List<Report> getReportsByEventId(int eventId) throws SQLException;

    // Update an existing report
    void updateReport(Report report) throws SQLException;

    // Delete a report by its ID
    void deleteReport(int reportId) throws SQLException;
}

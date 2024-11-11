package dao;

import model.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utility.DBConnection;

public class ReportDAOImpl implements ReportDAO {

    private final Connection connection;

    public ReportDAOImpl() throws SQLException {
        this.connection = DBConnection.getConnection(); // Assuming you have a DatabaseConnection class
    }

    @Override
    public void addReport(Report report) throws SQLException {
        String query = "INSERT INTO report (event_id, report_name, generated_on, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, report.getEventId());
            statement.setString(2, report.getReportName());
            statement.setTimestamp(3, report.getGeneratedOn());
            statement.setString(4, report.getDescription());
            statement.executeUpdate();
        }
    }

    @Override
    public Report getReportById(int reportId) throws SQLException {
        String query = "SELECT * FROM report WHERE report_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reportId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Report(
                    resultSet.getInt("reportId"),
                    resultSet.getInt("eventId"),
                    resultSet.getString("reportName"),
                    resultSet.getTimestamp("generatedOn"),
                    resultSet.getString("description")
                );
            }
        }
        return null;
    }

    @Override
    public List<Report> getAllReports() throws SQLException {
        String query = "SELECT * FROM report";
        List<Report> reports = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reports.add(new Report(
                    resultSet.getInt("reportId"),
                    resultSet.getInt("eventId"),
                    resultSet.getString("reportName"),
                    resultSet.getTimestamp("generatedOn"),
                    resultSet.getString("description")
                ));
            }
        }
        return reports;
    }

    @Override
    public List<Report> getReportsByEventId(int eventId) throws SQLException {
        String query = "SELECT * FROM report WHERE event_id = ?";
        List<Report> reports = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reports.add(new Report(
                    resultSet.getInt("reportId"),
                    resultSet.getInt("eventId"),
                    resultSet.getString("reportName"),
                    resultSet.getTimestamp("generatedOn"),
                    resultSet.getString("description")
                ));
            }
        }
        return reports;
    }

    @Override
    public void updateReport(Report report) throws SQLException {
        String query = "UPDATE report SET report_name = ?, generated_on = ?, description = ? WHERE report_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, report.getReportName());
            statement.setTimestamp(2, report.getGeneratedOn());
            statement.setString(3, report.getDescription());
            statement.setInt(4, report.getReportId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteReport(int reportId) throws SQLException {
        String query = "DELETE FROM report WHERE report_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reportId);
            statement.executeUpdate();
        }
    }
}


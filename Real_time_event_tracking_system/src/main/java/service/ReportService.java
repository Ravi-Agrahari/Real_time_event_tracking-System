package service;

import dao.ReportDAO;
import dao.ReportDAOImpl;
import model.Report;

import java.sql.SQLException;
import java.util.List;

public class ReportService {
    
    private final ReportDAO reportDAO;

    public ReportService() throws SQLException {
        this.reportDAO = new ReportDAOImpl();
    }

    public void addReport(Report report) throws SQLException {
        reportDAO.addReport(report);
    }

    public Report getReportById(int reportId) throws SQLException {
        return reportDAO.getReportById(reportId);
    }

    public List<Report> getAllReports() throws SQLException {
        return reportDAO.getAllReports();
    }

    public List<Report> getReportsByEventId(int eventId) throws SQLException {
        return reportDAO.getReportsByEventId(eventId);
    }

    public void updateReport(Report report) throws SQLException {
        reportDAO.updateReport(report);
    }

    public void deleteReport(int reportId) throws SQLException {
        reportDAO.deleteReport(reportId);
    }
}

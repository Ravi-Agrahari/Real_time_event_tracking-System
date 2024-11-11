package controller;

import model.Report;
import service.ReportService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class ReportController {

    private final ReportService reportService;

    public ReportController() throws SQLException {
        this.reportService = new ReportService();
    }

    // Menu for interacting with reports
    public void start() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nReport Management");
            System.out.println("1. Add Report");
            System.out.println("2. View All Reports");
            System.out.println("3. View Report by ID");
            System.out.println("4. Update Report");
            System.out.println("5. Delete Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addReport();
                case 2 -> viewAllReports();
                case 3 -> viewReportById();
                case 4 -> updateReport();
                case 5 -> deleteReport();
                case 6 -> {
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Add report
    private void addReport() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter Report Name: ");
        String reportName = scanner.nextLine();
        
        System.out.print("Enter Report Description: ");
        String description = scanner.nextLine();
        
        Report report = new Report(0, eventId, reportName, new Timestamp(System.currentTimeMillis()), description);
        reportService.addReport(report);
        System.out.println("Report added successfully!");
    }

    // View all reports
    private void viewAllReports() throws SQLException {
        List<Report> reports = reportService.getAllReports();
        for (Report report : reports) {
            System.out.println(report);
        }
    }

    // View report by ID
    private void viewReportById() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Report ID: ");
        int reportId = scanner.nextInt();

        Report report = reportService.getReportById(reportId);
        if (report != null) {
            System.out.println(report);
        } else {
            System.out.println("Report not found!");
        }
    }

    // Update report
    private void updateReport() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Report ID to update: ");
        int reportId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter new Report Name: ");
        String reportName = scanner.nextLine();
        
        System.out.print("Enter new Report Description: ");
        String description = scanner.nextLine();
        
        Report report = new Report(reportId, 0, reportName, new Timestamp(System.currentTimeMillis()), description);
        reportService.updateReport(report);
        System.out.println("Report updated successfully!");
    }

    // Delete report
    private void deleteReport() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Report ID to delete: ");
        int reportId = scanner.nextInt();
        
        reportService.deleteReport(reportId);
        System.out.println("Report deleted successfully!");
    }
}

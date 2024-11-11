package controller;

import model.Metric;
import service.MetricService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class MetricController {

    private final MetricService metricService;
    private final Scanner scanner;

    public MetricController() throws SQLException {
        this.metricService = new MetricService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> addMetric();
                case 2 -> viewMetricById();
                case 3 -> viewAllMetrics();
                case 4 -> deleteMetric();
                case 5 -> updateMetric();
                case 0 -> {
                    System.out.println("Exiting Metric Management...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Metric Management ---");
        System.out.println("1. Add Metric");
        System.out.println("2. View Metric by ID");
        System.out.println("3. View All Metrics");
        System.out.println("4. Delete Metric");
        System.out.println("5. Update Metric");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    public void addMetric() {
        try {
            System.out.print("Enter Session ID (must exist) : ");
            int sessionId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Metric Type: ");
            String metricType = scanner.nextLine();
            System.out.print("Enter Metric Value: ");
            int metricValue = scanner.nextInt();
            Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

            Metric metric = new Metric(0, sessionId, metricType, metricValue, updatedAt);
            boolean isAdded = metricService.addMetric(metric);
            System.out.println(isAdded ? "Metric added successfully!" : "Failed to add metric.");
        } catch (SQLException e) {
            System.out.println("Error adding metric: " + e.getMessage());
        }
    }

    public void viewMetricById() {
        try {
            System.out.print("Enter Metric ID: ");
            int metricId = scanner.nextInt();
            Metric metric = metricService.getMetricById(metricId);
            if (metric != null) {
                System.out.println("Metric_type: " + metric.getMetricType());
                System.out.println("Metric_value: " + metric.getMetricValue()) ; 
            } else {
                System.out.println("Metric not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving metric: " + e.getMessage());
        }
    }

    public void viewAllMetrics() {
        try {
            List<Metric> metrics = metricService.getAllMetrics();
            if (metrics.isEmpty()) {
                System.out.println("No metrics found.");
            } else {
                for(Metric metric : metrics){
                    System.out.println("Metric_type: " + metric.getMetricType());
                    System.out.println("Metric_value: " + metric.getMetricValue()) ;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving metrics: " + e.getMessage());
        }
    }

    public void deleteMetric() {
        try {
            System.out.print("Enter Metric ID to delete: ");
            int metricId = scanner.nextInt();
            boolean isDeleted = metricService.deleteMetric(metricId);
            System.out.println(isDeleted ? "Metric deleted successfully!" : "Failed to delete metric.");
        } catch (SQLException e) {
            System.out.println("Error deleting metric: " + e.getMessage());
        }
    }

    public void updateMetric() {
        try {
            System.out.print("Enter Metric ID to update: ");
            int metricId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter New Session ID: ");
            int sessionId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter New Metric Type: ");
            String metricType = scanner.nextLine();
            System.out.print("Enter New Metric Value: ");
            int metricValue = scanner.nextInt();
            Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

            Metric metric = new Metric(metricId, sessionId, metricType, metricValue, updatedAt);
            boolean isUpdated = metricService.updateMetric(metric);
            System.out.println(isUpdated ? "Metric updated successfully!" : "Failed to update metric.");
        } catch (SQLException e) {
            System.out.println("Error updating metric: " + e.getMessage());
        }
    }
}

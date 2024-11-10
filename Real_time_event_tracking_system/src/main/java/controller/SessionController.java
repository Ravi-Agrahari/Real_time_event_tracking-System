package controller;

import model.Session;
import service.SessionService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SessionController {
    private final SessionService sessionService;
    private final Scanner scanner;

    public SessionController() throws SQLException {
        this.sessionService = new SessionService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Session Management Dashboard ---");
            System.out.println("1. Add Session");
            System.out.println("2. View Session by ID");
            System.out.println("3. View All Sessions");
            System.out.println("4. View Sessions by Event ID");
            System.out.println("5. Update Session");
            System.out.println("6. Delete Session");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1 -> addSession();
                    case 2 -> viewSessionById();
                    case 3 -> viewAllSessions();
                    case 4 -> viewSessionsByEventId();
                    case 5 -> updateSession();
                    case 6 -> deleteSession();
                    case 7 -> {
                        System.out.println("Exiting Session Management...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private void addSession() throws SQLException {
        System.out.println("\n--- Add New Session ---");
        System.out.print("Enter Event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Session Name: ");
        String sessionName = scanner.nextLine();
        System.out.print("Enter Start Time (YYYY-MM-DD): ");
        Date startTime = Date.valueOf(scanner.nextLine());
        System.out.print("Enter End Time (YYYY-MM-DD): ");
        Date endTime = Date.valueOf(scanner.nextLine());
        System.out.print("Enter Speaker Name: ");
        String speaker = scanner.nextLine();

        Session session = new Session(0, eventId, sessionName, startTime, endTime, speaker);
        sessionService.addSession(session);
        System.out.println("Session added successfully.");
    }

    private void viewSessionById() throws SQLException {
        System.out.print("\nEnter Session ID: ");
        int sessionId = scanner.nextInt();
        Session session = sessionService.getSessionById(sessionId);
        if (session != null) {
            displaySession(session);
        } else {
            System.out.println("Session not found.");
        }
    }

    private void viewAllSessions() throws SQLException {
        System.out.println("\n--- All Sessions ---");
        List<Session> sessions = sessionService.getAllSessions();
        if (sessions.isEmpty()) {
            System.out.println("No sessions found.");
        } else {
            for (Session session : sessions) {
                displaySession(session);
            }
        }
    }

    private void viewSessionsByEventId() throws SQLException {
        System.out.print("\nEnter Event ID: ");
        int eventId = scanner.nextInt();
        List<Session> sessions = sessionService.getSessionsByEventId(eventId);
        if (sessions.isEmpty()) {
            System.out.println("No sessions found for this event.");
        } else {
            for (Session session : sessions) {
                displaySession(session);
            }
        }
    }

    private void updateSession() throws SQLException {
        System.out.print("\nEnter Session ID to update: ");
        int sessionId = scanner.nextInt();
        scanner.nextLine();
        Session session = sessionService.getSessionById(sessionId);
        if (session != null) {
            System.out.print("Enter new Session Name (current: " + session.getSessionName() + "): ");
            String sessionName = scanner.nextLine();
            System.out.print("Enter new Start Time (YYYY-MM-DD, current: " + session.getStartTime() + "): ");
            Date startTime = Date.valueOf(scanner.nextLine());
            System.out.print("Enter new End Time (YYYY-MM-DD, current: " + session.getEndTime() + "): ");
            Date endTime = Date.valueOf(scanner.nextLine());
            System.out.print("Enter new Speaker Name (current: " + session.getSpeaker() + "): ");
            String speaker = scanner.nextLine();

            session.setSessionName(sessionName);
            session.setStartTime(startTime);
            session.setEndTime(endTime);
            session.setSpeaker(speaker);
            sessionService.updateSession(session);

            System.out.println("Session updated successfully.");
        } else {
            System.out.println("Session not found.");
        }
    }

    private void deleteSession() throws SQLException {
        System.out.print("\nEnter Session ID to delete: ");
        int sessionId = scanner.nextInt();
        sessionService.deleteSession(sessionId);
        System.out.println("Session deleted successfully.");
    }

    private void displaySession(Session session) {
        System.out.println("\n--- Session Details ---");
        System.out.println("Session ID: " + session.getSessionId());
        System.out.println("Event ID: " + session.getEventId());
        System.out.println("Session Name: " + session.getSessionName());
        System.out.println("Start Time: " + session.getStartTime());
        System.out.println("End Time: " + session.getEndTime());
        System.out.println("Speaker: " + session.getSpeaker());
    }
}

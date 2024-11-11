package controller;

import java.sql.SQLException;
import model.Session;
import service.SessionService;

import java.sql.Time;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SessionController {
    private final SessionService sessionService ;
    private final Scanner scanner;

    // Constructor 
    public SessionController() throws SQLException {
        this.sessionService = new SessionService();
        this.scanner = new Scanner(System.in);
    }

    
    // Start Method 
    public void start() throws SQLException {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Session Dashboard ---");
            System.out.println("1. Add Session");
            System.out.println("2. View All Sessions");
            System.out.println("3. View Session by ID");
            System.out.println("4. Delete Session");
            System.out.println("5. Exit");

            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Clear newline

                switch (choice) {
                    case 1 -> addSession();
                    case 2 -> viewAllSessions();
                    case 3 -> viewSessionById();
                    case 4 -> deleteSession();
                    case 5 -> {
                        exit = true;
                        System.out.println("Exiting Session Dashboard...");
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }

    
    // method for adding a new session 
    private void addSession() {
        try {
            System.out.print("Enter Event ID(must exist): ");
            int eventId = scanner.nextInt();
            scanner.nextLine();  // Clear newline

            System.out.print("Enter Session Name: ");
            String sessionName = scanner.nextLine();

            System.out.print("Enter Start Time (HH:MM:SS): ");
            Time startTime = Time.valueOf(scanner.nextLine().trim());

            System.out.print("Enter End Time (HH:MM:SS): ");
            Time endTime = Time.valueOf(scanner.nextLine().trim());

            System.out.print("Enter Speaker: ");
            String speaker = scanner.nextLine();

            Session session = new Session(0, eventId, sessionName, startTime, endTime, speaker);
            boolean isAdded = sessionService.addSession(session);

            if (isAdded) {
                System.out.println("Session added successfully!");
            } else {
                System.out.println("Failed to add session.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid time format! Please enter time as HH:MM:SS.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data.");
            scanner.nextLine();  // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred while adding the session. Please try again.");
        }
    }

    
    // method for viewing all session in db 
    private void viewAllSessions() throws SQLException {
        System.out.println("\n--- All Sessions ---");
        sessionService.getAllSessions().forEach(session -> {
            System.out.println("Session ID: " + session.getSessionId());
            System.out.println("Event ID: " + session.getEventId());
            System.out.println("Session Name: " + session.getSessionName());
            System.out.println("Start Time: " + session.getStartTime());
            System.out.println("End Time: " + session.getEndTime());
            System.out.println("Speaker: " + session.getSpeaker());
            System.out.println("-------------------------");
        });
    }

    
    // method for getting session with the help of Id 
    private void viewSessionById() {
        try {
            System.out.print("Enter Session ID: ");
            int sessionId = scanner.nextInt();
            Session session = sessionService.getSessionById(sessionId);

            if (session != null) {
                System.out.println("Session ID: " + session.getSessionId());
                System.out.println("Event ID: " + session.getEventId());
                System.out.println("Session Name: " + session.getSessionName());
                System.out.println("Start Time: " + session.getStartTime());
                System.out.println("End Time: " + session.getEndTime());
                System.out.println("Speaker: " + session.getSpeaker());
            } else {
                System.out.println("Session not found.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid session ID.");
            scanner.nextLine();  // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving the session.");
            e.printStackTrace();
        }
    }

    
    // method for deleting the session 
    private void deleteSession() {
        try {
            System.out.print("Enter Session ID to delete: ");
            int sessionId = scanner.nextInt();

            boolean isDeleted = sessionService.deleteSession(sessionId);
            if (isDeleted) {
                System.out.println("Session deleted successfully.");
            } else {
                System.out.println("Failed to delete session or session not found.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid session ID.");
            scanner.nextLine();  // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the session.");
        }
    }
}

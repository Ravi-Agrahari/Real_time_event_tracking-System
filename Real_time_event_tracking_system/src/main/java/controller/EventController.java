
package controller;




import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Event;
import service.EventService;


public class EventController {
    
    private final EventService eventService ; 
    
    private Scanner scanner  ; 
    
    
    public EventController() throws SQLException{
        this.eventService = new EventService() ; 
        this.scanner = new Scanner(System.in) ; 
        
    }
    
    public void displayDashboard() {
        while (true) {
            System.out.println("\n===== Event Dashboard =====");
            System.out.println("1. View All Events");
            System.out.println("2. View Event by ID");
            System.out.println("3. Add Event");
            System.out.println("4. Update Event");
            System.out.println("5. Delete Event");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (choice) {
                case 1:
                    viewAllEvents();
                    break;
                case 2:
                    viewEventById();
                    break;
                case 3:
                    addEvent();
                    break;
                case 4:
                    updateEvent();
                    break;
                case 5:
                    deleteEvent();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    
    

    public void viewAllEvents() {
        try {
            List<Event> events = eventService.getAllEvents();
            if (events.isEmpty()) {
                System.out.println("No events found.");
            } else {
                for (Event event : events) {
                    System.out.println(event);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching events.");
        }
    }

    
    
    
    
    public  void viewEventById() {
        System.out.print("Enter Event ID: ");
        int eventId = scanner.nextInt();
        try {
            Event event = eventService.getEventById(eventId);
            if (event != null) {
                System.out.println(event);
            } else {
                System.out.println("Event not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching event.");
        }
    }

    public void addEvent() {
        System.out.print("Enter Event Name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter Event Organizer name: ");
        String event_organizer = scanner.nextLine() ; 

        try {
            Date date = Date.valueOf(dateStr);
            Event event = new Event(0, eventName, location, date,event_organizer); // eventId will be auto-generated
            eventService.addEvent(event);
            System.out.println("Event added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding event.");
        }
    }

    
    
    public void updateEvent() {
        System.out.print("Enter Event ID to update: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter new Event Name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter new Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter new Date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter Event Organizer name: ");
        String event_organizer = scanner.nextLine() ; 

        try {
            Date date = Date.valueOf(dateStr);
            Event event = new Event(eventId, eventName, location, date , event_organizer);
            eventService.updateEvent(event);
            System.out.println("Event updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating event.");
        }
    }

    
    
    public  void deleteEvent() {
        System.out.print("Enter Event ID to delete: ");
        int eventId = scanner.nextInt();
        try {
            eventService.deleteEvent(eventId);
            System.out.println("Event deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting event.");
        }
    }
    
    
    
    
    
    
    
    
}


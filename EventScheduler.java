import java.util.Scanner;
import java.util.LinkedList;
import java.text.SimpleDateFormat;
import java.util.Date;

class Event {
    private String title;
    private Date date;

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return "Title: " + title + "\nDate: " + sdf.format(date);
    }
}

public class EventScheduler {
    private LinkedList<Event> events;

    public EventScheduler() {
        events = new LinkedList<>();
    }

    public void addEvent(String title, Date date) {
        Event event = new Event(title, date);
        events.add(event);
    }

    public void removeEvent(int index) {
        if (index >= 0 && index < events.size()) {
            events.remove(index);
            System.out.println("Event removed.");
        } else {
            System.out.println("Invalid index. No event removed.");
        }
    }

    public void listEvents() {
        if (events.isEmpty()) {
            System.out.println("No events in the calendar.");
            return;
        }
        System.out.println("Events in the calendar:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println("Event #" + (i + 1));
            System.out.println(events.get(i));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        EventScheduler scheduler = new EventScheduler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Event Scheduler Menu:");
            System.out.println("1. Add Event");
            System.out.println("2. Remove Event");
            System.out.println("3. List Events");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter event title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter event date (dd-MM-yyyy HH:mm): ");
                    String dateString = scanner.nextLine();
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        Date date = sdf.parse(dateString);
                        scheduler.addEvent(title, date);
                        System.out.println("Event added.");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Event not added.");
                    }
                    break;
                case 2:
                    scheduler.listEvents();
                    System.out.print("Enter the index of the event to remove: ");
                    int index = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    scheduler.removeEvent(index - 1);
                    break;
                case 3:
                    scheduler.listEvents();
                    break;
                case 4:
                    scanner.close();
                    System.out.println("Exiting Event Scheduler. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

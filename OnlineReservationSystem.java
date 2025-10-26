import java.util.*;

class OnlineReservation {

    static Scanner sc = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>();
    static Map<Integer, String> reservations = new HashMap<>();
    static int pnrCounter = 1000;

    public static void main(String[] args) {
        users.put("admin", "1234"); // default login

        System.out.println("===== ONLINE RESERVATION SYSTEM =====");
        if (login()) {
            int choice = 0;

            do {
                System.out.println("\n1. Make Reservation");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        makeReservation();
                    case 2:
                        cancelReservation();
                    case 3:
                        System.out.println("Thank you for using the system!");
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 3);
        } else {
            System.out.println("Login failed. Exiting...");
        }
    }

    static boolean login() {
        System.out.print("Enter Login ID: ");
        String id = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (users.containsKey(id) && users.get(id).equals(pass)) {
            System.out.println("Login Successful!");
            return true;
        }
        return false;
    }

    static void makeReservation() {
        System.out.println("\n=== RESERVATION FORM ===");
        sc.nextLine(); // consume newline
        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Train Number: ");
        String trainNo = sc.next();
        System.out.print("Enter Train Name: ");
        String trainName = sc.next();
        System.out.print("Enter Class Type (Sleeper/AC): ");
        String classType = sc.next();
        System.out.print("Enter From Station: ");
        String from = sc.next();
        System.out.print("Enter To Station: ");
        String to = sc.next();
        System.out.print("Enter Date of Journey (DD/MM/YYYY): ");
        String date = sc.next();

        int pnr = pnrCounter++;
        String details = "PNR: " + pnr + ", Name: " + name + ", Train: " + trainNo + " (" + trainName + "), "
                + "Class: " + classType + ", From: " + from + ", To: " + to + ", Date: " + date;

        reservations.put(pnr, details);
        System.out.println("\nReservation Successful! Your PNR Number is: " + pnr);
    }

    static void cancelReservation() {
        System.out.print("\nEnter PNR Number to Cancel: ");
        int pnr = sc.nextInt();

        if (reservations.containsKey(pnr)) {
            System.out.println("Reservation Details:\n" + reservations.get(pnr));
            System.out.print("Are you sure you want to cancel? (yes/no): ");
            String confirm = sc.next();
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation Cancelled Successfully!");
            } else {
                System.out.println("Cancellation Aborted.");
            }
        } else {
            System.out.println("Invalid PNR Number!");
        }
    }
}
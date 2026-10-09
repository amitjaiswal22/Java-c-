import java.util.*;

class Movie {
    private String title;
    private String genre;
    private int durationMinutes;

    public Movie(String title, String genre, int durationMinutes) {
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getDurationMinutes() { return durationMinutes; }
}

class Theater {
    private String theaterName;
    private int totalRows;
    private int seatsPerRow;
    private boolean[][] bookedSeats;

    public Theater(String theaterName, int totalRows, int seatsPerRow) {
        this.theaterName = theaterName;
        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
        this.bookedSeats = new boolean[totalRows][seatsPerRow];
    }

    public void displaySeatMap() {
        System.out.println("\n--- Seat Map for " + theaterName + " ---");
        System.out.print("  ");
        for (int j = 1; j <= seatsPerRow; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < totalRows; i++) {
            char rowLabel = (char) ('A' + i);
            System.out.print(rowLabel + " ");
            for (int j = 0; j < seatsPerRow; j++) {
                if (bookedSeats[i][j]) {
                    System.out.print("X "); // X means booked
                } else {
                    System.out.print(". "); // . means available
                }
            }
            System.out.println();
        }
    }

    public boolean bookSeat(int row, int col) {
        if (row < 0 || row >= totalRows || col < 0 || col >= seatsPerRow) {
            System.out.println("Invalid seat selection! Out of bounds.");
            return false;
        }
        if (bookedSeats[row][col]) {
            System.out.println("Seat already booked! Please choose another.");
            return false;
        }
        bookedSeats[row][col] = true;
        return true;
    }
}

public class MovieBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Movie movie = new Movie("Interstellar", "Sci-Fi", 169);
        Theater theater = new Theater("PVR Cinemas - Screen 1", 5, 6);

        System.out.println("=== Welcome to Movie Ticket Booking System ===");
        System.out.println("Now Showing: " + movie.getTitle() + " (" + movie.getGenre() + ") - " + movie.getDurationMinutes() + " mins");

        while (true) {
            theater.displaySeatMap();
            System.out.println("\nOptions:");
            System.out.println("1. Book a Ticket");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            if (choice == 2) {
                System.out.println("Thank you for using Movie Booking System!");
                break;
            }

            System.out.print("Enter Row (e.g., A for 0, B for 1): ");
            char rowChar = scanner.next().toUpperCase().charAt(0);
            int rowIdx = rowChar - 'A';

            System.out.print("Enter Seat Number (1 to 6): ");
            int seatNum = scanner.nextInt();
            int colIdx = seatNum - 1;

            if (theater.bookSeat(rowIdx, colIdx)) {
                System.out.println("Booking Successful! Enjoy your movie.");
            }
            System.out.println("\n-------------------------------------------");
        }
        scanner.close();
    }
}

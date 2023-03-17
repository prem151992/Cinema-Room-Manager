package cinema;
import java.util.Scanner;

public class Cinema {

    public static void printCinemaLayout(char[][] cinemaSeats, int rows, int seatsPerRow) {
        System.out.println("Cinema:");

        for (int i = 0; i < seatsPerRow; i++) {
            if (i == 0) {
                System.out.print(" ");
            }
            System.out.print(" " + (i + 1));
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                if (j == 0) {
                    System.out.print((i + 1) + " " + cinemaSeats[i][j]);
                } else {
                    System.out.print(" " + cinemaSeats[i][j]);
                }

            }
            System.out.println();
        }
    }

    public static void bookCinemaSeats(char[][] cinemaSeats, int rowNumber, int seatNumber) {
        for (int i = 0; i < cinemaSeats.length; i++) {
            for (int j = 0; j < cinemaSeats[i].length; j++) {
                if (i == (rowNumber - 1) && j == (seatNumber - 1)) {
                    cinemaSeats[i][j] = 'B';
                }
            }
        }
    }

    public static boolean occupiedSeat(char[][] cinemaSeats, int rowNumber, int seatNumber) {
        if (cinemaSeats[rowNumber-1][seatNumber-1] == 'B') {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        char[][] cinemaSeats = new char[rows][seatsPerRow];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                cinemaSeats[i][j] = 'S';
            }
        }

        int firstHalf = rows / 2;
        int secondHalf = rows - firstHalf;
        int totalOccupancy = rows * seatsPerRow;
        int totalIncome = (totalOccupancy <= 60 ) ? totalOccupancy * 10: (firstHalf * seatsPerRow * 10) + (secondHalf * seatsPerRow * 8);
        int currentIncome = 0;
        int totalTicketsPurchased = 0;
        int selectionInput;


        while (true) {
            boolean ticketBooked = false;
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            selectionInput = scanner.nextInt();
            if (selectionInput == 1) {
                printCinemaLayout(cinemaSeats, rows, seatsPerRow);
            } else if (selectionInput == 2) {
                while (!ticketBooked) {
                    System.out.println("Enter a row number:");
                    int rowNumber = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int seatNumber = scanner.nextInt();

                    if (rowNumber > rows || seatNumber > seatsPerRow) {
                        System.out.println("Wrong input!");
                        System.out.println();
                        continue;
                    }

                    if (occupiedSeat(cinemaSeats, rowNumber, seatNumber)) {
                        System.out.println("That ticket has already been purchased!");
                        System.out.println();
                        continue;
                    }
                    totalTicketsPurchased += 1;
                    bookCinemaSeats(cinemaSeats, rowNumber, seatNumber);

                    int ticketPrice;

                    if (totalOccupancy <= 60) {
                        ticketPrice = 10;
                        currentIncome += 10;
                    } else {
                        if (rowNumber > firstHalf) {
                            ticketPrice = 8;
                            currentIncome += 8;
                        } else {
                            ticketPrice = 10;
                            currentIncome += 10;
                        }
                    }
                    System.out.println("Ticket price: " + "$" + ticketPrice);
                    ticketBooked = true;

                }
            } else if (selectionInput == 3) {
                System.out.printf("Number of purchased tickets: %d\n", totalTicketsPurchased);
                System.out.printf("Percentage: %.2f%%\n", (totalTicketsPurchased * 100) / (double) totalOccupancy);
                System.out.printf("Current income: $%d\n", currentIncome);
                System.out.printf("Total income: $%d\n", totalIncome);

            } else if (selectionInput == 0) {
                break;
            }

        }

//        printCinemaLayout(cinemaSeats, rows, seatsPerRow);


//        printCinemaLayout(cinemaSeats, rows, seatsPerRow);

    }
}
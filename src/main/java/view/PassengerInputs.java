package view;

import java.util.Scanner;

public class PassengerInputs {
    // validators must be added;
    private Scanner scan = new Scanner(System.in);

    public int getMenuItem() {
        System.out.println("Enter number of menu item to navigate through the application");
        int item = scan.nextInt();
        return item;
    }


    public String getDestination() {
        System.out.println("Enter your place of destination");
        String place = scan.nextLine();
        return validateDestination(place);
    }

    public String getDate() {
        System.out.println("Enter date of your flight");
        String date = scan.nextLine();
        return validateDate(date);
    }


    public int getTicketsQuantity() {
        System.out.println("Enter how many people are going to take a flight? ");
        int tickets = scan.nextInt();
        return validateTicketsQuantity(tickets);
    }


    public String getName() {
        System.out.println("Enter your name");
        return scan.nextLine();
    }

    public String getSurname() {
        System.out.println("Enter your surname");
        return scan.nextLine();
    }

    public String getFlightId() {
        System.out.println("Enter your flight ID");
        return scan.nextLine();
    }

// validators goes here
    private String validateDestination(String city) {
        String cityAgain = scan.nextLine();
        if (city.length() < 2) {
            return validateDestination(cityAgain);
        }
        boolean check = city.matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
        if (!check) {
            System.out.println("You have to enter correct city name");
        } else {
            return city;
        }
        return validateDestination(cityAgain);
    }

    private String validateDate(String date) {
        String dateAgain = scan.nextLine();
        boolean check = date.matches("([0-9]{2})\\.([0-9]{2})\\.([0-9]{4})");
        if (!check) {
            System.out.println("Not valid date. Enter the date at format dd.mm.yyyy");
        } else {
            return date;
        }
        return validateDate(dateAgain);
    }

    private int validateTicketsQuantity(int tickets) {
        return tickets;
    }

}

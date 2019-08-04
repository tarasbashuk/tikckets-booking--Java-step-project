package view;

import java.util.Scanner;

public class PassengerInputs {
    // validators must be added;
    private Scanner scan = new Scanner(System.in);


    public int getMenuItem() {
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


    public String getLogin() {
        System.out.println("Enter the login");
        return scan.next();
    }


    public String getPassword() {
        System.out.println("Enter the password");
        return scan.next();
    }

    public String getName() {
        System.out.println("Enter your name");
        return scan.next();
    }

    public String getSurname() {
        System.out.println("Enter your surname");
        return scan.next();
    }

    public String getFlightId() {
        System.out.println("Enter your flight ID");
        return scan.next();
    }
    public String getBookingId() {
        System.out.println("Enter your booking ID");
        return scan.next();
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

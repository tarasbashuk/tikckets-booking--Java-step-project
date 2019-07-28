package view;

import controllers.BookingController;
import entities.Passenger;

public class ConsoleView {
    // here Passenger's input and Controlles must combine

    BookingController bc = new BookingController();
    PassengerInputs pi = new PassengerInputs();
//    FlightController bc = new FlightController();


    public void showMenu() {
        System.out.println("1.Онайн-табло;");
        System.out.println("2.Посмотреть информацию о рейсе;");
        System.out.println("3.Поиск и бронировка рейса;");
        System.out.println("4.Отменить бронирование;");
        System.out.println("5.Мои рейсы;");
        System.out.println("6.Выход;");
    }


    public void MenuItemReader(int itemInput) {
        int inputItemAgaing = pi.getMenuItem();
        switch (itemInput) {
            case 1:
                // get flight for 24 hours
                break;
            case 2:
                // get flight by id
                // then show all the info about flight(date, time, destination, free seats)
                this.showMenu();
                break;
            case 3:
                // Search for the flight and booking
                break;
            case 4:
                // cancel booking
                String flightId = pi.getFlightId();
                bc.cancelBooking(flightId);
                break;
            case 5:
                // get my bookings
                String name = pi.getName();
                String surname = pi.getSurname();
                bc.getFlightsOfPassenger(new Passenger(name, surname));
                break;
            case 6:
                // exit
                System.exit(1);
                break;
            default:
                System.out.println("Try enter menu item again ");
                MenuItemReader(inputItemAgaing);
        }
    }
}

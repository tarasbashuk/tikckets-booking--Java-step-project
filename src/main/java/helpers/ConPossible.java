package helpers;

import entities.AirTrip;

import java.util.function.Predicate;

public class ConPossible implements Predicate<AirTrip> {
    private AirTrip origin;

    public ConPossible(AirTrip origin) {
        this.origin = origin;
    }

    @Override
    public boolean test(AirTrip trip) {
        boolean noTimeToTransfer = trip.getDepart().getTime() - origin.getArrival().getTime() < (60 * 60 * 1000);
        boolean tooLongWaiting = trip.getDepart().getTime() - origin.getArrival().getTime() > (12 * 60 * 60 * 1000);
        boolean citiesMatch = origin.getDestination().equals(trip.getFrom());

        return (citiesMatch & !noTimeToTransfer & !tooLongWaiting);
    }
}

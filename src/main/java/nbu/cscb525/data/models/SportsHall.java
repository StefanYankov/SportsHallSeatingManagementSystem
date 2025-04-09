package nbu.cscb525.data.models;

import nbu.cscb525.common.exceptions.ExceptionMessages;
import nbu.cscb525.common.exceptions.InsufficientSeatsException;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SportsHall {

    private final ConcurrentMap<SeatCategory, Integer> seatCategories;

    public SportsHall() {
        this.seatCategories = new ConcurrentHashMap<>();
        this.seatCategories.put(SeatCategory.STANDARD, 100);
        this.seatCategories.put(SeatCategory.PREMIUM, 25);
        this.seatCategories.put(SeatCategory.ECONOMY, 50);
    }
    public SportsHall(Map<SeatCategory, Integer> capacity) {
        if (capacity == null || capacity.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.CAPACITIES_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.seatCategories = new ConcurrentHashMap<>(capacity);
    }

    public void seatAGroup(SeatCategory seatCategory, int countOfPeople) throws InsufficientSeatsException {
        seatCategories.compute(seatCategory, (key, currentSeats) -> {
            if (currentSeats == null || currentSeats < countOfPeople) {
                throw new InsufficientSeatsException(
                        MessageFormat.format(ExceptionMessages.FULL_CAPACITY_SEAT_CATEGORY, seatCategory));
            }
            return currentSeats - countOfPeople;
        });
    }

    public String getSeatState() {
        StringBuffer seatState = new StringBuffer();
        for (SeatCategory seatCategory : seatCategories.keySet()) {
            seatState
                    .append(seatCategory.name())
                    .append(": ")
                    .append(seatCategories.get(seatCategory))
                    .append(" seats remaining").append(System.lineSeparator());
        }
        return seatState.toString();
    }
}

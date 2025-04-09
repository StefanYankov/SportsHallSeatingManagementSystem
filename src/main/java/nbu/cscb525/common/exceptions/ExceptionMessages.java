package nbu.cscb525.common.exceptions;

public class ExceptionMessages {

    public static final String FULL_CAPACITY_SEAT_CATEGORY = "Category of type {0} is at full capacity";
    public static final String CAPACITIES_CANNOT_BE_NULL_OR_EMPTY = "Custom capacities map cannot be null or empty";

    private ExceptionMessages() {
        throw new IllegalStateException("Utility class");
    }
}

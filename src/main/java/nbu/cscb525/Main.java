package nbu.cscb525;

import nbu.cscb525.common.exceptions.InsufficientSeatsException;
import nbu.cscb525.data.models.SeatCategory;
import nbu.cscb525.data.models.SportsHall;

import java.text.MessageFormat;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        // Initialize seat categories with default capacity
        SportsHall sportsHall = new SportsHall();

        // Create a fixed thread pool with 4 threads (one per entrance)
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        // Create an array to hold CompletableFuture objects for 10 groups
        CompletableFuture<Void>[] futures = new CompletableFuture[10];

        // Simulate 10 groups entering the hall
        for (int i = 0; i < 10; i++) {
            int groupSize = getRandomGroupSize();
            SeatCategory seatCategory = getRandomSeatCategory();

            futures[i] = CompletableFuture.runAsync(() -> {

                // from the thread name get the id (getId depricated in this java version) to simulate entrance based on the thread pool
                String entranceNumber = Thread.currentThread().getName().split("-")[3];

                try {
                    System.out.println(MessageFormat.format("Entrance {0}: Attempting to seat group of {1} in {2}", entranceNumber, groupSize, seatCategory.name()));
                    sportsHall.seatAGroup(seatCategory, groupSize);
                    System.out.println(MessageFormat.format("Entrance {0}: Successfully seated group of {1} in {2}", entranceNumber, groupSize, seatCategory.name()));
                } catch (InsufficientSeatsException e) {
                    System.out.println(MessageFormat.format("Entrance {0}: Failed to seat group of {1} in {2} - {3}",entranceNumber, groupSize, seatCategory.name(), e.getMessage()));
                }

 //                 // uncomment to "slow down" the concurrent execution
//                try {
//                    // for demonstration purpose
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }, executor);
        }

        // Wait for all CompletableFuture tasks to complete
        CompletableFuture.allOf(futures).join();

        // Shutdown the executor
        executor.shutdown();

        // Print the final state of the sports hall
        System.out.println(sportsHall.getSeatState());
    }

    // Helper method to generate a random group size (between 1 and 15)
    private static int getRandomGroupSize() {
        return ThreadLocalRandom.current().nextInt(1, 16);
    }

    // Helper method to get random seat category
    private static SeatCategory getRandomSeatCategory() {
        SeatCategory[] categories = SeatCategory.values();
        int index = ThreadLocalRandom.current().nextInt(0, categories.length);
        return categories[index];
    }
}
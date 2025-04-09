# Sports Hall Seating Simulator

The **Sports Hall Seating Simulator** is a Java-based console application developed as a project for the [**CSCB525 Applied Programming in Java**](https://ecatalog.nbu.bg/default.asp?V_Year=2022&YSem=5&Spec_ID=&Mod_ID=285&PageShow=coursepresent&P_Menu=courses_part2&Fac_ID=3&M_PHD=0&P_ID=2206&TabIndex=1&K_ID=44851&K_TypeID=10&l=1) course at the [**New Bulgarian University**](https://www.nbu.bg/en). It simulates seating groups of spectators in a sports hall with multiple seat categories, using concurrency to model four entrances processing groups simultaneously.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Seat Category Management**: Supports three seat categories (`STANDARD`, `PREMIUM`, `ECONOMY`) with predefined capacities.
- **Concurrent Entrances**: Simulates 4 entrances using a thread pool to seat groups concurrently.
- **Group Seating**: Processes at least 10 groups with random sizes and category preferences.
- **Thread Safety**: Uses `ConcurrentHashMap` for safe seat allocation across threads.
- **Exception Handling**: Handles insufficient seat scenarios with a custom `InsufficientSeatsException`.
- **Output**: Logs seating attempts, successes, and failures per entrance, plus a final seat state.

## Architecture

The application uses a simple, single-layer architecture focused on concurrency:

- **Data Model**:
    - `SeatCategory`: Enum for seat types.
    - `SportsHall`: Manages seat allocations with a `ConcurrentHashMap`.
- **Concurrency Logic**:
    - `Main`: Orchestrates 10 group-seating tasks across 4 threads using `ExecutorService` and `CompletableFuture`.
- **Exception Handling**: Custom `InsufficientSeatsException` for seat shortages.

### Technologies Used

- **Java**: Core programming language (version unspecified, compatible with modern JDKs).
- **Java Concurrency**: `ExecutorService`, `CompletableFuture`, and `ConcurrentHashMap` for thread-safe operations.
- **ThreadLocalRandom**: For generating random group sizes and categories.

## Installation

Follow these steps to set up the project locally:

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/sports-hall-seating-simulator.git
   ```

2. **Navigate to the project directory**:

   ```bash
   cd sports-hall-seating-simulator
   ```

3. **Compile and run** (assuming a simple Java setup without a build tool):

   ```bash
   javac -d bin src/nbu/cscb525/.java src/nbu/cscb525/data/models/.java src/nbu/cscb525/common/exceptions/*.java
   java -cp bin nbu.cscb525.Main
   ```

- Note: If you’re using an IDE (e.g., IntelliJ, Eclipse), import the project and run `Main.java` directly.

## Usage

The application runs in the console and simulates seating 10 random groups through 4 entrances. No user input is required—it automatically generates group sizes (1–15) and seat categories, then processes them concurrently.

### Example Output

   ```text
   Entrance 1: Attempting to seat group of 7 in ECONOMY
   Entrance 1: Successfully seated group of 7 in ECONOMY
   Entrance 2: Attempting to seat group of 12 in PREMIUM
   Entrance 2: Successfully seated group of 12 in PREMIUM
   Entrance 3: Attempting to seat group of 15 in PREMIUM
   Entrance 3: Failed to seat group of 15 in PREMIUM - Category of type PREMIUM is at full capacity
   Entrance 4: Attempting to seat group of 3 in STANDARD
   Entrance 4: Successfully seated group of 3 in STANDARD
   ...
   STANDARD: 97 seats remaining
   PREMIUM: 13 seats remaining
   ECONOMY: 43 seats remaining
   ```

## Contributing

As this is a university course project, contributions are not expected.

## License

This project is licensed under the MIT License. See the [LICENSE]() file for details.

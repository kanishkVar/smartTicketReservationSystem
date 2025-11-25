package model;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Database {

    private static final Path TRIPS = Paths.get("data/trips.csv");
    private static final Path TICKETS = Paths.get("data/tickets.csv");

    // Initialize files/folders
    public static void init() {
        try {
            Files.createDirectories(TRIPS.getParent());
            if (Files.notExists(TRIPS)) Files.createFile(TRIPS);
            if (Files.notExists(TICKETS)) Files.createFile(TICKETS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------------- TRIPS -------------------

    public static synchronized void addTrip(Trip trip) {
        try (BufferedWriter w = Files.newBufferedWriter(TRIPS, StandardOpenOption.APPEND)) {
            w.write(trip.toCSV());
            w.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized List<Trip> getAllTrips() {
        try {
            return Files.lines(TRIPS)
                    .map(Trip::fromCSV)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // ------------------- TICKETS -------------------

    public static synchronized void addTicket(Ticket ticket) {
        try (BufferedWriter w = Files.newBufferedWriter(TICKETS, StandardOpenOption.APPEND)) {
            w.write(ticket.toCSV());
            w.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized List<Ticket> getAllTickets() {
        try {
            return Files.lines(TICKETS)
                    .map(Ticket::fromCSV)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static synchronized void deleteTicketById(String id) {
        try {
            List<String> remaining = Files.lines(TICKETS)
                    .filter(line -> {
                        Ticket t = Ticket.fromCSV(line);
                        return t == null || !t.getId().equals(id);
                    })
                    .collect(Collectors.toList());

            Files.write(TICKETS, remaining, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

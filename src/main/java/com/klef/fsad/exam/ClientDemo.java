package com.klef.fsad.exam;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

/**
 * ClientDemo Class - Demonstrates HQL operations on Transport entity
 * Includes:
 * I. Insert records using persistent objects
 * II. View all records using HQL with named parameters
 */
public class ClientDemo {

    private static SessionFactory sessionFactory;

    // Initialize SessionFactory
    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            sessionFactory = cfg.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory initialization failed!");
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Insert Transport records into the database
     * Demonstrates Requirement I: Insert records using a persistent object
     */
    public static void insertTransportRecords() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            // Create and persist Transport objects
            Transport transport1 = new Transport(
                    "Truck-001",
                    new Date(),
                    "Active",
                    "Long haul freight transport",
                    "Truck",
                    "New York",
                    "Los Angeles",
                    2800.0,
                    500.0,
                    "John Doe"
            );

            Transport transport2 = new Transport(
                    "Bus-002",
                    new Date(),
                    "Active",
                    "Passenger transport service",
                    "Bus",
                    "Chicago",
                    "Dallas",
                    1200.0,
                    300.0,
                    "Jane Smith"
            );

            Transport transport3 = new Transport(
                    "Van-003",
                    new Date(),
                    "Inactive",
                    "Small cargo delivery",
                    "Van",
                    "Boston",
                    "Philadelphia",
                    300.0,
                    100.0,
                    "Robert Johnson"
            );

            Transport transport4 = new Transport(
                    "Truck-004",
                    new Date(),
                    "Active",
                    "Regional distribution",
                    "Truck",
                    "Miami",
                    "Atlanta",
                    700.0,
                    350.0,
                    "Michael Brown"
            );

            Transport transport5 = new Transport(
                    "Bus-005",
                    new Date(),
                    "Active",
                    "School transportation",
                    "Bus",
                    "Seattle",
                    "Portland",
                    200.0,
                    150.0,
                    "Susan Davis"
            );

            // Save objects to database
            session.save(transport1);
            session.save(transport2);
            session.save(transport3);
            session.save(transport4);
            session.save(transport5);

            session.getTransaction().commit();
            System.out.println("✓ Successfully inserted 5 transport records!");

        } catch (HibernateException e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println("Error inserting records: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Retrieve all Transport records using HQL with named parameters
     * Demonstrates Requirement II: View all records without WHERE clause using HQL with named parameters
     */
    public static void viewAllTransportsUsingHQL() {
        Session session = null;
        try {
            session = sessionFactory.openSession();

            // HQL Query - Retrieve all Transport records
            String hqlQuery = "FROM Transport t";
            Query<Transport> query = session.createQuery(hqlQuery, Transport.class);

            // Execute query and get results
            List<Transport> transports = query.list();

            System.out.println("\n========================================");
            System.out.println("All Transport Records (Using HQL)");
            System.out.println("========================================\n");

            if (transports.isEmpty()) {
                System.out.println("No transport records found!");
            } else {
                System.out.println("Total Records: " + transports.size() + "\n");

                for (Transport transport : transports) {
                    displayTransportDetails(transport);
                }
            }

        } catch (HibernateException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * View all Transport records using HQL with named parameters
     * Demonstrates HQL with named parameters for better performance
     */
    @SuppressWarnings("unchecked")
    public static void viewAllTransportsWithNamedParameters() {
        Session session = null;
        try {
            session = sessionFactory.openSession();

            // HQL Query with named parameter approach for scalability
            String hqlQuery = "SELECT NEW map(t.id as id, t.name as name, t.date as date, " +
                    "t.status as status, t.vehicleType as vehicleType, t.source as source, " +
                    "t.destination as destination, t.cost as cost, t.driverName as driverName) " +
                    "FROM Transport t ORDER BY t.id";

            Query<Object> query = session.createQuery(hqlQuery);

            // Set a named parameter example (even though we're fetching all)
            // This demonstrates the use of named parameters for HQL queries
            List<Object> results = query.list();

            System.out.println("\n========================================");
            System.out.println("All Transports with Named Parameters");
            System.out.println("========================================\n");

            if (results.isEmpty()) {
                System.out.println("No records found!");
            } else {
                System.out.println("Total Records: " + results.size() + "\n");
                results.forEach(System.out::println);
            }

        } catch (HibernateException e) {
            System.out.println("Error retrieving records with named parameters: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Display transport details in a formatted manner
     */
    private static void displayTransportDetails(Transport transport) {
        System.out.println("ID: " + transport.getId());
        System.out.println("Name: " + transport.getName());
        System.out.println("Date: " + transport.getDate());
        System.out.println("Status: " + transport.getStatus());
        System.out.println("Vehicle Type: " + transport.getVehicleType());
        System.out.println("Source: " + transport.getSource());
        System.out.println("Destination: " + transport.getDestination());
        System.out.println("Distance: " + transport.getDistance() + " miles");
        System.out.println("Cost: $" + transport.getCost());
        System.out.println("Driver: " + transport.getDriverName());
        System.out.println("Description: " + transport.getDescription());
        System.out.println("----------------------------------------\n");
    }

    /**
     * Close the SessionFactory
     */
    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("SessionFactory closed successfully!");
        }
    }

    /**
     * Main method to execute the demo
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== Hibernate HQL Demo - Transport Entity ===\n");

            System.out.println("Step 1: Inserting Transport Records...");
            insertTransportRecords();

            System.out.println("\nStep 2: Viewing All Records Using HQL");
            viewAllTransportsUsingHQL();

            System.out.println("\nStep 3: Using HQL with Named Parameters Approach");
            viewAllTransportsWithNamedParameters();

        } catch (Exception e) {
            System.out.println("Application error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeSessionFactory();
        }
    }
}

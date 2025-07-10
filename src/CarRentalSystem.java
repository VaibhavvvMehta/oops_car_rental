import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;

// Main class demonstrating the Car Rental System with Interactive Menu
public class CarRentalSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static RentalAgency agency;
    private static int nextCustomerId = 1;
    private static int nextVehicleId = 1;

    public static void main(String[] args) {
        // Initialize the rental agency
        agency = new RentalAgency("City Car Rentals", "456 Elm Street, Metropolis", "555-1234");

        // Add some initial vehicles to the fleet
        initializeFleet();

        // Display welcome message
        displayWelcomeMessage();

        // Main system loop
        boolean running = true;
        while (running) {
            displayUserTypeMenu();
            int userType = getIntInput("Select user type: ");

            switch (userType) {
                case 1:
                    customerInterface();
                    break;
                case 2:
                    adminInterface();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nThank you for using " + agency.getAgencyName() + "!");
                    break;
                default:
                    System.out.println("\n❌ Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void initializeFleet() {
        // Add some initial vehicles
        Car car1 = new Car("CAR001", "Toyota", "Camry", 2022, "Silver", 15000, 40.0, 4, "Gasoline", "Automatic", true);
        Car car2 = new Car("CAR002", "Honda", "Civic", 2021, "Red", 30000, 35.0, 4, "Gasoline", "Manual", false);
        Motorcycle moto1 = new Motorcycle("MOTO001", "Yamaha", "MT-07", 2023, "Black", 5000, 25.0, 689, "Standard", false, false);
        Motorcycle moto2 = new Motorcycle("MOTO002", "Harley-Davidson", "Street 750", 2022, "Blue", 8000, 45.0, 749, "Cruiser", false, true);

        agency.addVehicle(car1);
        agency.addVehicle(car2);
        agency.addVehicle(moto1);
        agency.addVehicle(moto2);

        nextVehicleId = 5; // Start next vehicle ID after existing ones
    }

    private static void displayWelcomeMessage() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     WELCOME TO " + agency.getAgencyName().toUpperCase());
        System.out.println("     " + agency.getAddress());
        System.out.println("     Phone: " + agency.getPhoneNumber());
        System.out.println("=".repeat(50));
    }

    private static void displayUserTypeMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("       SELECT USER TYPE");
        System.out.println("=".repeat(40));
        System.out.println("1. 👥 Customer");
        System.out.println("2. 🛠️ Admin");
        System.out.println("0. 🚪 Exit");
        System.out.println("=".repeat(40));
    }

    private static void customerInterface() {
        System.out.println("\nWelcome Customer!");
        System.out.println("1. Register as New Customer");
        System.out.println("2. View Available Cars");
        System.out.println("3. Book a Car");
        System.out.println("4. Calculate Rental Cost");
        System.out.println("0. Back to User Type Menu");

        int choice = getIntInput("Enter your choice: ");
        switch (choice) {
            case 1:
                registerNewCustomer();
                break;
            case 2:
                viewAvailableVehicles();
                break;
            case 3:
                createRental();
                break;
            case 4:
                calculateRentalCost();
                break;
            case 0:
                return; // Exit to main user type menu
            default:
                System.out.println("Invalid choice! Returning to User Type Menu.");
        }
    }

    private static void adminInterface() {
        System.out.println("\nWelcome Admin!");
        System.out.println("1. Add New Car");
        System.out.println("0. Back to User Type Menu");

        int choice = getIntInput("Enter your choice: ");
        switch (choice) {
            case 1:
                addNewVehicle();
                break;
            case 0:
                return; // Exit to main user type menu
            default:
                System.out.println("Invalid choice! Returning to User Type Menu.");
        }
    }

    private static void calculateRentalCost() {
        System.out.println("\nCalculating Rental Cost... (feature would be implemented here)");
        // Placeholder for calculation logic
    }

    private static void registerNewCustomer() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("       CUSTOMER REGISTRATION");
        System.out.println("=".repeat(40));

        String customerId = "CUST" + String.format("%03d", nextCustomerId++);

        System.out.println("Customer ID: " + customerId);
        String firstName = getStringInput("First Name: ");
        String lastName = getStringInput("Last Name: ");
        String email = getStringInput("Email: ");
        String phone = getStringInput("Phone Number: ");
        String license = getStringInput("License Number: ");
        int age = getIntInput("Age: ");

        System.out.print("Do you have a motorcycle license? (y/n): ");
        boolean hasMotorcycleLicense = scanner.nextLine().toLowerCase().startsWith("y");

        double creditLimit = getDoubleInput("Credit Limit: $");

        Customer customer = new Customer(customerId, firstName, lastName, email, phone, license, age, hasMotorcycleLicense, creditLimit);
        agency.registerCustomer(customer);

        System.out.println("\n✅ Customer registered successfully!");
        customer.displayCustomerInfo();
    }

    private static void addNewVehicle() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         ADD NEW VEHICLE");
        System.out.println("=".repeat(40));
        System.out.println("1. 🚗 Add Car");
        System.out.println("2. 🏍️ Add Motorcycle");

        int vehicleType = getIntInput("Select vehicle type: ");

        if (vehicleType == 1) {
            addNewCar();
        } else if (vehicleType == 2) {
            addNewMotorcycle();
        } else {
            System.out.println("❌ Invalid vehicle type!");
        }
    }

    private static void addNewCar() {
        System.out.println("\n--- CAR DETAILS ---");

        String vehicleId = "CAR" + String.format("%03d", nextVehicleId++);
        System.out.println("Vehicle ID: " + vehicleId);

        String brand = getStringInput("Brand: ");
        String model = getStringInput("Model: ");
        int year = getIntInput("Year: ");
        String color = getStringInput("Color: ");
        double mileage = getDoubleInput("Mileage: ");
        double basePrice = getDoubleInput("Base Price per Day: $");
        int doors = getIntInput("Number of Doors: ");
        String fuelType = getStringInput("Fuel Type (Gasoline/Diesel/Electric): ");
        String transmission = getStringInput("Transmission (Manual/Automatic): ");

        System.out.print("Has Air Conditioning? (y/n): ");
        boolean hasAC = scanner.nextLine().toLowerCase().startsWith("y");

        Car car = new Car(vehicleId, brand, model, year, color, mileage, basePrice, doors, fuelType, transmission, hasAC);
        agency.addVehicle(car);

        System.out.println("\n✅ Car added successfully!");
        car.displayVehicleInfo();
    }

    private static void addNewMotorcycle() {
        System.out.println("\n--- MOTORCYCLE DETAILS ---");

        String vehicleId = "MOTO" + String.format("%03d", nextVehicleId++);
        System.out.println("Vehicle ID: " + vehicleId);

        String brand = getStringInput("Brand: ");
        String model = getStringInput("Model: ");
        int year = getIntInput("Year: ");
        String color = getStringInput("Color: ");
        double mileage = getDoubleInput("Mileage: ");
        double basePrice = getDoubleInput("Base Price per Day: $");
        int engineSize = getIntInput("Engine Size (CC): ");
        String motorcycleType = getStringInput("Type (Sport/Cruiser/Standard/Touring): ");

        System.out.print("Has Sidecar? (y/n): ");
        boolean hasSidecar = scanner.nextLine().toLowerCase().startsWith("y");

        System.out.print("Requires Special License? (y/n): ");
        boolean requiresSpecialLicense = scanner.nextLine().toLowerCase().startsWith("y");

        Motorcycle motorcycle = new Motorcycle(vehicleId, brand, model, year, color, mileage, basePrice, engineSize, motorcycleType, hasSidecar, requiresSpecialLicense);
        agency.addVehicle(motorcycle);

        System.out.println("\n✅ Motorcycle added successfully!");
        motorcycle.displayVehicleInfo();
    }

    private static void viewAvailableVehicles() {
        agency.displayAvailableVehicles();
    }

    private static void createRental() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         CREATE RENTAL");
        System.out.println("=".repeat(40));

        // First, show available vehicles
        List<Vehicle> availableVehicles = agency.getAvailableVehicles();
        if (availableVehicles.isEmpty()) {
            System.out.println("❌ No vehicles available for rental!");
            return;
        }

        System.out.println("Available Vehicles:");
        for (int i = 0; i < availableVehicles.size(); i++) {
            Vehicle vehicle = availableVehicles.get(i);
            System.out.println((i + 1) + ". " + vehicle.getVehicleId() + " - " + vehicle.getVehicleSummary());
        }

        String customerId = getStringInput("\nCustomer ID: ");
        Customer customer = agency.findCustomer(customerId);
        if (customer == null) {
            System.out.println("❌ Customer not found!");
            return;
        }

        String vehicleId = getStringInput("Vehicle ID: ");
        Vehicle vehicle = agency.findVehicle(vehicleId);
        if (vehicle == null) {
            System.out.println("❌ Vehicle not found!");
            return;
        }

        LocalDate startDate = getDateInput("Start Date (YYYY-MM-DD): ");
        LocalDate endDate = getDateInput("End Date (YYYY-MM-DD): ");

        if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
            System.out.println("❌ End date must be after start date!");
            return;
        }

        Rental rental = agency.createRental(customerId, vehicleId, startDate, endDate);
        if (rental != null) {
            System.out.println("\n✅ Rental created successfully!");
            rental.displayRentalInfo();
        }
    }

    private static void returnVehicle() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         RETURN VEHICLE");
        System.out.println("=".repeat(40));

        String rentalId = getStringInput("Rental ID: ");
        agency.returnVehicle(rentalId);
    }

    private static void viewAllCustomers() {
        agency.displayCustomers();
    }

    private static void viewActiveRentals() {
        agency.displayActiveRentals();
    }

    private static void viewRentalHistory() {
        agency.displayRentalHistory();
    }

    private static void viewAgencyStatistics() {
        agency.displayAgencyStatistics();
    }

    // Helper methods for input validation
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.print("Please enter a valid value: ");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number!");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("❌ Please enter a positive number!");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number!");
            }
        }
    }

    private static LocalDate getDateInput(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            try {
                System.out.print(prompt);
                String dateString = scanner.nextLine().trim();
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Please enter date in YYYY-MM-DD format!");
            }
        }
    }
}


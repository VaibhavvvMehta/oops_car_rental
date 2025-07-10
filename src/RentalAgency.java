import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// RentalAgency class demonstrating Composition and system management
public class RentalAgency {
    // Private fields (Encapsulation)
    private String agencyName;
    private String address;
    private String phoneNumber;
    private List<Vehicle> fleet;        // Composition - Agency has Vehicles
    private List<Customer> customers;   // Composition - Agency has Customers
    private List<Rental> rentals;       // Composition - Agency has Rentals
    private int nextRentalId;

    // Constructor
    public RentalAgency(String agencyName, String address, String phoneNumber) {
        this.agencyName = agencyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.fleet = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
        this.nextRentalId = 1001;
    }

    // Getter methods
    public String getAgencyName() { return agencyName; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public List<Vehicle> getFleet() { return fleet; }
    public List<Customer> getCustomers() { return customers; }
    public List<Rental> getRentals() { return rentals; }

    // Method to add vehicle to fleet
    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
        System.out.println(vehicle.getVehicleSummary() + " added to fleet.");
    }

    // Method to remove vehicle from fleet
    public void removeVehicle(String vehicleId) {
        fleet.removeIf(vehicle -> vehicle.getVehicleId().equals(vehicleId));
        System.out.println("Vehicle " + vehicleId + " removed from fleet.");
    }

    // Method to register customer
    public void registerCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer " + customer.getFullName() + " registered successfully.");
    }

    // Method to find vehicle by ID
    public Vehicle findVehicle(String vehicleId) {
        for (Vehicle vehicle : fleet) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }

    // Method to find customer by ID
    public Customer findCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    // Method to get available vehicles
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : fleet) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    // Method to get available vehicles by type
    public List<Vehicle> getAvailableVehiclesByType(String vehicleType) {
        List<Vehicle> availableByType = new ArrayList<>();
        for (Vehicle vehicle : getAvailableVehicles()) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                availableByType.add(vehicle);
            }
        }
        return availableByType;
    }

    // Method to create rental (Polymorphism in action)
    public Rental createRental(String customerId, String vehicleId,
                               LocalDate startDate, LocalDate endDate) {
        Customer customer = findCustomer(customerId);
        Vehicle vehicle = findVehicle(vehicleId);

        if (customer == null) {
            System.out.println("Customer not found!");
            return null;
        }

        if (vehicle == null) {
            System.out.println("Vehicle not found!");
            return null;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is not available!");
            return null;
        }

        // Calculate rental cost
        int days = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) days = 1;
        double rentalCost = vehicle.calculateRentalPrice(days);

        // Check if customer can rent the vehicle
        if (!customer.canRentVehicle(vehicle, rentalCost)) {
            return null;
        }

        // Create rental
        String rentalId = "R" + nextRentalId++;
        Rental rental = new Rental(rentalId, customer, vehicle, startDate, endDate);

        // Rent the vehicle
        vehicle.rentVehicle();
        rentals.add(rental);

        System.out.println("Rental created successfully!");
        System.out.println("Rental ID: " + rentalId);
        System.out.println("Total Cost: $" + String.format("%.2f", rentalCost));

        return rental;
    }

    // Method to return vehicle
    public void returnVehicle(String rentalId) {
        Rental rental = findRental(rentalId);
        if (rental != null && !rental.isCompleted()) {
            rental.completeRental();
        } else {
            System.out.println("Rental not found or already completed!");
        }
    }

    // Method to find rental by ID
    public Rental findRental(String rentalId) {
        for (Rental rental : rentals) {
            if (rental.getRentalId().equals(rentalId)) {
                return rental;
            }
        }
        return null;
    }

    // Method to display all vehicles (Polymorphism)
    public void displayFleet() {
        System.out.println("\n=== " + agencyName + " FLEET ===");
        System.out.println("Total Vehicles: " + fleet.size());
        System.out.println();

        for (Vehicle vehicle : fleet) {
            vehicle.displayVehicleInfo(); // Polymorphic method call
            System.out.println();
        }
    }

    // Method to display available vehicles
    public void displayAvailableVehicles() {
        List<Vehicle> availableVehicles = getAvailableVehicles();
        System.out.println("\n=== AVAILABLE VEHICLES ===");
        System.out.println("Available: " + availableVehicles.size() + " vehicles");
        System.out.println();

        for (Vehicle vehicle : availableVehicles) {
            vehicle.displayVehicleInfo();
            System.out.println();
        }
    }

    // Method to display all customers
    public void displayCustomers() {
        System.out.println("\n=== REGISTERED CUSTOMERS ===");
        System.out.println("Total Customers: " + customers.size());
        System.out.println();

        for (Customer customer : customers) {
            customer.displayCustomerInfo();
            System.out.println();
        }
    }

    // Method to display active rentals
    public void displayActiveRentals() {
        System.out.println("\n=== ACTIVE RENTALS ===");
        int activeCount = 0;

        for (Rental rental : rentals) {
            if (!rental.isCompleted()) {
                rental.displayRentalInfo();
                activeCount++;
                System.out.println();
            }
        }

        System.out.println("Total Active Rentals: " + activeCount);
    }

    // Method to display rental history
    public void displayRentalHistory() {
        System.out.println("\n=== RENTAL HISTORY ===");
        System.out.println("Total Rentals: " + rentals.size());
        System.out.println();

        for (Rental rental : rentals) {
            System.out.println(rental.getRentalSummary());
        }
    }

    // Method to get agency statistics
    public void displayAgencyStatistics() {
        int totalVehicles = fleet.size();
        int availableVehicles = getAvailableVehicles().size();
        int totalCustomers = customers.size();
        int totalRentals = rentals.size();
        int activeRentals = 0;
        double totalRevenue = 0;

        for (Rental rental : rentals) {
            if (!rental.isCompleted()) {
                activeRentals++;
            } else {
                totalRevenue += rental.getTotalCost();
            }
        }

        System.out.println("\n=== AGENCY STATISTICS ===");
        System.out.println("Agency: " + agencyName);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phoneNumber);
        System.out.println();
        System.out.println("Fleet Statistics:");
        System.out.println("- Total Vehicles: " + totalVehicles);
        System.out.println("- Available Vehicles: " + availableVehicles);
        System.out.println("- Rented Vehicles: " + (totalVehicles - availableVehicles));
        System.out.println();
        System.out.println("Business Statistics:");
        System.out.println("- Total Customers: " + totalCustomers);
        System.out.println("- Total Rentals: " + totalRentals);
        System.out.println("- Active Rentals: " + activeRentals);
        System.out.println("- Total Revenue: $" + String.format("%.2f", totalRevenue));
        System.out.println("========================");
    }
}

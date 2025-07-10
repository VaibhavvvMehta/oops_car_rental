// Car class demonstrating Inheritance
public class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;
    private boolean hasAirConditioning;

    // Constructor
    public Car(String vehicleId, String brand, String model, int year, String color,
               double mileage, double basePricePerDay, int numberOfDoors,
               String fuelType, String transmissionType, boolean hasAirConditioning) {
        super(vehicleId, brand, model, year, color, mileage, basePricePerDay);
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.hasAirConditioning = hasAirConditioning;
    }

    // Getter methods
    public int getNumberOfDoors() { return numberOfDoors; }
    public String getFuelType() { return fuelType; }
    public String getTransmissionType() { return transmissionType; }
    public boolean hasAirConditioning() { return hasAirConditioning; }

    // Implementation of abstract methods (Polymorphism)
    @Override
    public void displayVehicleInfo() {
        System.out.println("=== CAR INFORMATION ===");
        System.out.println("Vehicle ID: " + getVehicleId());
        System.out.println("Brand: " + getBrand());
        System.out.println("Model: " + getModel());
        System.out.println("Year: " + getYear());
        System.out.println("Color: " + getColor());
        System.out.println("Mileage: " + getMileage() + " miles");
        System.out.println("Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Transmission: " + transmissionType);
        System.out.println("Air Conditioning: " + (hasAirConditioning ? "Yes" : "No"));
        System.out.println("Base Price/Day: $" + String.format("%.2f", getBasePricePerDay()));
        System.out.println("Available: " + (isAvailable() ? "Yes" : "No"));
        System.out.println("=====================");
    }

    @Override
    public double calculateRentalPrice(int days) {
        double price = getBasePricePerDay() * days;

        // Add premium for luxury features
        if (hasAirConditioning) {
            price += 5.0 * days; // $5 extra per day for AC
        }

        if (transmissionType.equalsIgnoreCase("Automatic")) {
            price += 3.0 * days; // $3 extra per day for automatic
        }

        // Discount for longer rentals
        if (days >= 7) {
            price *= 0.9; // 10% discount for weekly rentals
        }

        return price;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    // Car-specific method
    public void checkFuelLevel() {
        System.out.println("Checking fuel level for " + getBrand() + " " + getModel());
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Please ensure the tank is full before returning.");
    }

    // Car-specific method
    public void performMaintenance() {
        System.out.println("Performing maintenance on " + getVehicleSummary());
        System.out.println("- Oil change");
        System.out.println("- Tire pressure check");
        System.out.println("- Brake inspection");
        if (hasAirConditioning) {
            System.out.println("- AC system check");
        }
    }
}

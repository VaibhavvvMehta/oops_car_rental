// Motorcycle class demonstrating Inheritance
public class Motorcycle extends Vehicle {
    private int engineSize; // in CC
    private String motorcycleType;
    private boolean hasSidecar;
    private boolean requiresSpecialLicense;

    // Constructor
    public Motorcycle(String vehicleId, String brand, String model, int year, String color,
                      double mileage, double basePricePerDay, int engineSize,
                      String motorcycleType, boolean hasSidecar, boolean requiresSpecialLicense) {
        super(vehicleId, brand, model, year, color, mileage, basePricePerDay);
        this.engineSize = engineSize;
        this.motorcycleType = motorcycleType;
        this.hasSidecar = hasSidecar;
        this.requiresSpecialLicense = requiresSpecialLicense;
    }

    // Getter methods
    public int getEngineSize() { return engineSize; }
    public String getMotorcycleType() { return motorcycleType; }
    public boolean hasSidecar() { return hasSidecar; }
    public boolean requiresSpecialLicense() { return requiresSpecialLicense; }

    // Implementation of abstract methods (Polymorphism)
    @Override
    public void displayVehicleInfo() {
        System.out.println("=== MOTORCYCLE INFORMATION ===");
        System.out.println("Vehicle ID: " + getVehicleId());
        System.out.println("Brand: " + getBrand());
        System.out.println("Model: " + getModel());
        System.out.println("Year: " + getYear());
        System.out.println("Color: " + getColor());
        System.out.println("Mileage: " + getMileage() + " miles");
        System.out.println("Engine Size: " + engineSize + " CC");
        System.out.println("Type: " + motorcycleType);
        System.out.println("Sidecar: " + (hasSidecar ? "Yes" : "No"));
        System.out.println("Special License Required: " + (requiresSpecialLicense ? "Yes" : "No"));
        System.out.println("Base Price/Day: $" + String.format("%.2f", getBasePricePerDay()));
        System.out.println("Available: " + (isAvailable() ? "Yes" : "No"));
        System.out.println("==============================");
    }

    @Override
    public double calculateRentalPrice(int days) {
        double price = getBasePricePerDay() * days;

        // Add premium for larger engines
        if (engineSize > 600) {
            price += 10.0 * days; // $10 extra per day for powerful bikes
        }

        // Add premium for sport bikes
        if (motorcycleType.equalsIgnoreCase("Sport")) {
            price += 15.0 * days; // $15 extra per day for sport bikes
        }

        // Add premium for sidecar
        if (hasSidecar) {
            price += 8.0 * days; // $8 extra per day for sidecar
        }

        // Discount for longer rentals
        if (days >= 5) {
            price *= 0.85; // 15% discount for 5+ day rentals
        }

        return price;
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    // Motorcycle-specific method
    public void checkSafetyGear() {
        System.out.println("Safety gear check for " + getBrand() + " " + getModel() + ":");
        System.out.println("- Helmet (Required)");
        System.out.println("- Protective jacket");
        System.out.println("- Gloves");
        System.out.println("- Protective pants");
        System.out.println("- Boots");

        if (requiresSpecialLicense) {
            System.out.println("⚠️  Special motorcycle license required!");
        }
    }

    // Motorcycle-specific method
    public void performPreRideInspection() {
        System.out.println("Pre-ride inspection for " + getVehicleSummary() + ":");
        System.out.println("- Tire pressure and tread");
        System.out.println("- Brake fluid and function");
        System.out.println("- Chain tension and lubrication");
        System.out.println("- Lights and signals");
        System.out.println("- Engine oil level");

        if (hasSidecar) {
            System.out.println("- Sidecar attachment and balance");
        }
    }

    // Method to get power category
    public String getPowerCategory() {
        if (engineSize <= 125) {
            return "Beginner";
        } else if (engineSize <= 400) {
            return "Intermediate";
        } else if (engineSize <= 600) {
            return "Advanced";
        } else {
            return "Expert";
        }
    }
}

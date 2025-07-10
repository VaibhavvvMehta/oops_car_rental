// Abstract Vehicle class demonstrating Abstraction
public abstract class Vehicle {
    // Private fields demonstrating Encapsulation
    private String vehicleId;
    private String brand;
    private String model;
    private int year;
    private String color;
    private double mileage;
    private boolean isAvailable;
    private double basePricePerDay;

    // Constructor
    public Vehicle(String vehicleId, String brand, String model, int year,
                   String color, double mileage, double basePricePerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    // Getter methods (Encapsulation)
    public String getVehicleId() { return vehicleId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getColor() { return color; }
    public double getMileage() { return mileage; }
    public boolean isAvailable() { return isAvailable; }
    public double getBasePricePerDay() { return basePricePerDay; }

    // Setter methods (Encapsulation)
    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setMileage(double mileage) { this.mileage = mileage; }
    public void setBasePricePerDay(double basePricePerDay) { this.basePricePerDay = basePricePerDay; }

    // Abstract methods - must be implemented by subclasses
    public abstract void displayVehicleInfo();
    public abstract double calculateRentalPrice(int days);
    public abstract String getVehicleType();

    // Common method for renting
    public void rentVehicle() {
        if (isAvailable) {
            setAvailable(false);
            System.out.println(brand + " " + model + " has been rented.");
        } else {
            System.out.println(brand + " " + model + " is not available for rental.");
        }
    }

    // Common method for returning
    public void returnVehicle() {
        setAvailable(true);
        System.out.println(brand + " " + model + " has been returned.");
    }

    // Common method to get vehicle summary
    public String getVehicleSummary() {
        return year + " " + brand + " " + model + " (" + color + ")";
    }
}

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// Rental class demonstrating Composition and Association
public class Rental {
    // Private fields (Encapsulation)
    private String rentalId;
    private Customer customer; // Association - Rental is associated with Customer
    private Vehicle vehicle;   // Association - Rental is associated with Vehicle
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualReturnDate;
    private double totalCost;
    private boolean isCompleted;
    private String status; // "Active", "Completed", "Overdue"

    // Constructor
    public Rental(String rentalId, Customer customer, Vehicle vehicle,
                  LocalDate startDate, LocalDate endDate) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.actualReturnDate = null;
        this.isCompleted = false;
        this.status = "Active";

        // Calculate total cost
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) days = 1; // Minimum 1 day rental
        this.totalCost = vehicle.calculateRentalPrice(days);
    }

    // Getter methods (Encapsulation)
    public String getRentalId() { return rentalId; }
    public Customer getCustomer() { return customer; }
    public Vehicle getVehicle() { return vehicle; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public LocalDate getActualReturnDate() { return actualReturnDate; }
    public double getTotalCost() { return totalCost; }
    public boolean isCompleted() { return isCompleted; }
    public String getStatus() { return status; }

    // Method to complete the rental
    public void completeRental() {
        if (!isCompleted) {
            actualReturnDate = LocalDate.now();
            isCompleted = true;
            status = "Completed";
            vehicle.returnVehicle();

            // Calculate late fees if applicable
            if (actualReturnDate.isAfter(endDate)) {
                int lateDays = (int) ChronoUnit.DAYS.between(endDate, actualReturnDate);
                double lateFee = lateDays * 25.0; // $25 per day late fee
                totalCost += lateFee;
                customer.addCharge(lateFee);
                System.out.println("Late fee of $" + String.format("%.2f", lateFee) +
                        " applied for " + lateDays + " day(s) overdue.");
            }

            // Charge customer
            customer.addCharge(totalCost);
            System.out.println("Rental completed successfully!");
        } else {
            System.out.println("Rental is already completed.");
        }
    }

    // Method to check if rental is overdue
    public boolean isOverdue() {
        if (!isCompleted && LocalDate.now().isAfter(endDate)) {
            status = "Overdue";
            return true;
        }
        return false;
    }

    // Method to extend rental
    public void extendRental(LocalDate newEndDate) {
        if (!isCompleted && newEndDate.isAfter(endDate)) {
            int additionalDays = (int) ChronoUnit.DAYS.between(endDate, newEndDate);
            double additionalCost = vehicle.calculateRentalPrice(additionalDays);

            endDate = newEndDate;
            totalCost += additionalCost;

            System.out.println("Rental extended by " + additionalDays + " day(s).");
            System.out.println("Additional cost: $" + String.format("%.2f", additionalCost));
        } else {
            System.out.println("Cannot extend rental. Either rental is completed or new date is invalid.");
        }
    }

    // Method to get rental duration
    public int getRentalDuration() {
        LocalDate endDateToUse = actualReturnDate != null ? actualReturnDate : endDate;
        return (int) ChronoUnit.DAYS.between(startDate, endDateToUse);
    }

    // Method to display rental information
    public void displayRentalInfo() {
        System.out.println("=== RENTAL INFORMATION ===");
        System.out.println("Rental ID: " + rentalId);
        System.out.println("Customer: " + customer.getFullName());
        System.out.println("Vehicle: " + vehicle.getVehicleSummary());
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);

        if (actualReturnDate != null) {
            System.out.println("Actual Return Date: " + actualReturnDate);
        }

        System.out.println("Total Cost: $" + String.format("%.2f", totalCost));
        System.out.println("Status: " + status);
        System.out.println("Duration: " + getRentalDuration() + " day(s)");

        if (isOverdue()) {
            int overdueDays = (int) ChronoUnit.DAYS.between(endDate, LocalDate.now());
            System.out.println("⚠️  OVERDUE by " + overdueDays + " day(s)!");
        }

        System.out.println("=========================");
    }

    // Method to generate rental summary
    public String getRentalSummary() {
        return "Rental " + rentalId + ": " + customer.getFullName() +
                " - " + vehicle.getVehicleSummary() + " (" + status + ")";
    }

    // Method to calculate daily rate
    public double getDailyRate() {
        int duration = (int) ChronoUnit.DAYS.between(startDate, endDate);
        if (duration <= 0) duration = 1;
        return totalCost / duration;
    }
}

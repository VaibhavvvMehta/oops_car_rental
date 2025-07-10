// Customer class demonstrating Encapsulation
public class Customer {
    // Private fields (Encapsulation)
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String licenseNumber;
    private int age;
    private boolean hasMotorcycleLicense;
    private double creditLimit;
    private double currentBalance;

    // Constructor
    public Customer(String customerId, String firstName, String lastName, String email,
                    String phoneNumber, String licenseNumber, int age,
                    boolean hasMotorcycleLicense, double creditLimit) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
        this.age = age;
        this.hasMotorcycleLicense = hasMotorcycleLicense;
        this.creditLimit = creditLimit;
        this.currentBalance = 0.0;
    }

    // Getter methods (Encapsulation)
    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getLicenseNumber() { return licenseNumber; }
    public int getAge() { return age; }
    public boolean hasMotorcycleLicense() { return hasMotorcycleLicense; }
    public double getCreditLimit() { return creditLimit; }
    public double getCurrentBalance() { return currentBalance; }

    // Setter methods (Encapsulation with validation)
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Invalid email format!");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() >= 10) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Invalid phone number format!");
        }
    }

    public void setCreditLimit(double creditLimit) {
        if (creditLimit >= 0) {
            this.creditLimit = creditLimit;
        } else {
            System.out.println("Credit limit cannot be negative!");
        }
    }

    public void setHasMotorcycleLicense(boolean hasMotorcycleLicense) {
        this.hasMotorcycleLicense = hasMotorcycleLicense;
    }

    // Method to get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Method to check if customer can rent a vehicle
    public boolean canRentVehicle(Vehicle vehicle, double rentalCost) {
        // Age check
        if (age < 18) {
            System.out.println("Customer must be at least 18 years old to rent a vehicle.");
            return false;
        }

        // Motorcycle license check
        if (vehicle instanceof Motorcycle) {
            Motorcycle motorcycle = (Motorcycle) vehicle;
            if (motorcycle.requiresSpecialLicense() && !hasMotorcycleLicense) {
                System.out.println("Customer needs a motorcycle license to rent this vehicle.");
                return false;
            }
        }

        // Credit check
        if (currentBalance + rentalCost > creditLimit) {
            System.out.println("Rental cost exceeds customer's credit limit.");
            return false;
        }

        return true;
    }

    // Method to add charges to customer's balance
    public void addCharge(double amount) {
        if (amount > 0) {
            currentBalance += amount;
            System.out.println("$" + String.format("%.2f", amount) + " charged to " + getFullName());
        }
    }

    // Method to make payment
    public void makePayment(double amount) {
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            System.out.println("Payment of $" + String.format("%.2f", amount) + " received from " + getFullName());
        } else {
            System.out.println("Invalid payment amount!");
        }
    }

    // Method to display customer information
    public void displayCustomerInfo() {
        System.out.println("=== CUSTOMER INFORMATION ===");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + getFullName());
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("License Number: " + licenseNumber);
        System.out.println("Age: " + age);
        System.out.println("Motorcycle License: " + (hasMotorcycleLicense ? "Yes" : "No"));
        System.out.println("Credit Limit: $" + String.format("%.2f", creditLimit));
        System.out.println("Current Balance: $" + String.format("%.2f", currentBalance));
        System.out.println("Available Credit: $" + String.format("%.2f", (creditLimit - currentBalance)));
        System.out.println("============================");
    }

    // Method to check customer's rental eligibility
    public String getRentalEligibilityStatus() {
        if (age < 18) {
            return "Ineligible - Under 18";
        } else if (age < 21) {
            return "Restricted - Young Driver";
        } else if (age < 25) {
            return "Standard - Additional fees may apply";
        } else {
            return "Eligible - Standard rates";
        }
    }
}

# 🚗 Car Rental System - Java OOP Project

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![OOP](https://img.shields.io/badge/OOP-Concepts-blue?style=for-the-badge)]()
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)

## 📋 Overview
A comprehensive Car Rental Management System built in Java that demonstrates all four fundamental Object-Oriented Programming (OOP) concepts. This interactive console application manages vehicles, customers, and rentals with a complete business workflow including user registration, vehicle booking, and rental cost calculations.

## ✨ Features

### 👥 Customer Features
- ✅ **Register as New Customer** - Interactive registration with validation
- 🚗 **View Available Cars** - Browse all available vehicles with details
- 📝 **Book a Car** - Complete rental booking process
- 💰 **Calculate Rental Cost** - Estimate rental prices

### 🛠️ Admin Features
- ➕ **Add New Vehicles** - Add cars and motorcycles to the fleet
- 📊 **Fleet Management** - Manage vehicle inventory

## 🎯 OOP Concepts Demonstrated

### 1. **Abstraction**
- **Vehicle.java**: Abstract base class that defines common structure for all vehicles
- Abstract methods: `displayVehicleInfo()`, `calculateRentalPrice()`, `getVehicleType()`
- Provides a common interface while hiding implementation details

### 2. **Encapsulation**
- All classes use private fields with public getter/setter methods
- Data validation in setter methods (e.g., email format, phone number validation)
- Protected access to internal state of objects
- Examples: Customer credit limit validation, vehicle availability status

### 3. **Inheritance**
- **Car.java**: Extends Vehicle class, adds car-specific properties (doors, fuel type, transmission)
- **Motorcycle.java**: Extends Vehicle class, adds motorcycle-specific properties (engine size, type)
- Both inherit common functionality from Vehicle while adding specialized behavior

### 4. **Polymorphism**
- Runtime polymorphism: Different implementations of abstract methods in Car and Motorcycle
- Method overriding: `displayVehicleInfo()` and `calculateRentalPrice()` behave differently for cars vs motorcycles
- Interface polymorphism: Vehicle references can point to Car or Motorcycle objects

## Project Structure
```
CarRentalSystem/
├── Vehicle.java              # Abstract base class
├── Car.java                  # Car implementation
├── Motorcycle.java           # Motorcycle implementation
├── Customer.java             # Customer management
├── Rental.java               # Rental transaction handling
├── RentalAgency.java         # Main system management
├── CarRentalSystem.java      # Main demonstration class
└── README.md                 # This file
```

## Key Features

### Vehicle Management
- Add/remove vehicles from fleet
- Track vehicle availability
- Different pricing models for cars vs motorcycles
- Vehicle-specific features (AC for cars, engine size for motorcycles)

### Customer Management
- Customer registration with validation
- Age and license verification
- Credit limit management
- Motorcycle license requirements

### Rental Operations
- Create rentals with date validation
- Calculate dynamic pricing based on vehicle type and rental duration
- Handle rental returns with late fee calculations
- Track rental history and status

### Business Logic
- **Car Pricing**: Base rate + AC premium + automatic transmission premium + weekly discounts
- **Motorcycle Pricing**: Base rate + engine size premium + sport bike premium + sidecar premium + multi-day discounts
- **Late Fees**: $25 per day for overdue returns
- **Age Restrictions**: Minimum 18 years old, special rates for young drivers

## Class Relationships

### Inheritance Hierarchy
```
Vehicle (Abstract)
├── Car
└── Motorcycle
```

## Sample Business Workflow
1. **Setup**: Create rental agency and add vehicles to fleet
2. **Registration**: Register customers with validation
3. **Rental Creation**: Customer selects vehicle, system validates eligibility
4. **Pricing**: Dynamic pricing based on vehicle type and rental duration
5. **Rental Management**: Track active rentals and handle returns
6. **Financial Management**: Charge customers and handle late fees

## How to Run
1. Navigate to the project directory:
   ```
   cd CarRentalSystem
   ```

2. Compile all Java files:
   ```
   javac *.java
   ```

3. Run the main program:
   ```
   java CarRentalSystem
   ```

## Sample Output Features
- Vehicle fleet display with polymorphic method calls
- Customer information with validation
- Active rental tracking
- Rental history and statistics
- Agency performance metrics

## Advanced Features Demonstrated
- **Date/Time handling**: Using LocalDate for rental periods
- **Collections**: ArrayList for managing multiple objects
- **Input validation**: Email, phone number, credit limit validation
- **Business rules**: Age restrictions, license requirements, pricing algorithms
- **Error handling**: Validation and user feedback
- **Statistics**: Revenue tracking and fleet utilization

## Learning Objectives
This project demonstrates:
- How OOP principles work together in a real-world application
- Proper encapsulation with data validation
- Inheritance for code reuse and specialization
- Polymorphism for flexible behavior
- Composition for building complex systems
- Business logic implementation in OOP design

The Car Rental System serves as a comprehensive example of enterprise-level Java application design using OOP principles.


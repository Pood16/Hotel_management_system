# Hotel Management System

A comprehensive console-based hotel reservation management system built in Java, designed to handle hotel operations, user authentication, and reservation management with role-based access control.

## Overview

This application provides a complete hotel management solution with separate functionalities for administrators and regular users. The system allows users to register accounts, browse available hotels, make reservations, and manage their bookings through an intuitive console interface.

##  Features

### Authentication System
- **User Registration**: Create new user accounts with email validation
- **User Login**: Secure login system with credential verification
- **Profile Management**: Update email and password
- **Role-based Access**: Separate admin and user roles with different permissions
- **Session Management**: Secure logout functionality

### Hotel Management
- **View Hotels**: Browse all available hotels with details
- **Hotel Search**: Find specific hotels by ID
- **Hotel Information**: Display hotel name, address, available rooms, rating, and status

#### Admin-only Hotel Features
- **Create Hotels**: Add new hotels to the system
- **Update Hotels**: Modify hotel information (name, address, room count)
- **Delete Hotels**: Remove hotels from the system with confirmation

### Reservation Management

#### User Reservation Features
- **Make Reservations**: Book hotel rooms for specified number of nights
- **View My Reservations**: See personal reservation history
- **Update Reservations**: Modify number of nights for existing bookings
- **Cancel Reservations**: Cancel bookings with confirmation
- **Delete Reservations**: Remove reservation records

#### Admin Reservation Features
- **View All Reservations**: Access complete reservation database
- **System-wide Management**: Oversee all user reservations

## Architecture

The application follows a clean, layered architecture pattern:

### 📁 Project Structure
```
src/
├── Main.java                    # Application entry point
├── models/                      # Data models
│   ├── Client.java             # User/Admin model
│   ├── Hotel.java              # Hotel entity model
│   └── Reservation.java        # Reservation entity model
├── repositories/               # Data access layer
│   ├── ClientRepository.java
│   ├── HotelRepository.java
│   ├── ReservationRepository.java
│   └── memory/                 # In-memory implementations
│       ├── InMemoryClientRepository.java
│       ├── InMemoryHotelRepository.java
│       └── InMemoryReservationRepository.java
├── services/                   # Business logic layer
│   ├── AuthService.java
│   ├── HotelService.java
│   ├── ReservationService.java
│   └── implementation/         # Service implementations
│       ├── AuthServicesImplementation.java
│       ├── HotelServiceImplementation.java
│       └── ReservationServiceImplementation.java
├── ui/                         # User interface layer
│   └── ConsoleUI.java          # Console-based user interface
└── utils/                      # Utility classes
    ├── ConsoleInput.java       # Input handling and validation
    └── InputValidator.java     # Data validation utilities
```

### Design Patterns
- **Repository Pattern**: Abstracts data access with in-memory implementations
- **Service Layer Pattern**: Separates business logic from presentation
- **Dependency Injection**: Loose coupling between components
- **Interface Segregation**: Clear contracts between layers

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Console/Terminal access

### Installation & Running

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Pood16/Hotel_management_system.git
   cd Hotel_management_system
   ```

2. **Compile the application**:
   ```bash
   javac -d bin src/**/*.java
   ```

3. **Run the application**:
   ```bash
   java -cp bin Main
   ```

### Default Admin Account
The system automatically creates an admin account on startup:
- **Email**: `admin@admin.com`
- **Password**: `admin123`
- **Role**: Administrator

The admin account also creates three sample hotels:
- Youcode Nador (2 rooms, rating: 5.0)
- Youcode Youssoufia (10 rooms, rating: 5.0)
- Youcode Safi (10 rooms, rating: 5.0)

## Usage Guide

### User Flow
1. **Start Application**: Launch the console application
2. **Authentication**: Login with existing account or register new account
3. **Browse Hotels**: View available hotels and their details
4. **Make Reservations**: Select hotels and specify number of nights
5. **Manage Bookings**: View, update, or cancel existing reservations

### Admin Flow
1. **Admin Login**: Use admin credentials to access administrative features
2. **Hotel Management**: Create, update, or delete hotels
3. **Reservation Oversight**: View all system reservations
4. **User Management**: Monitor user activities and reservations

## Security Features

- **Password Validation**: Minimum 6 characters required
- **Email Validation**: Proper email format enforcement
- **Input Sanitization**: Comprehensive input validation
- **Role-based Access Control**: Admin-only features protected
- **Confirmation Prompts**: Destructive actions require explicit confirmation

## Data Storage

Currently uses **in-memory storage** for all data:
- Data persists only during application runtime
- All information is lost when application closes
- Suitable for demonstration and testing purposes


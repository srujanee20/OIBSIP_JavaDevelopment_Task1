# Train Reservation System

A full-stack, web-based Train Reservation System built with **Spring Boot**, **Spring Data JPA**, and **Mustache** templating. This application provides a comprehensive platform for users to register, log in, search for trains, book tickets, check their PNR status, and cancel bookings.

## 🚀 Features

- **User Authentication**: Secure user registration and login functionality.
- **Search Trains**: Look up available trains based on source and destination stations.
- **Book Tickets**: Users can seamlessly book train tickets and generate a unique PNR.
- **Dashboard**: A dedicated user dashboard to view all past and upcoming bookings.
- **PNR Status**: Instantly check the status and details of a ticket using its PNR.
- **Cancel Ticket**: Easy cancellation process for existing train bookings.

## 🛠️ Technology Stack

- **Backend**: Java 25, Spring Boot 4.1.0, Spring MVC, Spring Data JPA
- **Frontend**: HTML5, CSS3, Mustache Templating Engine (Custom responsive design with 'Inter' font)
- **Database**: H2 In-Memory Database (Disk persistent)
- **Build Tool**: Maven

## 📂 Project Structure

- `src/main/java/.../controller/`: Handles web requests (`AuthController`, `HomeController`, `TicketController`).
- `src/main/java/.../model/`: JPA Entities (`User`, `Train`, `Ticket`).
- `src/main/java/.../repository/`: Spring Data JPA interfaces for database operations.
- `src/main/java/.../service/`: Business logic layer.
- `src/main/resources/templates/`: Mustache views (`home.mustache`, `login.mustache`, `dashboard.mustache`, etc.).
- `src/main/resources/db/data/data.sql`: Seed file that populates initial train data automatically upon startup.

## ⚙️ Setup and Installation

### Prerequisites
- **Java 25** or higher installed on your system.
- **Maven** (to build and manage dependencies).

### Running the Application

1. **Navigate to the project directory**:
   ```bash
   cd train-reservation-system
   ```

2. **Build the project** using Maven:
   ```bash
   mvn clean install
   ```

3. **Run the Spring Boot application**:
   ```bash
   mvn spring-boot:run
   ```
   *(Alternatively, you can run the `TrainReservationSystemApplication` class directly from your IDE of choice).*

4. **Access the Application**:
   Open your web browser and navigate to:
   ```
   http://localhost:8080
   ```

## 🗄️ Database Information

The application utilizes an **H2 Database** configured to persist data locally (`./traindb`). You can access the H2 console to view the schema and data directly.

- **Console URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:file:./traindb`
- **Username**: `root`
- **Password**: `root123`

## 👥 How to Use

1. **Sign Up / Log In**: Create a new account or log in if you already have one.
2. **Search**: On the Home page, enter your source and destination to find matching trains.
3. **Book**: Select a train, enter the required passenger/class details, and complete the booking to get your PNR.
4. **Manage Bookings**: Head over to the Dashboard to view your tickets. You can search by PNR or browse your history, and cancel tickets if needed.

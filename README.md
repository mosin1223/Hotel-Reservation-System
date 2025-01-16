# Hotel Reservation System

A Java-based Hotel Reservation System with MySQL database integration. This system allows users to manage hotel bookings efficiently, including room reservations, viewing reservations, updating room details, and deleting reservations. The project demonstrates the use of JDBC for database connectivity and provides a simple, user-friendly interface for hotel administrators.

## Features

- **Reserve a Room**: Allows guests to reserve a room by providing their name, room number, and contact details.
- **View Reservations**: Displays all current reservations along with guest details, room numbers, and reservation dates.
- **Get Room Number**: Retrieve the room number based on reservation ID and guest name.
- **Update Reservation**: Update guest details, room number, and contact number for an existing reservation.
- **Delete Reservation**: Delete a reservation by providing its reservation ID.
- **Exit System**: Proper system shutdown with a countdown message.

## Technologies Used

- **Java**: The core programming language used to develop the system.
- **MySQL**: Relational database used for storing reservation data.
- **JDBC**: Java Database Connectivity for interaction with the MySQL database.

## Setup and Installation

### Prerequisites
- Install **Java** (JDK 8 or later).
- Install **MySQL** on your local machine or use a remote MySQL server.
- Download **JDBC MySQL Connector**: [JDBC MySQL Connector](https://dev.mysql.com/downloads/connector/j/)

### Steps to Run

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/Hotel-Reservation-System.git
    cd Hotel-Reservation-System
    ```

2. **Set up the MySQL Database**:
    - Create a MySQL database:
        ```sql
        CREATE DATABASE hotle_db;
        ```
    - Create a table for reservations:
        ```sql
        CREATE TABLE reservation (
            Rservation_Id INT AUTO_INCREMENT PRIMARY KEY,
            guest_name VARCHAR(255),
            Room_No INT,
            Contact_Num VARCHAR(15),
            Reservation_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
        ```

3. **Configure Database Connection**:
    - Open `HotlereservationSystem.java` and update the database connection details:
      ```java
      static final String url = "jdbc:mysql://localhost:3306/hotle_db";
      static final String name = "root";
      static final String password = "your_mysql_password";
      ```

4. **Compile and Run**:
    - Compile the Java code:
      ```bash
      javac HotlereservationSystem.java
      ```
    - Run the program:
      ```bash
      java HotlereservationSystem
      ```

## Usage

- Upon running the program, you will be presented with a menu of options to interact with the hotel reservation system.
- Follow the on-screen prompts to:
  - Reserve a room
  - View reservations
  - Get room numbers by reservation ID and guest name
  - Update reservations
  - Delete reservations
  - Exit the system

## Contributing

Feel free to fork the repository, submit issues, or make pull requests. Any improvements or bug fixes are welcome.

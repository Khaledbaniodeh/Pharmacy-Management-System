# Pharmacy Management System

A desktop-based pharmacy management application built with **JavaFX**, **MySQL**, and **JDBC**.

The current version is a functional prototype focused on managing medicine records through a graphical user interface. It demonstrates database connectivity, parameterized SQL queries, input validation, and automatic resource management.

## Overview

This project provides a simple and extensible foundation for a complete pharmacy management system.

The current prototype allows users to:

- Add new medicine records
- Search for medicines by name
- View all stored medicines
- Delete medicines by ID
- Display query results inside the JavaFX interface
- Store and retrieve data from a MySQL database
- Close database resources automatically using `try-with-resources`

The project is designed so that additional modules—such as inventory, suppliers, customers, warehouses, sales, purchases, and invoices—can be added later.

## Features

### Medicine Management

- Add medicine name, description, price, and expiration date
- Search using partial medicine names
- View all medicine records
- Delete a medicine using its unique ID
- Display success, validation, and error messages in the interface

### Database Integration

- MySQL database connection through JDBC
- Prepared statements for safer SQL execution
- Automatic closing of database connections, statements, and result sets
- Auto-incremented primary keys
- Persistent storage across application runs

### User Interface

- JavaFX desktop interface
- Clear input fields and action buttons
- Read-only output area for query results
- Exit button for closing the application

## Technologies

- Java 21
- JavaFX 23
- MySQL 8
- MySQL Connector/J
- JDBC
- Eclipse IDE

## Database Schema

The current prototype uses the following table:

```sql
CREATE DATABASE IF NOT EXISTS pharmacy_db;
USE pharmacy_db;

CREATE TABLE IF NOT EXISTS medicine (
    medicine_id INT PRIMARY KEY AUTO_INCREMENT,
    medicine_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10,2),
    expiration_date DATE
);
```

### Medicine Table

| Column | Type | Description |
|---|---|---|
| `medicine_id` | `INT` | Unique auto-generated medicine ID |
| `medicine_name` | `VARCHAR(100)` | Medicine name |
| `description` | `VARCHAR(255)` | Medicine description |
| `price` | `DECIMAL(10,2)` | Selling price |
| `expiration_date` | `DATE` | Expiration date |

## Project Structure

```text
Pharmacy-Management-System/
├── src/
│   └── PharmacyFX.java
├── database/
│   └── pharmacy_db.sql
├── lib/
│   └── mysql-connector-j.jar
├── screenshots/
├── README.md
└── .gitignore
```

> The exact folder structure may differ depending on the IDE configuration.

## Prerequisites

Install the following before running the project:

- Java Development Kit 21 or later
- JavaFX SDK 23
- MySQL Server 8
- MySQL Workbench
- MySQL Connector/J
- Eclipse IDE or another Java IDE

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/Pharmacy-Management-System.git
cd Pharmacy-Management-System
```

### 2. Create the database

Open MySQL Workbench and execute:

```sql
CREATE DATABASE IF NOT EXISTS pharmacy_db;
USE pharmacy_db;

CREATE TABLE IF NOT EXISTS medicine (
    medicine_id INT PRIMARY KEY AUTO_INCREMENT,
    medicine_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10,2),
    expiration_date DATE
);
```

### 3. Configure the database connection

Update the following values in `PharmacyFX.java`:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/pharmacy_db";

private static final String USER = "root";
private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";
```

> Never upload a real database password to a public repository. Use a local configuration file or environment variable before publishing the project.

### 4. Add MySQL Connector/J

In Eclipse:

```text
Right-click project
→ Build Path
→ Configure Build Path
→ Libraries
→ Classpath
→ Add External JARs
```

Select the MySQL Connector/J `.jar` file.

### 5. Add JavaFX libraries

Add all JavaFX `.jar` files from the JavaFX SDK `lib` folder to the project classpath.

Example path:

```text
C:\Users\YOUR_NAME\Desktop\JavaFx\javafx-sdk-23.0.1\lib
```

### 6. Configure JavaFX VM arguments

In Eclipse:

```text
Run
→ Run Configurations
→ Java Application
→ PharmacyFX
→ Arguments
```

Add:

```text
--module-path "C:\PATH\TO\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml
```

### 7. Run the application

Run:

```text
PharmacyFX.java
```

## Usage

### Add a Medicine

Enter:

```text
Medicine Name: Panadol
Description: Pain reliever
Price: 10.50
Expiration Date: 2027-08-08
```

Then click **Add Medicine**.

### Search for a Medicine

Enter part of a medicine name, such as:

```text
Pana
```

Then click **Search**.

### Delete a Medicine

Enter the medicine ID and click **Delete Medicine**.

### View All Medicines

Click **View All** to display all stored records.

## Important Notes

- Dates must use the `YYYY-MM-DD` format.
- Medicine IDs are generated automatically.
- Deleted IDs are not reused automatically. This is normal behavior for `AUTO_INCREMENT`.
- Database resources are closed automatically using Java's `try-with-resources`.
- The database and table are created in MySQL, while the JavaFX application executes queries against them.

## Security

The current prototype uses local database credentials for development.

Before publishing:

- Remove real database passwords from the source code
- Use environment variables or a local configuration file
- Add the configuration file to `.gitignore`
- Never commit production credentials

## Roadmap

Planned future improvements:

- Update medicine records
- TableView-based data display
- Input validation and confirmation dialogs
- Category and supplier management
- Warehouse and inventory tracking
- Low-stock and expiration alerts
- Customer and pharmacist management
- Sales and purchase transactions
- Invoice generation
- Advanced reports and charts
- Login and role-based access control
- Export reports to PDF or CSV

## Screenshots

Add screenshots to the `screenshots/` folder and reference them here:

```markdown
![Main Interface](screenshots/main-interface.png)
![Medicine Records](screenshots/medicine-records.png)
```

## Contributors

- Khaled Bani Oudeh

## License
No license has been specified yet.

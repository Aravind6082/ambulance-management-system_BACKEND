# Ambulance Management System — Backend

## About the Project
The Ambulance Management System Backend is developed using Java and Spring Boot. It provides REST APIs to manage users, patients, ambulance drivers, ambulances, and emergency requests. The backend connects to a MySQL database to store and retrieve application data and handles the assignment and completion of emergency requests.

## Technologies Used
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL
- Maven
- REST APIs

## Features
- User registration and login
- Patient profile management
- Driver profile management
- Ambulance information and availability tracking
- Emergency request creation and status tracking
- Ambulance and driver assignment
- Automatic availability updates after request completion
- MySQL database integration

## Project Structure
```text
backend/
├── src/
│   ├── main/
│   │   ├── java/com/ambulance_management/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## Prerequisites
- Java JDK 21
- MySQL Server 8.0 or compatible
- Git

## Database Setup
Create the database in MySQL:

```sql
CREATE DATABASE ambulance_management_db;
```

Configure your database connection in `src/main/resources/application.properties` using your local MySQL username and password. Do not commit real credentials to a public repository.

## How to Run
Open CMD or a terminal inside the backend folder.

Windows:

```cmd
mvnw.cmd spring-boot:run
```

The backend runs at:

`http://localhost:8080`

## Main API Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/auth/register` | Register a user |
| POST | `/api/auth/login` | Log in |
| GET | `/api/ambulances` | List ambulances |
| GET | `/api/ambulances/available` | List available ambulances |
| POST | `/api/patients` | Create a patient profile |
| GET | `/api/patients/{id}` | Retrieve a patient |
| GET | `/api/drivers` | List drivers |
| POST | `/api/drivers` | Create a driver profile |
| POST | `/api/emergency-requests` | Create an emergency request |
| GET | `/api/emergency-requests` | List emergency requests |
| PUT | `/api/emergency-requests/{requestId}/assign` | Assign ambulance and driver |
| PUT | `/api/emergency-requests/{requestId}/complete` | Complete an emergency request |

## Future Enhancements
- Role-based authorization and stronger authentication
- Real-time ambulance GPS tracking
- Maps and route optimization
- Emergency notifications
- Improved API validation and automated testing

## Author
**Aravind Reddy**

## Repository
[Ambulance Management System Backend](https://github.com/Aravind6082/ambulance-management-system_BACKEND)

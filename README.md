# Car Management & Fuel Tracking
## Project Architecture
The system is divided into two main components:
Java application with in-memory storage
1. `Backend Server`: Spring Boot application with in-memory storage.
2. `CLI Client`: A standalone Java application (separate module) using HTTP to communicate with the
   server.

## Technologies Used
- `Java 17` for both server and client
- `Spring Boot 4.0.1` for the backend server
- `Maven`  for dependency management and build automation

## Setup and Running the Application Instructions
### Prerequisites
- Ensure you have `Java 17` or higher version and `Maven` installed on your machine.
### Steps to Run the Application
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/ngirimana/codehills-academy-assessment.git
   cd codehills-academy-assessment
   ```
2. **Build the Project**:
   ```bash
   mvn clean install
   ```
3. **Run the Backend Server**:
   ```bash
   mvn spring-boot:run
   ```
and the server will start on `http://localhost:8080`.

 **Running API Endpoints examples**

| Action      | Method | Endpoint            | Request                                |
|------------|--------|---------------------|----------------------------------------|
| Create Car | POST   | /api/cars           |{"brand":"Toyota","model":"Corolla","year":2029|
| List Cars  | GET    | /api/cars           | N/A                                    |
| Add Fuel   | POST   | /api/cars/{id}/fuel |{"liters":40,"pricePerLiter":52.5,"odometer":45000}|
| Get Stats  | GET    | /api/cars/{id}/fuel/stats | N/A  |

   **Running servlet integration example**

   Go to browser and access `http://localhost:8080/servlet/fuel-stats?carId=1`

4. **Run the CLI Client**:
   Open a new terminal and navigate to the project directory, then run:
   ```bash
    mvn exec:java -Dexec.mainClass="com.codehills.car_management.cli.CliClient"
    ```
5. **Using the CLI**:
   Follow the on-screen prompts to add cars, record fuel entries, and view reports.

  **Commands example**
1. To add a car: 
```create-car --brand Toyota --model Corolla --year 2018 ```
2. To record a fuel entry:
```add-fuel --carId 1 --liters 40 --price 52.5 --odometer 45000```
3. To view fuel report:
```fuel-stats --carId 1```

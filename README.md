# Agricultural Dashboard

This project is a prototype developed as part of my university final project, aiming to provide an intuitive platform for monitoring and analyzing key metrics in the agricultural sector.  
Designed with small to medium-sized farms in mind, it helps businesses track crop performance, resource usage, and environmental conditions.  
The primary goal is to improve decision-making by presenting data in a clear and actionable format.  
Through real-time data simulation and visualization, the platform allows users to explore insights that can enhance productivity and sustainability.  
Though still in its early stages, it lays the groundwork for more interactive features and real-world applications.

<img src="docs/screenshots/img-demo1.png?raw=true" alt="Demo Screenshot 1" width="50%"><img src="docs/screenshots/img-demo2.png?raw=true" alt="Demo Screenshot 2" width="50%">

## Live Demo

You can try the live demo of the Agricultural Dashboard here:

[![Live Demo](https://img.shields.io/badge/Live%20Demo-🌐-darkgreen)](https://agricultural-dashboard-agoi.onrender.com/)

*(Note: If the demo is unavailable, please contact me for assistance.)*

## Features

- **Real-Time Data Visualization**: Displays real-time metrics such as crop yield, resource usage, and environmental
  conditions.
- **Data Simulation**: Includes a data simulator to generate random environmental and production data for analysis.

## Technologies Used

- **Java 17**
- **Spring Boot 3.3.0**
- **Thymeleaf**
- **Bootstrap**
- **Liquibase**
- **H2 Database**
- **Gradle**

## Installation and Setup

### Development Setup (H2 Database)

1. **Clone the repository:**

   ```sh
   git clone https://github.com/vittoriomigliore/agricultural-dashboard.git
   cd agricultural-dashboard
   ```

2. **Build the project:**

   ```sh
   ./gradlew build
   ```

3. **Run the application with the **`dev`** profile (H2 mode):**

   ```sh
   ./gradlew bootRun --args='--spring.profiles.active=dev'
   ```

   Alternatively, you can set the profile via an environment variable:

   ```sh
   export SPRING_PROFILES_ACTIVE=dev
   ./gradlew bootRun
   ```

4. The application should now be running on `http://localhost:8080`.\
   The in-memory H2 database can be accessed via its console at `http://localhost:8080/h2-console` using the configured credentials in `application-dev.properties`.

---

### Production Setup (PostgreSQL + Docker)

1. **Set up PostgreSQL database:**

   - Ensure a PostgreSQL instance is running.
   - Create a new database and note the connection details.

2. **Set the required environment variables:**

   ```sh
   export JDBC_DATABASE_URL="jdbc:postgresql://<DB_HOST>:<DB_PORT>/<DB_NAME>?user=<DB_USER>&password=<DB_PASSWORD>"
   ```

3. **Build and run using Docker:**

   ```sh
   docker build -t agricultural-dashboard .
   docker run -p 8080:8080 -e JDBC_DATABASE_URL="$JDBC_DATABASE_URL" agricultural-dashboard
   ```

4. The application should now be running on `http://localhost:8080`.

## Potential Applications
- **Small to Medium-Sized Farms**: Enables agricultural SMEs to digitize and monitor their operations with minimal investment.
- **Prototype and Research Tool**: Serves as a testbed for experimenting with agricultural innovations, such as new cultivation techniques or business models. 
- **Educational Platform**: Provides a learning environment for agricultural students or practitioners interested in data-driven farming.

## Future Improvements

- **Interactive Features**: Enable users to interact with the dashboard, filter data, and customize visualizations. 
- **Data Source Integration**: Incorporate external APIs for weather forecasts, soil moisture levels, and other agronomic data. 
- **User Authentication**: Add secure user authentication and role-based access to ensure data protection.

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for further improvements, please feel free to
open an issue or submit a pull request.

### How to contribute:

1. Fork the repository.
2. Create your feature branch:  
   `git checkout -b feature/your-feature`
3. Commit your changes:  
   `git commit -m 'Add some feature'`
4. Push to the branch:  
   `git push origin feature/your-feature`
5. Open a pull request.

## License

This project is licensed under the MIT License.
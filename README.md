# Agricultural Dashboard

## Description

This project focuses on developing an interactive dashboard for analyzing business performance in the agricultural
sector. The dashboard is designed to help agricultural businesses monitor key metrics, optimize resources, and enhance
sustainability by leveraging real-time data integration and visualization.

## Features

- **Real-Time Data Visualization**: Displays real-time metrics such as crop yield, resource usage, and environmental
  conditions.
- **Dynamic Interaction**: Users can interact with the dashboard to filter and analyze data based on specific criteria.
- **Data Simulation**: Includes a data simulator to generate random environmental and production data for analysis.
- **User-Friendly Interface**: Designed with principles of user experience in mind, making it accessible for
  non-technical users.

## Technologies Used

- **Java 17**
- **Spring Boot 3.3.0**
- **H2 Database** (for development)
- **Thymeleaf** (for templating)
- **Bootstrap** (for responsive UI)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/vittoriomigliore/agricultural-dashboard.git
   cd agricultural-dashboard
   ```
2. Configure the database connection in `application.properties`.
3. Create the required tables in MySQL using the provided SQL scripts.
4. Build the project using Maven:
   ```bash
   mvn clean install
   ```
5. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

### Accessing the Dashboard

Once the application is running, access the dashboard by navigating to:

```
http://localhost:8080
```

## Future Improvements

- Integrate external data sources for weather information.
- Implement user authentication and role management.
- Enhance data visualization with more interactive charts and graphs.

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
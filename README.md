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

1. Clone the repository:
   ```bash
   git clone https://github.com/vittoriomigliore/agricultural-dashboard.git
   cd agricultural-dashboard
   ```
2. Configure the database:  
   By default, the application uses H2 Database for development.  
   To use a different database, modify the connection settings in `application.properties` accordingly.
3. Set up the database schema:  
   The application uses Liquibase for database migration. On application startup, Liquibase will automatically apply the necessary schema changes.
4. Build the project:
   ```bash
   ./gradlew build
   ```
5. Run the application:
   ```bash
   ./gradlew bootRun
   ```
6. Access the dashboard:
   Open a web browser and navigate to:  
   ```
   http://localhost:8080
   ```

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
User Service
This is a Spring Boot microservice responsible for managing User Registration and Authentication.
It provides JWT-based security for other services.

📂 Project Structure
css
Copy
Edit
src/main/java/com/example/userservice
🚀 Technologies Used
Java 17+

Spring Boot 3+

Spring Security

JWT (JSON Web Token)

Spring Data JPA

MySQL / H2

Lombok

Maven

🛠️ How to Run
1️⃣ Configure Database (H2 or MySQL)
Set your application.properties / application.yml with DB details.

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/users_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
2️⃣ Run Application
bash
Copy
Edit
./mvnw spring-boot:run
🎯 Endpoints (Examples)
Method	Endpoint	Description
POST	/api/users/register	Register new user
POST	/api/users/login	Login & get JWT

🔑 JWT Secured Endpoints
You must include Authorization: Bearer <token> for secured routes in other services.

🔗 Integration
Other services (like Employee Service) will use this for user authentication.

Can expose /auth endpoints publicly, and secure the rest.

# Events Tickets Management Platform

A web application that helps organizers create and manage events while allowing attendees to browse events, purchase tickets, and receive confirmation.<a href="https://www.youtube.com/watch?v=SDJubuzoQ88">Demo Live Link</a>


## Technologies
- **Backend** : Java, Spring boot, Spring Data JPA, Spring Security, Swagger API, Maven.
- **Frontend** : Typescript, Angular Framework.
- **Database** : MySQL


## Quick Start

```bash
# Run the backend locally
cd EventTicketsApp
mvnw spring-boot:run

# Run the frontend locally
cd event-tickets-frontend
npm install
npm start

# Run the full application with Docker Compose From the project root :
docker compose up --build

# The Docker Compose stack contains:
    MySQL
    Spring Boot backend
    Angular frontend served by Nginx

```

### Service URLs

| Service | URL |
|---------|-----|
|Back-end App | http://localhost:8086 |
|front-end App | http://localhost:4200 |
|Swagger UI | http://localhost:8086/swagger-ui/index.html |

## Test Accounts

After running the seed script:

### Regular Users
- anas@gmail.com / 123456

### Admin User
- admin@events.com / password: 123456
- you can create another admin account you should first run command from root project 
  ```bash
      $docker exec -it eventtickets-mysql mysql -uroot -pmysql2002
      mysql> INSERT INTO user
            (id, created_at, email, name, password, phone, role)
            VALUES
            (
            UUID(),
            NOW(),
            'admin@eventtickets.com',
            'System Administrator',
            '123456',
            '0612345678',
            'ADMIN'
            );
  ```
## Features

### Authentication
    User registration
    User login
    JWT authentication
    Role-based authorization
    Admin and customer access control
    Logout
### Admin
    Dashboard
    User management
    List users by role
    Search users by name
    Delete users
    Event management
    List events
    Search events
    Create events
    Update events
    Delete events
    Filter events by category
    Category management
    List categories
    Create categories
    Delete categories
    Ticket management
    List tickets
    Search by customer or event
    Filter by status
    Filter by purchase date
### Customer / Attendee
    Browse events
    Search events by title
    View event details
    Purchase tickets
    View purchase history
    Logout




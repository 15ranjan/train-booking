# Train Booking System

A simple Train Booking System built with Java and Spring Boot, allowing users to book, modify, and manage train tickets efficiently.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [API Endpoints](#api-endpoints)


## Features

- Book a ticket by providing user details and seat information.
- Modify existing bookings to change allocated seats.
- Remove a user from the train if needed.
- View users and their allocated seats by requested section.

## Technologies Used

- Java
- Spring Boot
- Maven
- Git
- Postman (for API testing)

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/train-booking.git

## API Endpoints
  1. Book a Ticket
   Endpoint: POST /api/tickets/purchase

   {
    "from": "London",
    "to": "Paris",
    "user": {
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com"
    },
    "seat": "A1"
    }

  2. View Users by Section
     Endpoint: GET /api/tickets/seats?section=A

  3. Remove a User
     Endpoint: DELETE - http://localhost:8081/api/remove?email=john.doe@example.com

  4. Modify User Seat
     Endpoint : POST - http://localhost:8081/api/modify?email=john.doe@example.com&newSeat=A1

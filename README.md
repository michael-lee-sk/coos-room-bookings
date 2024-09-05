# COOS Room Bookings

COOS Room Bookings is a web application that allows users to view, book, and manage meeting rooms. It integrates Google OAuth2 for user authentication and Google Calendar for syncing booked events to the user's calendar.

## Features

- **Google OAuth2 Authentication**: Users can log in using their Google accounts.
- **Room Booking**: Users can view available rooms and book them for specific time slots.
- **Google Calendar Integration**: Booked rooms are automatically synced to the user's Google Calendar.
- **Real-Time Availability**: Room availability is updated in real-time, ensuring no double bookings.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Technologies

- **Backend**: Spring Boot, MySQL, Google OAuth2, Google Calendar API
- **Frontend**: React, Bootstrap (TBD)
- **Testing**: JUnit 5, Mockito, Spring Security Test
- **Build Tools**: Maven
- **Database**: MySQL

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- Java 17 or higher
- Maven 3.6.0 or higher
- MySQL
- A Google Cloud project with OAuth2 credentials and access to the Google Calendar API

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/coos-room-bookings.git
cd coos-room-bookings

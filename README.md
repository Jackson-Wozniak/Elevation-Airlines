<div align="center">
  <kbd> <img src="https://github.com/Jackson-Wozniak/Airline-Reservation-System/assets/105665813/abdc2e68-fbc7-4d98-8abc-88fb2b9db5a8" width="420" height="260"/> </kbd>
  

  <h3 align="center">Airline Tracker & Reservation System</h3>

  <a href="https://github.com/Jackson-Wozniak/Airline-Reservation-System/edit/main/backend"><strong>Explore The Code»</strong></a>
    </br>
    <p>
      <img src="https://img.shields.io/github/commit-activity/m/Jackson-Wozniak/Airline-Reservation-System" alt="commits" />
      <img src="https://img.shields.io/github/issues/Jackson-Wozniak/Airline-Reservation-System" alt="license" />
      <img src="https://img.shields.io/github/license/Jackson-Wozniak/Airline-Reservation-System" alt="license" />
    </p> 
    <a href="https://github.com/Jackson-Wozniak/Airline-Reservation-System/blob/main/backend/flight-tracking-service/flight-tracking-documentation.md">Flight Tracking API Docs</a>
    ·
    <a href="https://github.com/Jackson-Wozniak/Airline-Reservation-System/issues">Report Bug</a>
    ·
    <a href="https://github.com/Jackson-Wozniak/Airline-Reservation-System/issues">Request Feature</a>
</div>

## :books: Table of Contents

<ol>
    <li><a href="#features">Features</a></li>
    <li><a href="#tracking">Flight Tracking</a></li>
    <li><a href="#tickets">Ticket Reservations</a></li>
    <li><a href="#banking">Banking Management</a></li>
    <li><a href="#technologies">Technologies</a></li>
    <li><a href="#local-dev">Local Deployment</a></li>
</ol>    

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## 📓 Features & Overview <a id="features"></a>
An airline reservation & tracking system that generates flights to popular airports across the world. The airline used in this simulation is called Elevation Airlines, and is a fake airline. Route generation is largely random, and the flights each plane takes does not necessarily match the popular flights real airlines fly.

Airport data is found from <a href="https://ourairports.com/data"/>ourairports.com</a> and is filtered to only include those that have the large_airports tag

The image used as the preview for this repo can be found <a href="https://wallpaperaccess.com/full/254381.jpg" />here </a>

<br>

## ✈️ Flight Tracking <a id="tracking"></a>
Elevation airlines runs two categories of flights. Scheduled flights are those scheduled from the same airports each day, and run at the same time-of-day no matter what. These are round trip flights, meaning the plane will travel to and from each airport once a day. The second type of flight is random routes, which are scheduled once daily and all planes in the airlines fleet that do not have a scheduled route will fly to a random airport once a day. Departures are set based on the location of the plane at the time, meaning planes will follow a path and can only depart from their current location.

Full API docs can be found <a href="https://github.com/Jackson-Wozniak/Airline-Reservation-System/blob/main/backend/flight-tracking-service/flight-tracking-documentation.md" />here</a>

<br>

## 🎟️ Ticket Reservations <a id="tickets"></a>
After selecting a flight, users can attempt to buy tickets to the desired flight. There must be available seats on the flight in order to buy tickets, and users can't buy tickets if there is a scheduling conflict, meaning that they already own tickets for a flight scheduled to be in the air at the same time. Flights will be booked at a rate dependent on their demand, meaning higher demand routes will be sold out earlier

Full API docs will be available shortly

<br>

## 💵 Banking Management <a id="banking"></a>
In order to buy tickets, users must first create a bank account, and deposit money to their account. Users will have to ensure their bank accounts hold enough value to afford the tickets they are attempting to buy

*The money in this account does not have any real-life value, and can be deposited in the click of a button

Full API docs for the banking service will available soon

<br>

## 🔌 Demo <a id="demo"></a>
coming soon...

<br>

## 📱 Technologies Used <a id="technologies"></a>

#### Backend
- Java
- Spring Boot
- Maven
- MySQL

#### Frontend

#### General
- Git
- Docker

<br>

## ✏️ Local Development <a id="local-dev"></a>

To run locally, follow these commands

```
- git clone https://github.com/Jackson-Wozniak/Airline-Reservation-System.git
- cd (to the location of cloned repo)
- docker-compose up

to shut down the application, run:
- docker-compose down

to restart the app after making local changes (to rebuild the jar file), run:
-docker-compose up --build
```

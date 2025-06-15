<div align="center">
  <kbd> <img src="https://github.com/Jackson-Wozniak/Elevation-Airlines/assets/105665813/13133968-b09d-4036-b944-ac32a0c66a41" width="700" height="318"/> </kbd>
  

  <h3 align="center">Airline Tracker & Reservation System</h3>

  <a href="https://github.com/Jackson-Wozniak/Airline-Reservation-System/edit/main/backend"><strong>Explore The Code»</strong></a>
    </br>
    <p>
      <img alt="GitHub Repo stars" src="https://img.shields.io/github/stars/Jackson-Wozniak/Elevation-Airlines?style=plastic&color=green">
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
    <li><a href="#features">Overview & Current Undertakings</a></li>
    <li><a href="#tracking">Flight Tracking</a></li>
    <li><a href="#technologies">Technologies</a></li>
    <li><a href="#local-dev">Local Deployment</a></li>
    <li><a href="#credits">Credits</a></li>
</ol>    

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## 📓 Overview & Current Undertakings <a id="features"></a>
An airline tracking system that generates flights to popular airports across the world. The airline used in this simulation is called Elevation Airlines, and is a fake airline. Route generation is largely random, and the flights each plane takes does not necessarily match the popular flights real airlines fly.

I am currently in the process of transforming the way flight scheduling works. I have increased the number
    of airports in the database by including smaller regional airports, and also added passenger data
    for larger airports. Currently, the scheduling algorithm works by a point-to-point system, not
    accounting for airport economics or passenger data, meaning that it is entirely randomized.
    In transforming this algorithm, Elevation Airlines will include a hub-and-spoke style flight network,
    and incorporate location and passenger count into the frequency of certain routes. Likewise, certain
    planes (identified with callsigns) will fill certain popular routes, such as KJFK-KLAX. This frees
    up the remainder of the fleet to fly through the hubs, much like a legacy airline would in real life.

The details of the hubs is up for change, however it is likely that the system will generate multiply
    'hub' airports, flying a plane to a destination, then flying back to the hub etc. This maximizes the realism
    and economic feasibility of this scheduling system, and could be scaled up to include more comprehensive
    passenger data in the future. Although this remains a project built for fun, the idea would be
    for me to explore the philosophy of airline scheduling, to better understand the real-world process.

Current passenger data remains limited, with only ~1000 airports (out of nearly 6500) including a
    count of real-world visitors. This can be scaled up in the future to be more accurate, and
    hone in the scheduling algorithm to focus on economic factors. Furthermore, this focus on
    economics can also introduce features such as increasing fleet count, allowing me to
    simulate the purchase of new planes and therefore more routes as the airline makes more money.

<br>

## ✈️ Flight Tracking <a id="tracking"></a>
Elevation airlines runs two categories of flights. Scheduled flights are those scheduled from the same airports each day, and run at the same time-of-day no matter what. These are round trip flights, meaning the plane will travel to and from each airport once a day. The second type of flight is random routes, which are scheduled once daily and all planes in the airlines fleet that do not have a scheduled route will fly to a random airport once a day. Departures are set based on the location of the plane at the time, meaning planes will follow a path and can only depart from their current location.

Full API docs can be found <a href="https://github.com/Jackson-Wozniak/Elevation-Airlines/blob/main/backend/flight-tracking-service/flight-tracking-documentation.md" />here</a>

## 📱 Technologies Used <a id="technologies"></a>

#### Backend
- Java
- Spring Boot
- Maven
- MySQL

#### Frontend

- React
- JavaScript

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

## Credits <a id="credits"></a>

Airport and runway data is found from <a href="https://ourairports.com/data"/>ourairports.com</a>

    Airport and runway data is cleaned and filtered in the data_utils directory, to ensure
    that all airports used by Elevation Airlines are capable of handling the specs for each plane in the fleet.
    This is largely done by determining if an airport has a long enough runway, but attributes such as
    the presence of lights and other features work to determine which airports are included as well.

    After the data is cleaned in data_utils, I move the csv to the flight tracking service to be read by the config files in Spring Boot

Passenger data is found from <a href="https://www.faa.gov/airports/planning_capacity/passenger_allcargo_stats/passenger/cy23_all_enplanements">FAA.gov</a>

More passenger data: https://www.bts.gov/browse-statistical-products-and-data/state-transportation-statistics/us-airline-traffic-airport

https://catalog.data.gov/dataset/consumer-airfare-report-table-2-top-1000-city-pair-markets

https://simplemaps.com/data/us-cities

https://www.bea.gov/data/gdp/gdp-state

The image used as the preview for this repo can be found <a href="https://wallpaperaccess.com/full/254381.jpg" />here </a>

<div align="center">
  <kbd> <img src="https://github.com/Jackson-Wozniak/Elevation-Airlines/assets/105665813/13133968-b09d-4036-b944-ac32a0c66a41" width="700" height="318"/> </kbd>
  

  <h3 align="center">Airline Tracker & Reservation System</h3>

  <a href="#"><strong>Live Demo»</strong></a>
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
    <li><a href="#elevation-airlines-overview">Elevation Airlines: Overview & Features</a></li>
    <li><a href="#design-and-docs">Design & Documentation Overview</a></li>
    <ul>
        <li><a href="#airline-server">Airline Server</a></li>
        <li><a href="#reservation-server">Reservation Server</a></li>
    </ul>
    <li><a href="#local-dev-and-contributions">Local Deployment & Contributing</a></li>
    <li><a href="#credits">Credits</a></li>
</ol>    

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## ✈️ Elevation Airlines: Overview & Features <a id="elevation-airlines-overview"></a>

Elevation Airlines is a simulated airline carrier that services airports across the United States. Elevation Airlines uses a Hub-and-Spoke style network, with the operational hub located in Boston Logan Intl (KBOS). Real-world airport data is used to create a route network, integrating economic data to build a profit-centric route map. A reservation and ticketing system is implemented, allowing users to 'book' tickets for scheduled flights.

While real airport and aircraft model data is used by the servers, all generated flight data is fake.

<br>
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## 📓 Design & Documentation Overview <a id="design-and-docs"></a>

### ✈️ Airline Server <a id="airline-server"></a>

Full documentation for Airline.Server can be found <a href="https://github.com/Jackson-Wozniak/Elevation-Airlines/blob/main/Airline.Server/README.md" />here</a>


#### Folder Structure

```md
.
└── Airline.Server/src/
    └── Core/
        ├── Data/
        ├── Entity/
        ├── Exception
        ├── Infrastructure/ -> Contains Caching, PriorityQueue and other implementations
        ├── Interface/
        ├── IO/
        ├── Settings/
        └── Utils/
    └── Domain/
        ├── aircraft/
        ├── airport/
        ├── fleet/
        ├── flight/
        ├── routenetwork/
        └── shared/
    └── Engine/
        ├── Initializer/
        ├── Interface/
        ├── Orchestration/
        └── Service/
```

#### Orchestrators & Initializers

- Upon startup, DatabaseInitializer is run to set static data (airports, aircraft etc.), and clear
database tables if required for setup

- Next, AirlineInitializer is run, handling:
  - creating the serviced route network (more details in the next section)
  - scheduling for the first 7 days of the simulation
  - setting and loading the flight event queue for the first 2 days of scheduled flights

- Once the application is initialized and running, AirlineBatchProcessingService & FlightEventProcessor run as background services, periodically handling relevant scheduled work

- AirlineBatchProcessingService runs each day at midnight, and schedules flights for 7 days from the current date (to ensure 7 days of flights are always scheduled). After this, it loads events into the Flight Event Queue for 2 days out (to ensure 2 days of flights are in the event queue)

- FlightEventProcessor is a background service that handles the internal event queue, using thread delays to await important times for flight events. For example, if the flight is scheduled to begin boarding at 9:00am, a previously queued event will be setoff by the event processor to update the flight status to boarding at 9:00am

#### Flight Scheduling & Route Network Planning

The Flight Scheduling algorithm generates a batch of 'NetworkedRoutes' when starting the application, which are used to track what airports the airline services in their network. Early iterations of the scheduler focus on a strict hub-and-spoke model, meaning that flights either originate from the hub (KBOS - Logan Intl), or return to the hub. This means that the map of networked routes involve flights from Boston to airports across the country, and then a mirrored set of 'return routes' which go from the previous destination back to the hub. This is not exactly realistic to how airlines would approach flight scheduling, however it works as starting point to optimize further down the line.

#### Simulating Flights with Events

To design a scalable approach to simulating flights, a centralized event queue is used to queue up
and process important steps throughout each scheduled flight. Once a flight is scheduled to start within two days of the current midnight batch process, a set of FlightEvents are created, accounting for major changes in the status of a flight (begin boarding, takeoff, landing/completion). Further down the line, periodic Flight Positional updates can be queued to accurately track the exact position of a flight along its expected flight plan, potentially altering other events in the queue that may be dependent on the timeline of a flight (for example, future flights by the plane if delays occur). In the current implementation however, the flight events are static, and occur exactly at their scheduled time. 

As stated before, this approach allows for higher scalability, by creating only 3 processed events for each flight. If a future implementation involves position-based updates and events, this could be similarly scaled by queueing periodic positional updates, adding more to the queue if the flight is delayed and needs more updates to await completion

#### Flight Data Publisher

A Redis Pub/Sub message queue is used to broadcast flight data. When a set of flights are scheduled (either by the AirlineInitializer or by AirlineBatchProcessingService), a message is broadcast for each of the flights.

Similarly, messages are broadcast when the FlightEventProcessor handles a new event in the queue, such as a status change from boarding to takeoff, so that the reservation service can remain synchronized with the airlines current and future operations

<br>
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

### 🎟️ Reservation Server <a id="reservation-server"></a>

Full documentation for Airline.Server can be found <a href="https://github.com/Jackson-Wozniak/Elevation-Airlines/blob/main/Reservation.Server/README.md" />here</a>

#### Folder Structure

```md
.
└── Reservation.Server/src/
    └── Core/
        ├── Data/
        ├── Entity/
        ├── Exception
        ├── Infrastructure/
        └── Settings/
    └── Features/
        ├── Airline/
        |    ├── Fleet/
        |    ├── Flights/
        |    └── Operations/
        ├── MasterData/ -> Static data that overrides those read by the Airline Server
        |    ├── Aircraft/
        |    └── Airport/
        ├── Reservation/
        |    ├── Billing/
        |    ├── Booking/
        |    └── Pricing/
        └── Users/
    └── Initialization/
```

#### Airline Subscriber & Synchronization

#### Creating open seats and pricing for flights

#### Booking System

#### Users & Billing


<br>
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## Local Development & Contributions <a id="local-dev-and-contributions"></a>

TODO...

<br>
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

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

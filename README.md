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
    <li><a href="#features">Overview & Current Undertakings</a></li>
    <li><a href="#airline-simulation">Airline Simulation Approach</a></li>
    <li><a href="#credits">Credits</a></li>
</ol>    

<br/> 
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

## 📓 Overview & Current Undertakings <a id="features"></a>

An airline tracking system that generates flights to popular airports across the world. The airline used in this simulation is called Elevation Airlines, and is a fake airline. Route generation is largely random, and the flights each plane takes does not necessarily match the popular flights real airlines fly.

<br>

## ✈️ Airline Simulation Approach <a id="airline-simulation"></a>

### The Steps for Flight Scheduling & Simulation

1. On application startup, the fleet of aircraft and network of serviced routes are set
2. On application startup, the first 7 days of flights are scheduled (or verified if already present) to allow for recovery after failure or setting initial state
3. After the 7 days of flights are scheduled, the first 2 days of flights are sent to the event queue
4. Every day at midnight, flights are scheduled for 7 days from now, ensuring 7 days of flights are always scheduled
5. During this batch scheduling, flights scheduled 2 days from now are sent to the event queue, ensuring the event queue always has 2 days worth of events
6. The event queue works in the background, processing events for flights in chronological order
    - Flight events are, in order: Boarding -> Takeoff/In Progress -> Completed -> In Layover or Maintenance
7. FlightService keeps a rolling cache of flights in-memory for fast retrieval, usually of in progress flights


### Network Planner

The network planner is run once on application startup. This creates a map of serviced routes. For the
early models, a very simple hub-and-spoke model is used, where flights depart from the Hub to a destination, and then 
immediately return to the hub for the next flight. The current network planner simply creates a route to/from the top 2 airports in each state and the hub airport (KBOS).

### Route Scheduler

The route scheduler is run using dates to create a flight table. On app startup, the first 7 days of flights are scheduled.
After that, a batch process is run at midnight that schedules the flight table for 7 days from the current date.
During this batch process, the flights scheduled to start on the next 2 days are sent to the event queue.
This creates a rolling queue of scheduled flights that is always 7 days out, and a rolling queue of flight events that is always 2 days out.
For flights that take place across multiple days (for example starts at 10pm and ends at 5am), the flight's events are
scheduled once as if it took place over one day. Below is an example:

Today is the 1st. The app starts up and schedules flights for the 2nd-8th. Flights
starting on the 2nd and 3rd are sent to the event queue with their boarding, takeoff and completion events.
--- At midnight on the 2nd, flights for the 9th are scheduled, and FlightEvents are sent to event queue for all flights
that start on the 4th. --- At this point, the previous steps repeat for subsequent days.

IMPORTANT: In the event of failure recovery, where flights are already scheduled and the app
needs to be restarted, the event queue is clear and preloaded with the upcoming 2 days of flight events

### Central Event Queue

The central event queue is the engine of the flight simulation, enabling scalable and ordered processing of flight events.
The central event queue is sent Flight Events based on milestones in a scheduled flight (boarding, takeoff, completion).
These FlightEvents are given an execution time. The worker thread uses non-blocking delays to wait until the time of the next event,
at which point it is executed and taken out of the queue.

FlightEvents hold reference to the Flight entity id. This allows the event queue to remain stateless. Execution and state updates
are handled by the FlightService, which maintains an in-memory cache of flights, and can
persist to the database as needed.

IMPORTANT: The expected implementation of flight simulation will start with a simple fixed-time
event queue for each flight. This means that we know beforehand exactly what time each event happens. Down the line,
more realistic tracking could be introduced. This would replace a 'Flight Completion Event' with periodically scheduled FlightPositionUpdate events,
which would call the dynamic FlightTrackingService to ask the whereabouts of the plane. It would then detect the completion of the flight
plan, and handle the completion of the flight at that point. This is paramount to introducing dynamic flight tracking, allowing
for flights to follow a non-deterministic path from waypoint to waypoint, completing on it's own schedule rather than a fixed schedule.
That said, for the minimum viable product (MVP), this is not strictly necessary and so fixed-time events suffice


Full simulation documentation can be found <a href="https://github.com/Jackson-Wozniak/Elevation-Airlines/blob/main/backend/README.md" />here</a>

<br>

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

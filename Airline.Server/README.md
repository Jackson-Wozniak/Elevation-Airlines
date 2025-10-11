### The Steps for Flight Simulation

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

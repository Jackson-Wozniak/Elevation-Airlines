### Flight Tracking API Plan
```
- Elevation Airlines (fake airline) will have a total of 15 weekly routes, flown at the
    same time/day each week
- The company will have an auto-generated plane fleet which will also be used to connect different airports
    throughout the week, on top of the regular routes
- Flight Schedules are updated daily and are scheduled for
    exactly one week out, and track in real 24-hour intervals
```

### Flight Tracking URL Endpoints
*All endpoints are publicly available*
```
- GET | http://localhost:8080/api/v1/tracking/live-flights
Get all flights currently in the air
```

```
- GET | http://localhost:8080/api/v1/tracking/daily_flights&date={date}
Get all flights scheduled on date

{date} must be formatted as MM/DD/YYYY
```

```
- GET | http://localhost:8080/api/v1/tracking
Get all flights currently scheduled
```

```
- GET | http://localhost:8080/api/v1/tracking/call_sign/{callSign}
Get all flights currently scheduled for a specific planes call sign
```

```
- GET | http://localhost:8080/api/v1/tracking/{identifier}
View the route & status of a flight based on the flight's indentifier
```

```
- GET | http://localhost:8080/api/v1/tracking&departure={departureAirport}
View flights departing from inputted airport

{departureAirport} must be the relevant ICAO code
```

```
- GET | http://localhost:8080/api/v1/tracking&destination={destinationAirport}
View flights arriving to inputted airport

{destinationAirport} must be the relevant ICAO code
```

```
- POST | http://localhost:8080/api/v1/tracking/generate_path?departure={departureAirport}&destination={destinationAirport}
Create a generated flight path departing from one airport and arriving at another.
Flight paths will either be direct or connecting flights, and will only generate
if the required routes are scheduled

 {departureAirport} & {destinationAirport} must be the relevant ICAO code
```

### REST API JSON Object Mapping
```
Flight:
    - identifier: string
    - callSign: string
    - plane: object
    - route: object
```

```
Plane:
    - model: string
    - economy seat capacity: int
    - economy seat price: double
    - luxury seat capacity: int
    - luxury seat price: double
    - cruising speed (knots): int
    - cruising altitude (ft): int
```

```
Route:
    - departure airport: object
    - destination airport: object
    - timeToArrival
    - scheduled date/time
    - liveFlight: boolean
```

```
Airport:
    - ICAO code: string
    - name: string
    - country: string
    - continent: string
    - latitude: double
    - longitude: double
```
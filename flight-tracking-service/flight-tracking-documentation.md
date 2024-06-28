### Flight Tracking API Plan

- Elevation Airlines (fake airline) will have a total of 15 weekly routes, flown at the
    same time/day each week
- The company will have an auto-generated plane fleet which will also be used to connect different airports
    throughout the week, on top of the regular routes
- Flight Schedules are updated daily and are scheduled for
    exactly one week out, and track in real 24-hour intervals

---

### Flight Tracking URL Endpoints
*All endpoints are publicly available*

<details>
 <summary>
    <code>GET</code> <code>/api/v1/tracking/path_generator?departure={departure}&destination={destination}</code> generates an optimized flight plan between departure and destination airports. Uses graph algorithm optimizing by shortest path duration
 </summary>

##### URL Parameters
>departure: icao code of root airport node | required
>destination: icao code of target airport node | required
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/tracking/</code> returns all flights currently scheduled
 </summary>

##### URL Parameters
>None
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/tracking/live_flights</code> returns all flights currently in the air
 </summary>

##### URL Parameters
>None
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/tracking/call_sign/{callSign}</code> returns all flights scheduled for a given plane
 </summary>

##### URL Parameters
>callSign: String formatted as ELV{num 1-999} | required
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/tracking/time_table?date={date}</code> returns all flights scheduled on given date
 </summary>

##### URL Parameters
>date: String formatted as mm/dd/yyyy | required
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/tracking/{identifier}</code> return a flight by the given identifier
 </summary>

##### URL Parameters
>identifier: 64-bit integer | required
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/tracking?departure={departureAirport}</code> returns all flights scheduled to depart from given airport
 </summary>

##### URL Parameters
>departureAirport: String containing airport ICAO code | not required
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/tracking?destination={destinationAirport}</code> returns all flights scheduled to arrive at a given airport
 </summary>

##### URL Parameters
>destinationAirport: String containing airport ICAO code | not required
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>POST</code> <code>/api/v1/tracking/generate_path?departure={departure}&destination={destination}</code> If possible, generate a path from one airport to another based on scheduled flight plans
 </summary>

##### URL Parameters
>departure: String containing airport ICAO code | required
>destination: String containing airport ICAO code | required
</details>

<!-- -------------------------------------------------------------------------------- -->

---

### REST API JSON Object Mapping
```
Flight:
    - identifier: string
    - plane: object
    - route: object
    - takeOffDateTime: String mm/dd/yyyy HH:mm
```

```
Plane:
    - model: string
    - seatCapacity: int
    - luxurySeats: int
    - speedKnots: int
    - rangeMiles: int
```

```
Route:
    - departureAirport: object
    - destinationAirport: object
    - duration: String HH:mm
    - miles: float (2 decimal places)
```

```
Airport:
    - icaoCode: string
    - name: string
    - country: string
    - continent: string
    - latitude: double
    - longitude: double
```
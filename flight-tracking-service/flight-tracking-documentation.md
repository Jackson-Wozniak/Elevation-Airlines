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

*For each endpoint, a query parameter 'is_detailed'=true can be added to return the full entity in the response, rather than the summary information*

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/flights</code> returns all flights currently scheduled
 </summary>

##### URL Parameters
>departure: ICAO code
> 
>destination: ICAO code
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/flights/live</code> returns all flights currently in the air
 </summary>

##### URL Parameters
>None
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/flights/call_sign/{callSign}</code> returns all flights scheduled for a given plane
 </summary>

##### URL Parameters
>callSign: String formatted as ELV{num 1-999} | required
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/flights/time_table</code> returns all flights scheduled in given date range
 </summary>

##### URL Parameters
>start: String formatted as mm/dd/yyyy | required
> 
>end: String formatted as mm/dd/yyyy | required
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/flights/identifier/{identifier}</code> return a flight by the given identifier
 </summary>

##### URL Parameters
>identifier: 64-bit integer | required
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
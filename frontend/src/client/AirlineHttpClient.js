class FlightSummary {
    departure;
    destination;
    callsign;
    planeType;
    departureTime;
    distanceMiles;
    flightTime;

    constructor(dep, dest, sign, plane, miles, takeoff, time){
        this.departure = dep;
        this.destination = dest;
        this.callsign = sign;
        this.planeType = plane;
        this.distanceMiles = miles;
        this.departureTime = takeoff;
        this.flightTime = time;
    }
}

export async function getAllFlights(){
    let response = await fetch("http://localhost:8080/api/v1/flights");

    if(!response.ok){
        alert("Error in getting flights");
        return [];
    }

    let data = await response.json();

    const flights = data.map((flight) => (
            new FlightSummary(
                flight.departure,
                flight.destination,
                flight.callsign,
                flight.planeType,
                flight.distanceMiles,
                flight.departureTime,
                flight.flightTime
            )
    ));
    return flights;
}

export { FlightSummary };
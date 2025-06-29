import type { FlightDto } from '../types/Dtos';

const ROOT_URL: string = "http://localhost:8080/api/v1";
const FLIGHTS_URL: string = ROOT_URL + "/flights";

export async function getAllFlights(){
    // let response = await fetch(FLIGHTS_URL);

    // if(!response.ok){
    //     alert("ERROR IN getAllFlights()");
    //     return [];
    // }

    // const data = await response.json();

    // const mapped: FlightDto[] = data.map((flight: any) => ({
    //     identifier: flight.identifier,
    //     departure: flight.departure,
    //     destination: flight.destination,
    //     callsign: flight.callsign,
    //     planeType: flight.planeType,
    //     scheduledBoarding: flight.scheduledBoarding,
    //     scheduledTakeoff: flight.scheduledTakeoff,
    //     scheduledArrival: flight.scheduledArrival,
    //     distanceMiles: flight.distanceMiles
    // }));

    // return mapped;
    let flights: FlightDto[] = [{
        identifier: 1100321,
        departure: "departure",
        destination: "destination",
        callsign: "flight.callsign",
        planeType: "flight.planeType",
        scheduledBoarding: "flight.scheduledBoarding",
        scheduledTakeoff: "flight.scheduledTakeoff",
        scheduledArrival: "flight.scheduledArrival",
        distanceMiles: 100.0
    }];
    return flights;
}
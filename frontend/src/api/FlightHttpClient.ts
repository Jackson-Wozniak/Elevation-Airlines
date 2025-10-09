import type { FlightDto } from '../types/flight/flight.dto';

const ROOT_URL: string = "http://localhost:5258/api";
const FLIGHTS_URL: string = ROOT_URL + "/Flight";

export async function fetchFlights(): Promise<FlightDto[]> {
    let response = await fetch(FLIGHTS_URL);

    if(!response.ok){
        alert("ERROR IN fetchFlights()");
        return [];
    }

    const data = await response.json();

    const mapped: FlightDto[] = data as FlightDto[];

    return mapped;   
}
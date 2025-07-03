import type { FlightDto } from '../types/Dtos';
import { getAllFlightsSample } from '../data/HttpClientDemoData';
import { useDemoMode } from '../AppInitializer';

const ROOT_URL: string = "http://localhost:8080/api/v1";
const FLIGHTS_URL: string = ROOT_URL + "/flights";

export async function isServerRunning(){
    return false;
}

export async function getAllFlights(isDemo: boolean){
    if(isDemo) return getAllFlightsSample(25);

    let response = await fetch(FLIGHTS_URL);

    if(!response.ok){
        alert("ERROR IN getAllFlights()");
        return [];
    }

    const data = await response.json();

    const mapped: FlightDto[] = data.map((flight: any) => ({
        identifier: flight.identifier,
        departure: flight.departure,
        destination: flight.destination,
        callsign: flight.callsign,
        planeType: flight.planeType,
        scheduledBoarding: flight.scheduledBoarding,
        scheduledTakeoff: flight.scheduledTakeoff,
        scheduledArrival: flight.scheduledArrival,
        distanceMiles: flight.distanceMiles
    }));

    return mapped;
}
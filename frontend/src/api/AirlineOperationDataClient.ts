import type { AirlineOperationDataDto } from '../types/airline/AirlineOperationData';
import type { FlightDto } from '../types/flight/flight.dto';

const ROOT_URL: string = "http://localhost:5258/api/airlineoperationdata";

export async function fetchAirlineOperationData(): Promise<AirlineOperationDataDto> {
    let response = await fetch(ROOT_URL);

    // if(!response.ok){
    //     alert("ERROR IN fetchFlights()");
    //     return [];
    // }

    const data = await response.json();

    return data;
}
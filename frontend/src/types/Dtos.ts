interface RouteDto{

}

export interface FlightDto{
    identifier: number;
    departure: string;
    destination: string;
    callsign: string;
    planeType: string;
    scheduledBoarding: string;
    scheduledTakeoff: string;
    scheduledArrival: string;
    distanceMiles: number;
    status: string;
}
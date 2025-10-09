import type { AirportDto } from "../airport/airport.dto";
import type { PlaneDto } from "../plane/plane.dto";

export interface FlightDto {
    status: string,
    plane: PlaneDto,
    flightPlan: FlightPlanDto,
    boardingTime: string,
    takeoffTime: string,
    expectedArrivalTime: string
}

export interface FlightPlanDto {
    departure: AirportDto,
    destination: AirportDto,
    distanceNauticalMiles: number
}
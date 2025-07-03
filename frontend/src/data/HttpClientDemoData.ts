import type { FlightDto } from "../types/Dtos";

const AIRPORT_CODES = ["KATL", "KLAX", "KORD", "KDFW", "KDEN", "KJFK", "KSFO", "KSEA", "KLAS", "KMIA"];
const PLANE_TYPES = ["737 MAX 10", "A320neo", "787-9", "A350-1000", "737-800", "A321XLR", "777-300ER", "A220-300", "767-300F", "CRJ-900"];
const BOARDING_TIMES = [
  "07/03/25 08:00", "07/03/25 09:15", "07/03/25 11:45", "07/03/25 13:30", "07/03/25 15:20",
  "07/03/25 16:10", "07/03/25 18:00", "07/03/25 19:30", "07/03/25 21:00", "07/03/25 22:15"
];

const DEPARTURE_TIMES = [
  "07/03/25 08:30", "07/03/25 09:45", "07/03/25 12:15", "07/03/25 14:00", "07/03/25 15:50",
  "07/03/25 16:40", "07/03/25 18:30", "07/03/25 20:00", "07/03/25 21:30", "07/03/25 22:45"
];

const ETA = [
  "07/03/25 12:15", "07/03/25 14:05", "07/03/25 16:40", "07/03/25 19:00", "07/03/25 21:10",
  "07/03/25 22:50", "07/04/25 00:35", "07/04/25 01:45", "07/04/25 03:00", "07/04/25 04:50"
];

const STATUS_MODES = ["SCHEDULED", "BOARDING", "IN FLIGHT", "ARRIVED"];

export function getAllFlightsSample(count: number){
    let sampleFlights: FlightDto[] = [];

    for(let i = 0; i < count; i++){
        const timesIndex = randomNumber(0, BOARDING_TIMES.length);
        const depIndex = randomNumber(0, AIRPORT_CODES.length);
        const destIndex = randomNumber(0, AIRPORT_CODES.length);
        const planeTypeIndex = randomNumber(0, PLANE_TYPES.length);

        sampleFlights.push({
            identifier: randomNumber(1000000, 2400000),
            departure: AIRPORT_CODES[depIndex],
            destination: AIRPORT_CODES[destIndex],
            callsign: ("ELEV " + randomNumber(100, 401)),
            planeType: PLANE_TYPES[planeTypeIndex],
            scheduledBoarding: BOARDING_TIMES[timesIndex],
            scheduledTakeoff: DEPARTURE_TIMES[timesIndex],
            scheduledArrival: ETA[timesIndex],
            distanceMiles: randomNumber(1000, 4000),
            status: STATUS_MODES[randomNumber(0, STATUS_MODES.length)]
        });
    }
    return sampleFlights;
}

function randomNumber(min: number, max: number){
    return Math.floor(Math.random() * (max - min) + min);
}

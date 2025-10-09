
export interface PlaneDto {
    callSign: string,
    aircraft: AircraftDto,
    status: string
}

export interface AircraftDto {
    manufacturer: string,
    model: string,
    cruiseSpeedKnots: number,
    rangeNauticalMiles: number,
    passengerCapacity: number,
    category: string
}
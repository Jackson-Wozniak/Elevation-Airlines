using Airline.Server.Domain.airport.Dto;

namespace Airline.Server.Domain.routenetwork.Dto;

public class AirportConnectionDto
{
    public AirportDto Airport { get; set; }
    public List<AirportDto> ServicedAirports { get; set; }

    public AirportConnectionDto(KeyValuePair<AirportDto, List<AirportDto>> pair)
    {
        Airport = pair.Key;
        ServicedAirports = pair.Value;
    }
}
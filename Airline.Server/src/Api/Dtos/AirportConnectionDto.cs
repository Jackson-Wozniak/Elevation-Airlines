namespace Airline.Server.Api.Dtos;

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
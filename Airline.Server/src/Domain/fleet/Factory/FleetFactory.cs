using Airline.Server.Engine.Settings;
using Airline.Server.Core.Utils;
using Airline.Server.Domain.aircraft.Service;
using Airline.Server.Domain.fleet.Entity;
using Airline.Server.Domain.fleet.Enums;
using Microsoft.Extensions.Options;

namespace Airline.Server.Domain.fleet.Factory;

public class FleetFactory(
    IOptions<SimulationSettings> options, 
    AircraftService aircraftService)
{
    private readonly SimulationSettings _simulationSettings = options.Value;
    
    public List<Plane> Create(int count, List<string>? usedCallsigns = null)
    {
        usedCallsigns ??= [];
        var aircraftTypes = aircraftService.GetAllAircraft();
        var fleet = new List<Plane>();
        for (int i = 0; i < count; i++)
        {
            var value = RandomUtils.Next(100, 2000);
            if (usedCallsigns.Contains($"ELEV{value}"))
            {
                i--;
                continue;
            }

            var callSign = $"ELEV{value}";
            fleet.Add(new Plane(callSign, aircraftTypes.Random(), PlaneStatus.Parked));
        }

        return fleet;
    }
}
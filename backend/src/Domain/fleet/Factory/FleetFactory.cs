using backend.Core.Settings;
using backend.Core.Utils;
using backend.Domain.aircraft.Service;
using backend.Domain.fleet.Entity;
using backend.Domain.fleet.Enums;

namespace backend.Domain.fleet.Factory;

public class FleetFactory(SimulationSettings settings, AircraftService aircraftService)
{
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
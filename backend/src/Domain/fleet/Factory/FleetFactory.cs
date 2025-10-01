using backend.Core.Settings;
using backend.Domain.fleet.Entity;

namespace backend.Domain.fleet.Factory;

public class FleetFactory(SimulationSettings settings)
{
    public List<Plane> Create(int count, List<string>? usedCallsigns = null)
    {
        usedCallsigns ??= [];

        throw new NotImplementedException();
    }
}
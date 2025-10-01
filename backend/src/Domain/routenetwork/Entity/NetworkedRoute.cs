using backend.Core.Entity;
using Route = backend.Domain.shared.Route;

namespace backend.Domain.routenetwork.Entity;

public class NetworkedRoute : BaseEntity
{
    public Route Route { get; set; }
}
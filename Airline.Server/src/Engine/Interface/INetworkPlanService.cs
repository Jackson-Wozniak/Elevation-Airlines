using Airline.Server.Domain.routenetwork.Entity;

namespace Airline.Server.Engine.Interface;

public interface INetworkPlanService
{
    List<NetworkedRoute> CreateRouteNetwork();
}
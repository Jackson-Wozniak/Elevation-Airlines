using backend.Domain.routenetwork.Entity;

namespace backend.Engine.Scheduling.Interface;

public interface INetworkPlanService
{
    List<NetworkedRoute> CreateRouteNetwork();
}
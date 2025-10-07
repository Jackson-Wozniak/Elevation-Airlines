using backend.Domain.routenetwork.Entity;

namespace backend.Engine.Interface;

public interface INetworkPlanService
{
    List<NetworkedRoute> CreateRouteNetwork();
}
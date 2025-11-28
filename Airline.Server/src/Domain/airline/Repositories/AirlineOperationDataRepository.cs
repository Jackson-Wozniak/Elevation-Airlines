using Airline.Server.Core.Data;
using Airline.Server.Domain.airline.Entities;

namespace Airline.Server.Domain.airline.Repositories;

public class AirlineOperationDataRepository(ApplicationDbContext context)
{
    private const long EntityId = 1;
    
    public AirlineOperationData Find()
    {
        AirlineOperationData? data = context.AirlineOperationData
            .SingleOrDefault(a => a.Id == EntityId);
        
        if (data is not null) return data;
        
        data = AirlineOperationData.Create();
        context.Add(data);
        context.SaveChanges();
        return data;
    }

    public AirlineOperationData Update(AirlineOperationData data)
    {
        data.Id = EntityId;
        context.Update(data);
        context.SaveChanges();
        return data;
    }
}
using backend.Core.Entity;

namespace backend.Core.Infrastructure.Caching;

public class ConcurrentEntityCache<T> : IEntityCache<T> where T : BaseEntity
{
    public int Capacity { get; }
    private Dictionary<long, T> Items { get; set; } = [];
    private readonly Lock _lock = new();

    public ConcurrentEntityCache(int capacity)
    {
        Capacity = capacity;
    }

    public int Count()
    {
        lock (_lock)
        {
            return Items.Count;
        }
    }

    public T? FindOrDefault(long id)
    {
        lock (_lock)
        {
            if (!Items.ContainsKey(id)) return null;
            return Items[id];
        }
    }

    public T? PutOrUpdate(T value)
    {
        return null;
    }

    public void Put(T value)
    {
        
    }

    public void PutAll(List<T> value)
    {
        
    }
}
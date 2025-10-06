using backend.Core.Entity;

namespace backend.Core.Infrastructure.Caching;

public interface IEntityCache<T> where T : BaseEntity
{
    int Capacity { get; }
    int Count();
    T? FindOrDefault(long id);
    T? PutOrUpdate(T value);
    void Put(T value);
    void PutAll(List<T> value);
}
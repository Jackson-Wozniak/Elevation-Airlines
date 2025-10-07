namespace backend.Core.Infrastructure.EventQueue;

public class PriorityEventQueue<T> where T : Event
{
    private readonly PriorityQueue<T, DateTime> _queue = new();
    private readonly Lock _lock = new();
    private readonly SemaphoreSlim _semaphore = new(0);

    public void Enqueue(T val)
    {
        lock (_lock)
        {
            _queue.Enqueue(val, val.ScheduledTime);
            _semaphore.Release();
        }
    }
    
    public void Enqueue(List<T> vals)
    {
        lock (_lock)
        {
            _queue.EnqueueRange(vals.Select(val => (Element: val, Priority: val.ScheduledTime)));
            _semaphore.Release(vals.Count);
        }
    }

    public async Task<T?> DequeueAsync(CancellationToken token)
    {
        await _semaphore.WaitAsync(token);
        lock (_lock)
        {
            try
            {
                return _queue.Dequeue();
            }
            catch { return null; }
        }
    }

    public int Count()
    {
        lock (_lock)
        {
            return _queue.Count;
        }
    }
}
namespace backend.Core.Infrastructure.EventQueue;

public class PriorityEventQueue<T> where T : Event
{
    private readonly PriorityQueue<T, DateTime> _queue = new();
    private readonly Lock _lock = new();

    public void Enqueue(T val)
    {
        lock (_lock)
        {
            _queue.Enqueue(val, val.ScheduledTime);
        }
    }
    
    public void Enqueue(IEnumerable<T> vals)
    {
        lock (_lock)
        {
            _queue.EnqueueRange(vals.Select(val => (Element: val, Priority: val.ScheduledTime)));
        }
    }

    public T? Dequeue()
    {
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
using Airline.Server.Core.Infrastructure.EventQueue;

namespace Airline.Server.Tests.Core.Infrastructure.EventQueue;

public class PriorityEventQueueTests
{
    [Fact]
    public void Enqueue_OneInsert_CountIs1()
    {
        var queue = new PriorityEventQueue<EventTest>();
        queue.Enqueue(new EventTest{ScheduledTime = DateTime.Now});
        Assert.Equal(1, queue.Count());
    }
    
    [Fact]
    public void Enqueue_IncrementalInsert_CountIsN()
    {
        var queue = new PriorityEventQueue<EventTest>();
        for (var i = 1; i <= 100; i++)
        {
            queue.Enqueue(new EventTest{ScheduledTime = DateTime.Now});
            Assert.Equal(i, queue.Count());
        }
    }
}
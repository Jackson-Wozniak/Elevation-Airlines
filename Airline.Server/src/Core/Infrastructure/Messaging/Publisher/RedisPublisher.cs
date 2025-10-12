using Airline.Server.Core.Infrastructure.Messaging.Message;
using StackExchange.Redis;

namespace Airline.Server.Core.Infrastructure.Messaging.Publisher;

public class RedisPublisher : IPublisher
{
    private readonly ISubscriber _subscriber;
    private bool _overrideExceptionStopPublisher;

    public RedisPublisher(ILogger<RedisPublisher> logger, string connectionString)
    {
        try
        {
            _subscriber = ConnectionMultiplexer.Connect(connectionString).GetSubscriber();
        }
        catch
        {
            _overrideExceptionStopPublisher = true;
            logger.LogError($"Could not connect to Redis with url({connectionString}). Publisher will be OFFLINE.");
        }
    }
    
    public void Publish(string channel, IMessage message)
    {
        if (_overrideExceptionStopPublisher) return;
        _subscriber.Publish(RedisChannel.Literal(channel), message.Serialize());
    }

    public async Task PublishAsync(string channel, IMessage message)
    {
        if (_overrideExceptionStopPublisher) return;
        await _subscriber.PublishAsync(RedisChannel.Literal(channel), message.Serialize());
    }
}
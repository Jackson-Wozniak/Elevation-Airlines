using Airline.Server.Core.Infrastructure.Messaging.Message;
using StackExchange.Redis;

namespace Airline.Server.Core.Infrastructure.Messaging.Publisher;

public class RedisPublisher : IPublisher
{
    private readonly ISubscriber _subscriber;

    public RedisPublisher(string connectionString)
    {
        _subscriber = ConnectionMultiplexer.Connect(connectionString).GetSubscriber();
    }
    
    public void Publish(string channel, IMessage message)
    {
        _subscriber.Publish(RedisChannel.Literal(channel), message.Serialize());
    }

    public async Task PublishAsync(string channel, IMessage message)
    {
        await _subscriber.PublishAsync(RedisChannel.Literal(channel), message.Serialize());
    }
}
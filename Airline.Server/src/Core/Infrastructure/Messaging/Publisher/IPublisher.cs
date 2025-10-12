using Airline.Server.Core.Infrastructure.Messaging.Message;

namespace Airline.Server.Core.Infrastructure.Messaging.Publisher;

public interface IPublisher
{
    Task PublishAsync(string channel, IMessage message);
    void Publish(string channel, IMessage message);
}
namespace Airline.Server.Core.Infrastructure.Messaging.Message;

public interface IMessage
{
    string Serialize();
}
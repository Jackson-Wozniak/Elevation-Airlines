using System.Net;

namespace Reservation.Server.Core.Exception.types;

public class UnknownException : BaseException
{
    public UnknownException(string message)
        : base(message, HttpStatusCode.InternalServerError) { }
}
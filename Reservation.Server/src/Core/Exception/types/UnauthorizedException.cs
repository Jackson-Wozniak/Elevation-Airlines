using System.Net;

namespace Reservation.Server.Core.Exception.types;

public class UnauthorizedException : BaseException
{
    public UnauthorizedException(string message) 
        : base(message, HttpStatusCode.Unauthorized) { }
}
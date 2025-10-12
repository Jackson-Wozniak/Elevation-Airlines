using System.Net;

namespace Reservation.Server.Core.Exception.types;

public class NotFoundException : BaseException
{
    public NotFoundException(string message) 
        : base(message, HttpStatusCode.NotFound) { }
}
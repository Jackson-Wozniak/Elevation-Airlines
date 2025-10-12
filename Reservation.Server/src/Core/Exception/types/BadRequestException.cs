using System.Net;

namespace Reservation.Server.Core.Exception.types;

public class BadRequestException : BaseException
{
    public BadRequestException(string message, string source) 
        : base(message, HttpStatusCode.BadRequest, source) { }
}
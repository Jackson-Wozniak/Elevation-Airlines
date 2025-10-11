using System.Net;

namespace Airline.Server.Core.Exception.types;

public class BadRequestException : BaseException
{
    public BadRequestException(string message, string source) 
        : base(message, HttpStatusCode.BadRequest, source) { }
}
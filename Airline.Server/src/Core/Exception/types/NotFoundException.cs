using System.Net;

namespace Airline.Server.Core.Exception.types;

public class NotFoundException : BaseException
{
    public NotFoundException(string message, string source) 
        : base(message, HttpStatusCode.NotFound, source) { }
}
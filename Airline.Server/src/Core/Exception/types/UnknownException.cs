using System.Net;

namespace Airline.Server.Core.Exception.types;

public class UnknownException : BaseException
{
    public UnknownException(string message, string source)
        : base(message, HttpStatusCode.InternalServerError, source) { }
}
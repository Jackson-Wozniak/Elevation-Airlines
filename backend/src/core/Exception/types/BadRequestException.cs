using System.Net;

namespace backend.core.Exception.types;

public class BadRequestException : BaseException
{
    public BadRequestException(string message, string source) 
        : base(message, HttpStatusCode.BadRequest, source) { }
}
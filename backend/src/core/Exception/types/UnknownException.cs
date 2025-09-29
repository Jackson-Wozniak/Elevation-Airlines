using System.Net;

namespace backend.core.Exception.types;

public class UnknownException : BaseException
{
    public UnknownException(string message, string source)
        : base(message, HttpStatusCode.InternalServerError, source) { }
}
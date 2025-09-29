using System.Net;

namespace backend.core.Exception.types;

public class NotFoundException : BaseException
{
    public NotFoundException(string message, string source) 
        : base(message, HttpStatusCode.NotFound, source) { }
}
using System.Net;

namespace backend.Core.Exception.types;

public class NotFoundException : BaseException
{
    public NotFoundException(string message, string source) 
        : base(message, HttpStatusCode.NotFound, source) { }
}
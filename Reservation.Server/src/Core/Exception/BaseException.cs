using System.Net;
using Microsoft.AspNetCore.Mvc;

namespace Reservation.Server.Core.Exception;

public abstract class BaseException : System.Exception
{
    public HttpStatusCode Status { get; set; }
    public string CallingSource { get; set; }

    protected BaseException(string message, HttpStatusCode status, string source) 
        : base(message)
    {
        Status = status;
        CallingSource = source;
    }

    public JsonResult Json()
    {
        return new JsonResult(this);
    }
}
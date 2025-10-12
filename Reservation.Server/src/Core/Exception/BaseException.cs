using System.Net;
using Microsoft.AspNetCore.Mvc;

namespace Reservation.Server.Core.Exception;

public abstract class BaseException : System.Exception
{
    public HttpStatusCode Status { get; set; }

    protected BaseException(string message, HttpStatusCode status) 
        : base(message)
    {
        Status = status;
    }

    public JsonResult Json()
    {
        return new JsonResult(this);
    }
}
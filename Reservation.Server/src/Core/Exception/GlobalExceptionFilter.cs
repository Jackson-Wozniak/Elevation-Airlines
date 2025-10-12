using Reservation.Server.Core.Exception.types;
using Microsoft.AspNetCore.Mvc.Filters;

namespace Reservation.Server.Core.Exception;

public class GlobalExceptionFilter : IExceptionFilter
{
    public void OnException(ExceptionContext context)
    {

        if (context.Exception is BaseException ex)
        {
            context.Result = ex.Json();
        }
        else
        {
            context.Result = new UnknownException(context.Exception.Message).Json();
        }

        context.ExceptionHandled = true;
    }
}
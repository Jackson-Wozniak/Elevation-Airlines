using backend.core.Exception.types;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;

namespace backend.core.Exception;

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
            context.Result = new UnknownException(context.Exception.Message, 
                "GlobalExceptionFilter.OnException()").Json();
        }

        context.ExceptionHandled = true;
    }
}
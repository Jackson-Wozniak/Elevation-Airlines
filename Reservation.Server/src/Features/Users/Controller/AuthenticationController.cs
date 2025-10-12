using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Reservation.Server.Features.Users.Dto;
using Reservation.Server.Features.Users.Service;

namespace Reservation.Server.Features.Users.Controller;

[ApiController]
[Route("api/[controller]")]
public class AuthenticationController(
    AuthenticationService authenticationService) : ControllerBase
{
    [HttpPost("Register")]
    public async Task<ActionResult<string>> RegisterNewUser([FromBody] UserCredentialsDto credentials)
    {
        var token = await authenticationService.RegisterUser(credentials);
        return Ok(token);
    }
    
    [HttpPut("Login")]
    public async Task<ActionResult<string>> LoginUser([FromBody] UserCredentialsDto credentials)
    {
        var token = await authenticationService.LoginUser(credentials);
        return Ok(token);
    }
    
    [HttpGet("Verify")]
    [Authorize]
    public async Task<ActionResult<string>> VerifyUser()
    {
        var user = await authenticationService.GetUserFromHttpContext();
        return Ok(new UserDto(user));
    }
}
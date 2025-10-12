using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Microsoft.IdentityModel.Tokens;
using Reservation.Server.Core.Exception.types;
using Reservation.Server.Features.Users.Dto;
using Reservation.Server.Features.Users.Entity;
using Reservation.Server.Features.Users.Repository;

namespace Reservation.Server.Features.Users.Service;

public class AuthenticationService(UserRepository userRepository,
    IHttpContextAccessor httpContextAccessor, IConfiguration configuration)
{
    public async Task<User> GetUserFromHttpContext()
    {
        var username = httpContextAccessor.HttpContext?.User.Identity?.Name;
        if (username == null)
        {
            throw new BadRequestException("Could not get username from http context");
        }
        var user = await userRepository.FindByUsernameAsync(username);
        if(user == null)
        {
            throw new NotFoundException($"Username({username}) from http context has no user");
        }
        return user;
    }

    public async Task<string> RegisterUser(UserCredentialsDto credentials)
    {
        if (await userRepository.FindByUsernameAsync(credentials.Username) is not null)
        {
            throw new BadRequestException("Username already in use");
        }

        var hashed = BCrypt.Net.BCrypt.HashPassword(credentials.Username);
        var user = new User(credentials.Username, hashed);
        await userRepository.SaveAsync(user);
        return new JwtSecurityTokenHandler().WriteToken(CreateToken(user));
    }

    public async Task<string> LoginUser(UserCredentialsDto credentials)
    {
        var user = await userRepository.FindByUsernameAsync(credentials.Username);
        if (user == null)
        {
            throw new NotFoundException($"No user has username({credentials.Username})");
        }

        if (!BCrypt.Net.BCrypt.Verify(credentials.Password, user.PasswordHash))
        {
            throw new UnauthorizedException("Invalid credentials");
        }

        return new JwtSecurityTokenHandler().WriteToken(CreateToken(user));
    }

    private JwtSecurityToken CreateToken(User user)
    {
        var claims = new[]
        {
            new Claim(ClaimTypes.Name, user.Username),
            new Claim(ClaimTypes.NameIdentifier, user.Username),
            new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString())
        };

        var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(configuration["JwtSettings:SecretKey"]));
        var credentials = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

        return new JwtSecurityToken(
            issuer: configuration["JwtSettings:Issuer"],
            audience: configuration["JwtSettings:Audience"],
            claims: claims,
            expires: DateTime.Now.AddDays(1),
            signingCredentials: credentials);
    }
}
using Reservation.Server.Features.Users.Entity;

namespace Reservation.Server.Features.Users.Dto;

public class UserDto
{
    public string Username { get; set; }
    public decimal Balance { get; set; }

    public UserDto(User user)
    {
        Username = user.Username;
        Balance = user.Balance;
    }
}
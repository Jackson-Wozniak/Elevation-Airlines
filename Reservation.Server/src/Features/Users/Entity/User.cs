using Reservation.Server.Core.Entity;

namespace Reservation.Server.Features.Users.Entity;

public class User : BaseEntity
{
    public string Username { get; set; }
    public string PasswordHash { get; set; }
    public decimal Balance { get; set; }

    public User(string username, string hashed)
    {
        Username = username;
        PasswordHash = hashed;
        Balance = new decimal(0.0);
    }
}
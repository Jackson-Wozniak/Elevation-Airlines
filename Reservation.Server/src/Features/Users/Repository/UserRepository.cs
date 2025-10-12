using Microsoft.EntityFrameworkCore;
using Reservation.Server.Core.Data;
using Reservation.Server.Features.Users.Entity;

namespace Reservation.Server.Features.Users.Repository;

public class UserRepository(ApplicationDbContext context)
{
    public async Task<User> SaveAsync(User user)
    {
        await context.Users.AddAsync(user);
        await context.SaveChangesAsync();
        return user;
    }
    
    public User Update(User user)
    {
        context.Users.Update(user);
        context.SaveChanges();
        return user;
    }

    public async Task<User?> FindByIdAsync(long id)
    {
        var user = await context.Users.FindAsync(id);
        return user;
    }

    public async Task<User?> FindByUsernameAsync(string username)
    {
        var user = await context.Users
            .SingleOrDefaultAsync(u => u.Username.Equals(username));
        return user;
    }
}
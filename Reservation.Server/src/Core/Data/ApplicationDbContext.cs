using Microsoft.EntityFrameworkCore;
using Reservation.Server.Core.Entity;

namespace Reservation.Server.Core.Data;

public class ApplicationDbContext(
    DbContextOptions<ApplicationDbContext> options) : DbContext(options)
{
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        foreach (var entityType in modelBuilder.Model.GetEntityTypes())
        {
            var property = entityType.FindProperty(nameof(BaseEntity.CreatedAt));
            if (property == null) continue;
            
            modelBuilder.Entity(entityType.ClrType)
                .Property(nameof(BaseEntity.CreatedAt))
                .HasColumnType("TIMESTAMP")
                .HasDefaultValueSql("CURRENT_TIMESTAMP")
                .ValueGeneratedOnAdd();
        }
        
        base.OnModelCreating(modelBuilder);
    }
}
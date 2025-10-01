using backend.Core.Entity;
using backend.Domain.Aircrafts.Entity;
using backend.Domain.Airports.Entity;
using Microsoft.EntityFrameworkCore;

namespace backend.Core.Data;

public class ApplicationDbContext : DbContext
{
    public DbSet<Aircraft> Aircraft { get; set; }
    public DbSet<Airport> Airports { get; set; }
    
    public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
        : base(options){ }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);

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
    }
}
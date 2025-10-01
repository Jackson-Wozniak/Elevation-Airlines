using backend.Core.Entity;
using backend.Domain.aircraft.Entity;
using backend.Domain.airport.Entity;
using backend.Domain.fleet.Entity;
using backend.Domain.flight.Entity;
using backend.Domain.routenetwork.Entity;
using Microsoft.EntityFrameworkCore;

namespace backend.Core.Data;

public class ApplicationDbContext : DbContext
{
    public DbSet<Aircraft> Aircraft { get; set; }
    public DbSet<Airport> Airports { get; set; }
    public DbSet<Plane> Planes { get; set; }
    public DbSet<Flight> Flights { get; set; }
    public DbSet<NetworkedRoute> NetworkedRoutes { get; set; }
    
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

        modelBuilder.Entity<Plane>()
            .HasOne(p => p.Aircraft)
            .WithMany()
            .HasForeignKey("AircraftId");
        
        modelBuilder.Entity<Flight>()
            .HasOne(f => f.Plane)
            .WithMany(p => p.ScheduledFlights)
            .HasForeignKey("PlaneId");

        modelBuilder.Entity<Flight>()
            .HasOne(f => f.FlightPlan)
            .WithOne(p => p.Flight)
            .HasForeignKey<FlightPlan>("FlightId");

        modelBuilder.Entity<FlightPlan>()
            .OwnsOne(f => f.Route, route =>
            {
                route.HasOne(r => r.Departure)
                    .WithMany()
                    .HasForeignKey("DepartureId");
                route.HasOne(r => r.Destination)
                    .WithMany()
                    .HasForeignKey("DestinationId");
            });
        
        modelBuilder.Entity<NetworkedRoute>()
            .OwnsOne(n => n.Route, route =>
            {
                route.HasOne(r => r.Departure)
                    .WithMany()
                    .HasForeignKey("DepartureId");
                route.HasOne(r => r.Destination)
                    .WithMany()
                    .HasForeignKey("DestinationId");
            });
    }
}
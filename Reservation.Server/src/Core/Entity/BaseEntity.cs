namespace Reservation.Server.Core.Entity;

public abstract class BaseEntity
{
    public long Id { get; set; }
    public DateTime CreatedAt { get; set; }
}
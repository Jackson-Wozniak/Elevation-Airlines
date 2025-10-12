namespace Reservation.Server.Features.Airline.Operations.Orchestration;

/*
Flight data is received from the Flight/Airline subscribers consuming the Redis message
 queue. This synchronizer background service runs once daily to ensure all airline-related
 data (flights, status, etc.) is consistent with what was read by the subscriber.
*/
public class AirlineOperationsSynchronizer
{
    
}
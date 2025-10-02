using backend.Core.Utils;
using backend.Domain.airport.Entity;

namespace backend.Domain.airport.Utils;

public static class AirportUtils
{
    private const double EarthRadiusNauticalMiles = 3443.92;
    
    public static double NauticalMilesTo(this Airport departure, Airport destination)
    {
        var departureLat = departure.Latitude.ToRadians();
        var departureLong = departure.Longitude.ToRadians();
        var destinationLat = destination.Latitude.ToRadians();
        var destinationLong = destination.Longitude.ToRadians();

        var differenceLat = departureLat - destinationLat;
        var differenceLong = departureLong - destinationLong;

        var a = Math.Pow(Math.Sin(differenceLat / 2), 2)
                + (Math.Cos(departureLat) * Math.Cos(destinationLat))
                * Math.Pow(Math.Sin(differenceLong / 2), 2);
        var c = 2 * Math.Asin(Math.Sqrt(a));
        return Math.Round(c * EarthRadiusNauticalMiles, 2);
    }
}
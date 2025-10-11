using Airline.Server.Core.Settings;
using Airline.Server.Core.Utils;
using Airline.Server.Domain.airport.Service;
using Airline.Server.Domain.fleet.Entity;
using Airline.Server.Domain.fleet.Repository;
using Airline.Server.Domain.fleet.Service;
using Airline.Server.Domain.flight.Entity;
using Airline.Server.Domain.flight.Service;
using Airline.Server.Domain.routenetwork.Service;
using Airline.Server.Engine.Interface;
using Microsoft.Extensions.Options;
using Route = Airline.Server.Domain.shared.Route;

namespace Airline.Server.Engine.Service;

public class SimpleFlightSchedulerService(
    NetworkedRouteService networkedRouteService,
    FleetService fleetService,
    PlaneRepository planeRepository,
    AirportService airportService,
    FlightService flightService,
    IOptions<SimulationSettings> options,
    ILogger<SimpleFlightSchedulerService> logger) : IFlightSchedulerService
{
    private readonly SimulationSettings _settings = options.Value;
    private static readonly TimeOnly Midnight = TimeOnly.FromTimeSpan(TimeSpan.Zero);
    
    public List<Flight> ScheduleAndSave(DateOnly date)
    {
        return CreateForDate(date, fleetService.LastFlightForPlanes());
    }
    
    public List<Flight> ScheduleAndSave(DateOnly start, DateOnly end)
    {
        Dictionary<Plane, Flight?> fleet = fleetService.LastFlightForPlanes();
        List<Flight> flightSchedule = [];
        var daysBetween = start.DaysUntil(end);
        for (var i = 0; i < daysBetween; i++)
        {
            var flights = CreateForDate(start.AddDays(i), fleet);
            flights.ForEach(f => fleet[f.Plane] = f);
            flightSchedule.AddRange(flights);
        }
        return flightService.SaveFlights(flightSchedule);
    }

    private List<Flight> CreateForDate(DateOnly date, Dictionary<Plane, Flight?> fleet)
    {
        var networkedRoutes = networkedRouteService.GetNetworkedRoutes();
        return fleet.Select(pair =>
            {
                DateTime boardingTime;
                if (pair.Value is null)
                {
                    boardingTime = new DateTime(date, Midnight);
                    return Flight.Create(networkedRoutes.Random().Route, pair.Key,
                        boardingTime, boardingTime.AddMinutes(30));
                }

                var lastLandingTime = pair.Value.ExpectedArrivalTime;
                boardingTime = lastLandingTime.AddMinutes(_settings.LayoverMinutes)
                    .RoundUpToNearestQuarter();
                var route = CreateSubsequentRoute(pair.Value.FlightPlan.Route);
                return Flight.Create(route, pair.Key,
                    boardingTime, boardingTime.AddMinutes(30));
            })
            .ToList();
    }

    private Route CreateSubsequentRoute(Route route)
    {
        if (route.Destination.AirportCode.Equals(_settings.PrimaryHub))
        {
            var randomRoute = networkedRouteService
                .GetNetworkedRoutesFrom(_settings.PrimaryHub).Random();
            return new Route(randomRoute.Route.Departure, randomRoute.Route.Destination);
        }

        return new Route(route.Destination, 
            airportService.FindAirportByCode(_settings.PrimaryHub));
    }
}
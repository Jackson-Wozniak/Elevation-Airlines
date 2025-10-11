using Airline.Server.Core.Data;
using Airline.Server.Core.Exception;
using Airline.Server.Core.Infrastructure.EventQueue;
using Airline.Server.Engine.Initializer;
using Airline.Server.Core.Settings;
using Airline.Server.Domain.aircraft.Service;
using Airline.Server.Domain.airport.Service;
using Airline.Server.Domain.fleet.Factory;
using Airline.Server.Domain.fleet.Repository;
using Airline.Server.Domain.fleet.Service;
using Airline.Server.Domain.flight.Object;
using Airline.Server.Domain.flight.Repository;
using Airline.Server.Domain.flight.Service;
using Airline.Server.Domain.routenetwork.Service;
using Airline.Server.Engine.Orchestration;
using Airline.Server.Engine.Interface;
using Airline.Server.Engine.Service;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddLogging(logging => logging.AddConsole().AddDebug());

builder.Services.Configure<SimulationSettings>(
    builder.Configuration.GetSection("SimulationSettings"));
builder.Services.Configure<InitializerSettings>(
    builder.Configuration.GetSection("InitializerSettings"));

// Add services to the container.
string connectionString = builder.Configuration.GetConnectionString("ElevationAirlinesDbConnection");
builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString)));

builder.Services.AddSingleton<PriorityEventQueue<FlightEvent>>();
builder.Services.AddScoped<AirportService>();
builder.Services.AddScoped<AircraftService>();
builder.Services.AddScoped<PlaneRepository>();
builder.Services.AddScoped<FleetService>();
builder.Services.AddScoped<FleetFactory>();
builder.Services.AddScoped<FlightService>();
builder.Services.AddScoped<FlightRepository>();
builder.Services.AddScoped<FlightEventService>();
builder.Services.AddScoped<NetworkedRouteService>();
builder.Services.AddScoped<INetworkPlanService, SimpleNetworkPlanService>();
builder.Services.AddScoped<IFlightSchedulerService, SimpleFlightSchedulerService>();

builder.Services.AddHostedService<FlightEventProcessor>();
builder.Services.AddHostedService<AirlineBatchProcessingService>();

builder.Services.AddScoped<DatabaseInitializer>();
builder.Services.AddScoped<AirlineInitializer>();

builder.Services.AddControllers(options =>
{
    options.Filters.Add<GlobalExceptionFilter>();
});

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddCors(options =>
{
    options.AddDefaultPolicy(
        policy =>
        {
            policy.AllowAnyOrigin()
                .AllowAnyMethod()
                .AllowAnyHeader();
        });
});

var app = builder.Build();

using (var scope = app.Services.CreateScope())
{
    scope.ServiceProvider.GetRequiredService<DatabaseInitializer>().Initialize();
    scope.ServiceProvider.GetRequiredService<AirlineInitializer>().Initialize();
}

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseCors();

app.UseAuthorization();

app.MapControllers();

app.Run();
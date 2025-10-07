using backend.Core.Data;
using backend.Core.Exception;
using backend.Core.Infrastructure.EventQueue;
using backend.Engine.Initializer;
using backend.Core.Settings;
using backend.Domain.aircraft.Service;
using backend.Domain.airport.Service;
using backend.Domain.fleet.Factory;
using backend.Domain.fleet.Repository;
using backend.Domain.fleet.Service;
using backend.Domain.flight.Object;
using backend.Domain.flight.Repository;
using backend.Domain.flight.Service;
using backend.Domain.routenetwork.Service;
using backend.Engine.Orchestration;
using backend.Engine.Interface;
using backend.Engine.Service;
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
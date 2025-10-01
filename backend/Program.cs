using backend.Core.Data;
using backend.Core.Exception;
using backend.Core.Initializer;
using backend.Core.Settings;
using backend.Domain.aircraft.Service;
using backend.Domain.airport.Service;
using backend.Domain.fleet.Factory;
using backend.Domain.fleet.Service;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

builder.Services.Configure<SimulationSettings>(
    builder.Configuration.GetSection("SimulationSettings"));
builder.Services.Configure<InitializerSettings>(
    builder.Configuration.GetSection("InitializerSettings"));

// Add services to the container.
string connectionString = builder.Configuration.GetConnectionString("ElevationAirlinesDbConnection");
builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString)));

builder.Services.AddScoped<DatabaseInitializer>();
builder.Services.AddScoped<AirlineInitializer>();
builder.Services.AddScoped<AirportService>();
builder.Services.AddScoped<AircraftService>();
builder.Services.AddScoped<PlaneService>();
builder.Services.AddScoped<FleetService>();
builder.Services.AddSingleton<FleetFactory>();

builder.Services.AddControllers(options =>
{
    options.Filters.Add<GlobalExceptionFilter>();
});

// Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi
builder.Services.AddOpenApi();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
}

using (var scope = app.Services.CreateScope())
{
    scope.ServiceProvider.GetRequiredService<DatabaseInitializer>().Initialize();
    scope.ServiceProvider.GetRequiredService<AirlineInitializer>().Initialize();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
namespace backend.Core.Settings;

public class SimulationSettings
{
    public int FleetSize { get; set; } = 20;
    public string PrimaryHub { get; set; } = "";
    public string[] SecondaryHubs { get; set; } = [];
    public int LayoverMinutes { get; set; } = 60;
    public int FlightHoursUntilSafetyInspection { get; set; } = 500;
    public int SafetyInspectionHoursGrounded { get; set; } = 2;
    public int MinorMaintenanceHoursGrounded { get; set; } = 48;
    public int MajorMaintenanceHoursGrounded { get; set; } = 168;
}
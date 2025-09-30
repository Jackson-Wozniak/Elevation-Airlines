namespace backend.Core.Settings;

public class InitializerSettings
{
    public bool ResetStaticDataOnStartup { get; set; } = true;
    public string[] ResetStaticDataTypes { get; set; } = [];
    public bool ResetSimulationState { get; set; } = true;
}
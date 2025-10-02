namespace backend.Core.Settings;

public class InitializerSettings
{
    public bool ResetStaticData { get; set; }
    public string[] ResetStaticDataTypes { get; set; } = [];
    public bool ResetSimulationState { get; set; }
}
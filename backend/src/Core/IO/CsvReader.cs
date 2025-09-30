using System.Reflection;

namespace backend.Core.IO;

public class CsvReader
{
    public static List<string[]> ReadLines(string path)
    {
        Assembly assembly = Assembly.GetExecutingAssembly();
        using var stream = assembly.GetManifestResourceStream(path);
        if (stream == null) throw new FileNotFoundException();
        using var streamReader = new StreamReader(stream);
        return Lines(streamReader)
            .Select(line => line.Split(","))
            .ToList();
    }

    private static IEnumerable<string> Lines(StreamReader streamReader)
    {
        string? line;
        while ((line = streamReader.ReadLine()) != null)
        {
            yield return line;
        }
    }
}
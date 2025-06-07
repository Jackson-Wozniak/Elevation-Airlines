package internal.parser.objects;

public class RunwayInfo {
    private final int lengthFt;
    private final String surface;
    private final boolean hasLights;
    private final boolean isClosed;

    public RunwayInfo(int lengthFt, String surface, boolean hasLights, boolean isClosed){
        this.lengthFt = lengthFt;
        this.surface = surface;
        this.hasLights = hasLights;
        this.isClosed = isClosed;
    }

    public int getLengthFt() {
        return lengthFt;
    }

    public String getSurface() {
        return surface;
    }

    public boolean hasLights() {
        return hasLights;
    }

    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public String toString() {
        return "{ " + lengthFt + "ft " + surface + hasLights + isClosed + " }";
    }
}

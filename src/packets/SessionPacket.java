package packets;


/**
 * Session packet based on https://forums.codemasters.com/topic/44592-f1-2019-udp-specification/
 *
 * @Author Alex Shaw
 */
public class SessionPacket {

    private PacketHeader packetHeader;
    private int weather;
    private int trackTemp;
    private int airTemp;
    private int laps;
    private int trackLength;
    private int sessionType;
    private int trackID;
    private int formula;
    private int sessionTimeLeft;
    private int sessionDuration;
    private int pitSpeedLim;
    private boolean gamePaused;
    private boolean isSpectating;
    private int spectatorIndex;
    private int sliSupport;
    private int numMarshallZones;
    private MarshallZone[] marshallZones;
    private int safteyCarStatus;
    private int networkedGame;


    public SessionPacket(PacketHeader packetHeader, int weather, int trackTemp, int airTemp, int laps, int trackLength,
                         int sessionType, int trackID, int formula, int sessionTimeLeft, int sessionDuration,
                         int pitSpeedLim, boolean gamePaused, boolean isSpectating, int spectatorIndex, int sliSupport,
                         int numMarshallZones, MarshallZone[] marshallZones, int safteyCarStatus, int networkedGame) {
        this.packetHeader = packetHeader;
        this.weather = weather;
        this.trackTemp = trackTemp;
        this.airTemp = airTemp;
        this.laps = laps;
        this.trackLength = trackLength;
        this.sessionType = sessionType;
        this.trackID = trackID;
        this.formula = formula;
        this.sessionTimeLeft = sessionTimeLeft;
        this.sessionDuration = sessionDuration;
        this.pitSpeedLim = pitSpeedLim;
        this.gamePaused = gamePaused;
        this.isSpectating = isSpectating;
        this.spectatorIndex = spectatorIndex;
        this.sliSupport = sliSupport;
        this.numMarshallZones = numMarshallZones;
        this.marshallZones = marshallZones;
        this.safteyCarStatus = safteyCarStatus;
        this.networkedGame = networkedGame;
    }

    public PacketHeader getPacketHeader() {
        return packetHeader;
    }

    public int getWeather() {
        return weather;
    }

    public int getTrackTemp() {
        return trackTemp;
    }

    public int getAirTemp() {
        return airTemp;
    }

    public int getLaps() {
        return laps;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public int getSessionType() {
        return sessionType;
    }

    public int getTrackID() {
        return trackID;
    }

    public int getFormula() {
        return formula;
    }

    public int getSessionTimeLeft() {
        return sessionTimeLeft;
    }

    public int getSessionDuration() {
        return sessionDuration;
    }

    public int getPitSpeedLim() {
        return pitSpeedLim;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public boolean isSpectating() {
        return isSpectating;
    }

    public int getSpectatorIndex() {
        return spectatorIndex;
    }

    public int getSliSupport() {
        return sliSupport;
    }

    public int getNumMarshallZones() {
        return numMarshallZones;
    }

    public MarshallZone[] getMarshallZones() {
        return marshallZones;
    }

    public int getSafteyCarStatus() {
        return safteyCarStatus;
    }

    public int getNetworkedGame() {
        return networkedGame;
    }
}

class MarshallZone{
    private float zoneStart;
    private int zoneFlag;

    public MarshallZone(float zoneStart, int zoneFlag) {
        this.zoneStart = zoneStart;
        this.zoneFlag = zoneFlag;
    }

    public float getZoneStart() {
        return zoneStart;
    }

    public int getZoneFlag() {
        return zoneFlag;
    }
}

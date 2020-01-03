package uk.co.alexks.jeff2.packets;

import uk.co.alexks.jeff2.TelemetryListener;

import java.nio.ByteBuffer;
import java.util.Arrays;

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

    public static SessionPacket createSessionPacket(byte[] packet, PacketHeader header){
        ByteBuffer bb = ByteBuffer.wrap(packet);
        int weather = TelemetryListener.uint8ToInt(bb.get());
        int trackTemp = bb.get();
        int airTemp = bb.get();
        int laps = TelemetryListener.uint8ToInt(bb.get());
        int trackLength = TelemetryListener.uint16ToInt(bb.get(), bb.get());
        int sessionType = TelemetryListener.uint8ToInt(bb.get());
        int trackID = bb.get();
        int formula = TelemetryListener.uint8ToInt(bb.get());
        int sessionTimeLeft = TelemetryListener.uint16ToInt(bb.get(), bb.get());
        int sessionDuration = TelemetryListener.uint16ToInt(bb.get(), bb.get());
        int pitSpeedLim = TelemetryListener.uint8ToInt(bb.get());
        boolean gamePaused = TelemetryListener.uint8ToInt(bb.get()) == 1;
        boolean isSpectating = TelemetryListener.uint8ToInt(bb.get()) == 1;;
        int spectatorIndex = TelemetryListener.uint8ToInt(bb.get());;
        int sliSupport = TelemetryListener.uint8ToInt(bb.get());;
        int numMarshallZones = TelemetryListener.uint8ToInt(bb.get());;
        MarshallZone[] marshallZones = new MarshallZone[numMarshallZones];
        for(int i = 0; i < numMarshallZones; i++){
            float start = bb.getFloat();
            int flag = bb.get();
            marshallZones[i] = new MarshallZone(start, flag);
        }
        int safteyCarStatus = TelemetryListener.uint8ToInt(bb.get());;
        int networkedGame = TelemetryListener.uint8ToInt(bb.get());;
        return new SessionPacket(header, weather, trackTemp, airTemp, laps, trackLength, sessionType, trackID, formula,
                sessionTimeLeft, sessionDuration, pitSpeedLim, gamePaused, isSpectating, spectatorIndex, sliSupport,
                numMarshallZones, marshallZones, safteyCarStatus, networkedGame);
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

    @Override
    public String toString() {
        return "SessionPacket{" +
                "packetHeader=" + packetHeader.toString() +
                ", weather=" + weather +
                ", trackTemp=" + trackTemp +
                ", airTemp=" + airTemp +
                ", laps=" + laps +
                ", trackLength=" + trackLength +
                ", sessionType=" + sessionType +
                ", trackID=" + trackID +
                ", formula=" + formula +
                ", sessionTimeLeft=" + sessionTimeLeft +
                ", sessionDuration=" + sessionDuration +
                ", pitSpeedLim=" + pitSpeedLim +
                ", gamePaused=" + gamePaused +
                ", isSpectating=" + isSpectating +
                ", spectatorIndex=" + spectatorIndex +
                ", sliSupport=" + sliSupport +
                ", numMarshallZones=" + numMarshallZones +
                ", marshallZones=" + Arrays.toString(marshallZones) +
                ", safteyCarStatus=" + safteyCarStatus +
                ", networkedGame=" + networkedGame +
                '}';
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

    @Override
    public String toString() {
        return "MarshallZone{" +
                "zoneStart=" + zoneStart +
                ", zoneFlag=" + zoneFlag +
                '}';
    }
}

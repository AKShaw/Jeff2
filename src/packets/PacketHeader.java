package packets;

/**
 * Packet header based on https://forums.codemasters.com/topic/44592-f1-2019-udp-specification/
 *
 * @Author Alex Shaw
 */
public class PacketHeader {
    private int packetFormat;
    private int gameMajorVersion;
    private int gameMinorVersion;
    private int packetVersion;
    private int packetID;
    private long sessionUID;
    private float sessionTime;
    private int frameID;
    private int playerCarIndex;

    public PacketHeader(int packetFormat, int gameMajorVersion, int gameMinorVersion, int packetVersion, int packetID,
                        long sessionUID, float sessionTime, int frameID, int playerCarIndex) {
        this.packetFormat = packetFormat;
        this.gameMajorVersion = gameMajorVersion;
        this.gameMinorVersion = gameMinorVersion;
        this.packetVersion = packetVersion;
        this.packetID = packetID;
        this.sessionUID = sessionUID;
        this.sessionTime = sessionTime;
        this.frameID = frameID;
        this.playerCarIndex = playerCarIndex;
    }

    public int getPacketFormat() {
        return packetFormat;
    }

    public int getGameMajorVersion() {
        return gameMajorVersion;
    }

    public int getGameMinorVersion() {
        return gameMinorVersion;
    }

    public int getPacketVersion() {
        return packetVersion;
    }

    public int getPacketID(){
        return packetID;
    }

    public long getSessionUID() {
        return sessionUID;
    }

    public float getSessionTime() {
        return sessionTime;
    }

    public int getFrameID() {
        return frameID;
    }

    public int getPlayerCarIndex() {
        return playerCarIndex;
    }
}

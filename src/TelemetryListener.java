import packets.PacketHeader;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * Listens for the UDP telemetry from the game and decodes it
 *
 * @Author Alex Shaw
 */
public class TelemetryListener implements Runnable {
    private int PORT = 20777;
    private final int MAX_BUFFER_SIZE = 2048;
    private FullSession fullSession;
    private DatagramSocket ds;
    private DatagramPacket dp;
    private List<PacketHeader> temp = new ArrayList();

    /**
     * Initialise sockets and packets
     */
    public TelemetryListener(FullSession fs){
        this.fullSession = fs;
        try {
            ds = new DatagramSocket(PORT);
            byte[] buffer = new byte[MAX_BUFFER_SIZE];
            dp = new DatagramPacket(buffer, buffer.length);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Receive the data from the UDP packet and decode it. Once decoded, create an object for that packet
     * and store it in the FullSession instance.
     */
    public void run(){
        try {
            while (true) {
                ds.receive(dp);
                byte[] data = dp.getData();
                ByteBuffer bb = ByteBuffer.wrap(data);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                while(bb.hasRemaining()){
                    //Create packet header, then use that to create the other rest of the packet.
                    int packetFormat = uint16ToInt(bb.get(), bb.get());
                    int gameMajorVersion = uint8ToInt(bb.get());
                    int gameMinorVersion = uint8ToInt(bb.get());
                    int packetVersion = uint8ToInt(bb.get());
                    int packetID = uint8ToInt(bb.get());
                    long sessionUID = bb.getLong();
                    float sessionTime = bb.getFloat();
                    int frameID = bb.getInt();
                    int playerCarIndex = uint8ToInt(bb.get());
                    PacketHeader ph = new PacketHeader(packetFormat, gameMajorVersion, gameMinorVersion, packetVersion,
                            packetID, sessionUID, sessionTime, frameID, playerCarIndex);
                    //TODO: Create the rest of the packet based on the packet ID
                }
                dp.setLength(MAX_BUFFER_SIZE);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Convert a 16 bit unsigned integer to an integer (e.g: 11111111 -> 255 not -1)
     * @param b The first byte
     * @param c The second byte
     * @return The value of those bytes
     */
    private int uint16ToInt(byte b, byte c){
        int n = 0xFF & b;
        int m = 0xFF & c;
        return n*256+m;
    }

    /**
     * Convert a 8 bit unsigned integer to an integer.
     * @param b
     * @return
     */
    private int uint8ToInt(byte b) {
        return 0xFF & b;
    }

}

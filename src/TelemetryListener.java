import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Listens for the UDP telemetry from the game and decodes it
 *
 * @Author Alex Shaw
 */
public class TelemetryListener implements Runnable {
    private int PORT = 20777;
    private int MAX_BUFFER_SIZE = 2048;
    private FullSession fullSession;
    private DatagramSocket ds;
    private DatagramPacket dp;

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
                bb = bb.order(ByteOrder.LITTLE_ENDIAN);
                while(bb.hasRemaining()){
                    //Create packet header, then use that to create the other rest of the packet.
                    //TODO: Convert the bytes from bb from uintX to int etc, create packet header object.
                    int packetFormat;
                    int gameMajorVersion;
                    int gameMinorVersion;
                    int packetVersion;
                    int packetID;
                    long sessionUID;
                    float sessionTime;
                    int frameID;
                    int playerCarIndex;
                }
                dp.setLength(MAX_BUFFER_SIZE);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}

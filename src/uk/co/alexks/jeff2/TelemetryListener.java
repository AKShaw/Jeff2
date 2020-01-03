package uk.co.alexks.jeff2;

import uk.co.alexks.jeff2.packets.*;

import java.io.*;
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
    private File saveLocation = null;
    private BufferedWriter bufferedWriter;

    /**
     * Initialise sockets and uk.co.alexks.jeff2.packets
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

    public TelemetryListener(FullSession fs, File saveLocation){
        this.fullSession = fs;
        this.saveLocation = saveLocation;
        try {
            FileOutputStream fos = new FileOutputStream(saveLocation);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
            ds = new DatagramSocket(PORT);
            byte[] buffer = new byte[MAX_BUFFER_SIZE];
            dp = new DatagramPacket(buffer, buffer.length);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Receive the data from the UDP packet and decode it. Once decoded, create an object for that packet
     * and store it in the uk.co.alexks.jeff2.FullSession instance.
     */
    public void run(){
        try {
            while (true) {
                ds.receive(dp);
                byte[] data = dp.getData();
                ByteBuffer bb = ByteBuffer.wrap(data);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                bb.position(0);
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

                //Depending on the packet type, create a packet object
                byte[] packet;
                switch(packetID){
                    case 0:
                        //Motion packets
                        break;
                    case 1:
                        //Session packets
                        packet = new byte[149];
                        bb.get(packet);
                        //TODO: Neither frame or session time resets when a flashback occurs, find another way to track them.
                        SessionPacket sessionPacket = SessionPacket.createSessionPacket(packet, ph);
                        fullSession.addToSessionLog(frameID, sessionPacket);
                        if (saveLocation!=null) {savePacket(sessionPacket);}
                        break;
                    case 2:
                        //Lap data packets
                        packet = new byte[843];
                        bb.get(packet);
                        LapDataPacket lapDataPacket = LapDataPacket.createLapDataPacket(packet, ph);
                        fullSession.addToLapDataLog(frameID, lapDataPacket);
                        if (saveLocation!=null) {savePacket(lapDataPacket);}
                        break;
                    case 3:
                        //Event packets
                        break;
                    case 4:
                        //Participants packets
                        break;
                    case 5:
                        //Car packets
                        break;
                    case 6:
                        //Car telemetry packets
                        packet = new byte[1347];
                        bb.get(packet);
                        CarTelemetryPacket carTelemPacket = CarTelemetryPacket.createCarTelemetryPacket(packet, ph);
                        fullSession.addToCarTelemetryLog(frameID, carTelemPacket);
                        if (saveLocation!=null) {savePacket(carTelemPacket);}
                        break;
                    case 7:
                        //Car status packets
                        packet = new byte[1143];
                        bb.get(packet);
                        CarStatusPacket carStatusPacket = CarStatusPacket.createCarStatusPacket(packet, ph);
                        fullSession.addToCarStatusLog(frameID, carStatusPacket);
                        if (saveLocation!=null) {savePacket(carStatusPacket);}
                        break;
                    default:
                        break;
                }

                dp.setLength(MAX_BUFFER_SIZE);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void savePacket(Object packet){
        try {
            bufferedWriter.write(packet.toString());
            bufferedWriter.newLine();
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
    public static int uint16ToInt(byte b, byte c){
        int n = 0xFF & b;
        int m = 0xFF & c;
        return n*256+m;
    }

    /**
     * Convert a 8 bit unsigned integer to an integer.
     * @param b
     * @return
     */
    public static int uint8ToInt(byte b) {
        return 0xFF & b;
    }
}

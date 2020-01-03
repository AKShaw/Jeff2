package uk.co.alexks.jeff2.packets;

import uk.co.alexks.jeff2.TelemetryListener;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Lap data packet based on https://forums.codemasters.com/topic/44592-f1-2019-udp-specification/
 *
 * @Author Alex Shaw
 */
public class LapDataPacket{

    private PacketHeader header;
    private LapData[] lapDatas;

    public LapDataPacket(PacketHeader header, LapData[] lds){
        this.header = header;
        this.lapDatas = lds;
    }

    /**
     * Create the lapdata packet from the given byte array and header
     * @param packet The packet data
     * @param header packet header
     * @return
     */
    public static LapDataPacket createLapDataPacket(byte[] packet, PacketHeader header){
        ByteBuffer bb = ByteBuffer.wrap(packet);
        LapData[] lapDatas = new LapData[20];
        for(int i = 0; i < 20; i++) {
            float lastLapTime = bb.getFloat();
            float currentLapTime = bb.getFloat();
            float bestLapTime = bb.getFloat();
            float sectorOneTime = bb.getFloat();
            float sectorTwoTime = bb.getFloat();
            float lapDistance = bb.getFloat();
            float totalDistance = bb.getFloat();
            float safteyCarDelta = bb.getFloat();
            int carPosition = TelemetryListener.uint8ToInt(bb.get());
            int lapNum = TelemetryListener.uint8ToInt(bb.get());
            int pitStatus = TelemetryListener.uint8ToInt(bb.get());
            int sector = TelemetryListener.uint8ToInt(bb.get());
            boolean lapValid = TelemetryListener.uint8ToInt(bb.get()) == 1;
            int penalties = TelemetryListener.uint8ToInt(bb.get());
            int gridPosition = TelemetryListener.uint8ToInt(bb.get());
            int driverStatus = TelemetryListener.uint8ToInt(bb.get());
            int resultsStatus = TelemetryListener.uint8ToInt(bb.get());
            lapDatas[i] = new LapData(lastLapTime, currentLapTime, bestLapTime, sectorOneTime, sectorTwoTime, lapDistance,
                    totalDistance, safteyCarDelta, carPosition, lapNum, pitStatus, sector, lapValid, penalties, gridPosition,
                    driverStatus, resultsStatus);
        }
        return new LapDataPacket(header, lapDatas);
    }

    public PacketHeader getHeader() {
        return header;
    }

    public LapData[] getLapDatas() {
        return lapDatas;
    }

    @Override
    public String toString() {
        return "LapDataPacket{" +
                "header=" + header +
                ", lapDatas=" + Arrays.toString(lapDatas) +
                '}';
    }
}

class LapData {

    private float lastLapTime;
    private float currentLapTime;
    private float bestLapTime;
    private float sectorOneTime;
    private float sectorTwoTime;
    private float lapDistance;
    private float totalDistance;
    private float safteyCarDelta;
    private int carPosition;
    private int lapNum;
    private int pitStatus;
    private int sector;
    private boolean lapValid;
    private int penalties;
    private int gridPosition;
    private int driverStatus;
    private int resultsStatus;

    public LapData(float lastLapTime, float currentLapTime, float bestLapTime, float sectorOneTime,
                   float sectorTwoTime, float lapDistance, float totalDistance, float safteyCarDelta,
                   int carPosition, int lapNum, int pitStatus, int sector, boolean lapValid, int penalties,
                   int gridPosition, int driverStatus, int resultsStatus) {
        this.lastLapTime = lastLapTime;
        this.currentLapTime = currentLapTime;
        this.bestLapTime = bestLapTime;
        this.sectorOneTime = sectorOneTime;
        this.sectorTwoTime = sectorTwoTime;
        this.lapDistance = lapDistance;
        this.totalDistance = totalDistance;
        this.safteyCarDelta = safteyCarDelta;
        this.carPosition = carPosition;
        this.lapNum = lapNum;
        this.pitStatus = pitStatus;
        this.sector = sector;
        this.lapValid = lapValid;
        this.penalties = penalties;
        this.gridPosition = gridPosition;
        this.driverStatus = driverStatus;
        this.resultsStatus = resultsStatus;
    }

    public float getLastLapTime() {
        return lastLapTime;
    }

    public float getCurrentLapTime() {
        return currentLapTime;
    }

    public float getBestLapTime() {
        return bestLapTime;
    }

    public float getSectorOneTime() {
        return sectorOneTime;
    }

    public float getSectorTwoTime() {
        return sectorTwoTime;
    }

    public float getLapDistance() {
        return lapDistance;
    }

    public float getTotalDistance() {
        return totalDistance;
    }

    public float getSafteyCarDelta() {
        return safteyCarDelta;
    }

    public int getCarPosition() {
        return carPosition;
    }

    public int getLapNum() {
        return lapNum;
    }

    public int getPitStatus() {
        return pitStatus;
    }

    public int getSector() {
        return sector;
    }

    public boolean isLapValid() {
        return lapValid;
    }

    public int getPenalties() {
        return penalties;
    }

    public int getGridPosition() {
        return gridPosition;
    }

    public int getDriverStatus() {
        return driverStatus;
    }

    public int getResultsStatus() {
        return resultsStatus;
    }

    @Override
    public String toString() {
        return "LapData{" +
                "lastLapTime=" + lastLapTime +
                ", currentLapTime=" + currentLapTime +
                ", bestLapTime=" + bestLapTime +
                ", sectorOneTime=" + sectorOneTime +
                ", sectorTwoTime=" + sectorTwoTime +
                ", lapDistance=" + lapDistance +
                ", totalDistance=" + totalDistance +
                ", safteyCarDelta=" + safteyCarDelta +
                ", carPosition=" + carPosition +
                ", lapNum=" + lapNum +
                ", pitStatus=" + pitStatus +
                ", sector=" + sector +
                ", lapValid=" + lapValid +
                ", penalties=" + penalties +
                ", gridPosition=" + gridPosition +
                ", driverStatus=" + driverStatus +
                ", resultsStatus=" + resultsStatus +
                '}';
    }
}

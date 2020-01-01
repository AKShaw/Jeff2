package packets;

/**
 * Lap data packet based on https://forums.codemasters.com/topic/44592-f1-2019-udp-specification/
 *
 * @Author Alex Shaw
 */
public class LapDataPacket {

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

    public LapDataPacket(float lastLapTime, float currentLapTime, float bestLapTime, float sectorOneTime,
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
}

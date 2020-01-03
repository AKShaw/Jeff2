package uk.co.alexks.jeff2.packets;

import uk.co.alexks.jeff2.TelemetryListener;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Car status packet based on https://forums.codemasters.com/topic/44592-f1-2019-udp-specification/
 *
 * @Author Alex Shaw
 */
public class CarStatusPacket {

    private PacketHeader packetHeader;
    private CarStatusData[] carStatusDatas;

    public CarStatusPacket(PacketHeader packetHeader, CarStatusData[] carStatusDatas) {
        this.packetHeader = packetHeader;
        this.carStatusDatas = carStatusDatas;
    }

    public static CarStatusPacket createCarStatusPacket(byte[] packet, PacketHeader header){
        ByteBuffer bb = ByteBuffer.wrap(packet);
        CarStatusData[] csd = new CarStatusData[20];
        for(int i = 0; i < 20; i++){
            int tractionControlLevel = TelemetryListener.uint8ToInt(bb.get());
            boolean absEnabled = TelemetryListener.uint8ToInt(bb.get())==1;
            int fuelMix = TelemetryListener.uint8ToInt(bb.get());
            int frontBrakeBias = TelemetryListener.uint8ToInt(bb.get());
            boolean pitLimEnabled = TelemetryListener.uint8ToInt(bb.get())==1;
            float fuelInTank = bb.getFloat();
            float fuelCapacity = bb.getFloat();
            float fuelRemainingLaps = bb.get();
            int maxRPM = TelemetryListener.uint16ToInt(bb.get(), bb.get());
            int idleRPM = TelemetryListener.uint16ToInt(bb.get(), bb.get());
            int maxGears = TelemetryListener.uint8ToInt(bb.get());
            boolean drsAllowed = TelemetryListener.uint8ToInt(bb.get())==1;
            int[] tyreWears = new int[4];
            for(int j = 0; j<4; j++){
                tyreWears[j] = TelemetryListener.uint8ToInt(bb.get());
            }

            int actualTyreCompound = TelemetryListener.uint8ToInt(bb.get());
            int visualTyreCompound = TelemetryListener.uint8ToInt(bb.get());
            int[] tyreDamages = new int[4];
            for(int j = 0; j<4; j++){
                tyreDamages[j] = TelemetryListener.uint8ToInt(bb.get());
            }

            int frontLeftWingDamage = TelemetryListener.uint8ToInt(bb.get());
            int frontRightWingDamage = TelemetryListener.uint8ToInt(bb.get());
            int rearWingDamage = TelemetryListener.uint8ToInt(bb.get());
            int engineDamage = TelemetryListener.uint8ToInt(bb.get());
            int gearboxDamage = TelemetryListener.uint8ToInt(bb.get());
            int vehicleFIAFlag = bb.get();
            float ersStoredEnergy = bb.getFloat();
            int ersDeployMode = TelemetryListener.uint8ToInt(bb.get());
            float ersHarvestedThisLapMGUK = bb.getFloat();
            float ersHarvestedThisLapMGUH = bb.getFloat();
            float ersDeployedThisLap = bb.getFloat();
            csd[i] = new CarStatusData(tractionControlLevel, absEnabled, fuelMix, frontBrakeBias, pitLimEnabled,
                    fuelInTank, fuelCapacity, fuelRemainingLaps, maxRPM, idleRPM, maxGears, drsAllowed, tyreWears,
                    actualTyreCompound, visualTyreCompound, tyreDamages, frontLeftWingDamage, frontRightWingDamage,
                    rearWingDamage, engineDamage, gearboxDamage, vehicleFIAFlag, ersStoredEnergy, ersDeployMode, ersHarvestedThisLapMGUK,
                    ersHarvestedThisLapMGUH, ersDeployedThisLap);
        }
        return new CarStatusPacket(header, csd);
    }

    public PacketHeader getPacketHeader() {
        return packetHeader;
    }

    public CarStatusData[] getCarStatusDatas() {
        return carStatusDatas;
    }

    @Override
    public String toString() {
        return "CarStatusPacket{" +
                "packetHeader=" + packetHeader +
                ", carStatusDatas=" + Arrays.toString(carStatusDatas) +
                '}';
    }
}

class CarStatusData{
    private int tractionControlLevel;
    private boolean absEnabled;
    private int fuelMix;
    private int frontBrakeBias;
    private boolean pitLimEnabled;
    private float fuelInTank;
    private float fuelCapacity;
    private float fuelRemainingLaps;
    private int maxRPM;
    private int idleRPM;
    private int maxGears;
    private boolean drsAllowed;
    private int[] tyreWears;
    private int actualTyreCompound;
    private int visualTyreCompound;
    private int[] tyreDamages;
    private int frontLeftWingDamage;
    private int frontRightWingDamage;
    private int rearWingDamage;
    private int engineDamage;
    private int gearboxDamage;
    private int vehicleFIAFlag;
    private float ersStoredEnergy;
    private int ersDeployMode;
    private float ersHarvestedThisLapMGUK;
    private float ersHarvestedThisLapMGUH;
    private float ersDeployedThisLap;

    public CarStatusData(int tractionControlLevel, boolean absEnabled, int fuelMix, int frontBrakeBias,
                         boolean pitLimEnabled, float fuelInTank, float fuelCapacity, float fuelRemainingLaps,
                         int maxRPM, int idleRPM, int maxGears, boolean drsAllowed, int[] tyreWears, int actualTyreCompound,
                         int visualTyreCompound, int[] tyreDamages, int frontLeftWingDamage, int frontRightWingDamage,
                         int rearWingDamage, int engineDamage, int gearboxDamage, int vehicleFIAFlag,
                         float ersStoredEnergy, int ersDeployMode, float ersHarvestedThisLapMGUK,
                         float ersHarvestedThisLapMGUH, float ersDeployedThisLap) {
        this.tractionControlLevel = tractionControlLevel;
        this.absEnabled = absEnabled;
        this.fuelMix = fuelMix;
        this.frontBrakeBias = frontBrakeBias;
        this.pitLimEnabled = pitLimEnabled;
        this.fuelInTank = fuelInTank;
        this.fuelCapacity = fuelCapacity;
        this.fuelRemainingLaps = fuelRemainingLaps;
        this.maxRPM = maxRPM;
        this.idleRPM = idleRPM;
        this.maxGears = maxGears;
        this.drsAllowed = drsAllowed;
        this.tyreWears = tyreWears;
        this.actualTyreCompound = actualTyreCompound;
        this.visualTyreCompound = visualTyreCompound;
        this.tyreDamages = tyreDamages;
        this.frontLeftWingDamage = frontLeftWingDamage;
        this.frontRightWingDamage = frontRightWingDamage;
        this.rearWingDamage = rearWingDamage;
        this.engineDamage = engineDamage;
        this.gearboxDamage = gearboxDamage;
        this.vehicleFIAFlag = vehicleFIAFlag;
        this.ersStoredEnergy = ersStoredEnergy;
        this.ersDeployMode = ersDeployMode;
        this.ersHarvestedThisLapMGUK = ersHarvestedThisLapMGUK;
        this.ersHarvestedThisLapMGUH = ersHarvestedThisLapMGUH;
        this.ersDeployedThisLap = ersDeployedThisLap;
    }

    public int getTractionControlLevel() {
        return tractionControlLevel;
    }

    public boolean isAbsEnabled() {
        return absEnabled;
    }

    public int getFuelMix() {
        return fuelMix;
    }

    public int getFrontBrakeBias() {
        return frontBrakeBias;
    }

    public boolean isPitLimEnabled() {
        return pitLimEnabled;
    }

    public float getFuelInTank() {
        return fuelInTank;
    }

    public float getFuelCapacity() {
        return fuelCapacity;
    }

    public float getFuelRemainingLaps() {
        return fuelRemainingLaps;
    }

    public int getMaxRPM() {
        return maxRPM;
    }

    public int getIdleRPM() {
        return idleRPM;
    }

    public int getMaxGears() {
        return maxGears;
    }

    public boolean getDrsAllowed() {
        return drsAllowed;
    }

    public int[] getTyreWears() {
        return tyreWears;
    }

    public int getActualTyreCompound() {
        return actualTyreCompound;
    }

    public int getVisualTyreCompound() {
        return visualTyreCompound;
    }

    public int[] getTyreDamages() {
        return tyreDamages;
    }

    public int getFrontLeftWingDamage() {
        return frontLeftWingDamage;
    }

    public int getFrontRightWingDamage() {
        return frontRightWingDamage;
    }

    public int getRearWingDamage() {
        return rearWingDamage;
    }

    public int getEngineDamage() {
        return engineDamage;
    }

    public int getGearboxDamage() {
        return gearboxDamage;
    }

    public int getVehicleFIAFlag() {
        return vehicleFIAFlag;
    }

    public float getErsStoredEnergy() {
        return ersStoredEnergy;
    }

    public int getErsDeployMode() {
        return ersDeployMode;
    }

    public float getErsHarvestedThisLapMGUK() {
        return ersHarvestedThisLapMGUK;
    }

    public float getErsHarvestedThisLapMGUH() {
        return ersHarvestedThisLapMGUH;
    }

    public float getErsDeployedThisLap() {
        return ersDeployedThisLap;
    }

    @Override
    public String toString() {
        return "CarStatusData{" +
                "tractionControlLevel=" + tractionControlLevel +
                ", absEnabled=" + absEnabled +
                ", fuelMix=" + fuelMix +
                ", frontBrakeBias=" + frontBrakeBias +
                ", pitLimEnabled=" + pitLimEnabled +
                ", fuelInTank=" + fuelInTank +
                ", fuelCapacity=" + fuelCapacity +
                ", fuelRemainingLaps=" + fuelRemainingLaps +
                ", maxRPM=" + maxRPM +
                ", idleRPM=" + idleRPM +
                ", maxGears=" + maxGears +
                ", drsAllowed=" + drsAllowed +
                ", tyreWears=" + Arrays.toString(tyreWears) +
                ", actualTyreCompound=" + actualTyreCompound +
                ", visualTyreCompound=" + visualTyreCompound +
                ", tyreDamages=" + Arrays.toString(tyreDamages) +
                ", frontLeftWingDamage=" + frontLeftWingDamage +
                ", frontRightWingDamage=" + frontRightWingDamage +
                ", rearWingDamage=" + rearWingDamage +
                ", engineDamage=" + engineDamage +
                ", gearboxDamage=" + gearboxDamage +
                ", vehicleFIAFlag=" + vehicleFIAFlag +
                ", ersStoredEnergy=" + ersStoredEnergy +
                ", ersDeployMode=" + ersDeployMode +
                ", ersHarvestedThisLapMGUK=" + ersHarvestedThisLapMGUK +
                ", ersHarvestedThisLapMGUH=" + ersHarvestedThisLapMGUH +
                ", ersDeployedThisLap=" + ersDeployedThisLap +
                '}';
    }
}

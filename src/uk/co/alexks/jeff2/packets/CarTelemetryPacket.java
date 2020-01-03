package uk.co.alexks.jeff2.packets;

import org.w3c.dom.html.HTMLTextAreaElement;
import uk.co.alexks.jeff2.TelemetryListener;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Car telemetry packet based on https://forums.codemasters.com/topic/44592-f1-2019-udp-specification/
 *
 * @Author Alex Shaw
 */
public class CarTelemetryPacket {

    private PacketHeader packetHeader;
    private CarTelemetryData[] carTelemetryData;
    private int buttonStatus;

    public CarTelemetryPacket(PacketHeader packetHeader, CarTelemetryData[] carTelemetryData, int buttonStatus) {
        this.packetHeader = packetHeader;
        this.carTelemetryData = carTelemetryData;
        this.buttonStatus = buttonStatus;
    }

    public static CarTelemetryPacket createCarTelemetryPacket(byte[] packet, PacketHeader header){
        ByteBuffer bb = ByteBuffer.wrap(packet);
        CarTelemetryData[] ctd = new CarTelemetryData[20];
        for(int i = 0; i < 20; i++){
            int speedKPH = TelemetryListener.uint16ToInt(bb.get(), bb.get());
            float throttle = bb.getFloat();
            float steeringInput = bb.getFloat();
            float brake = bb.getFloat();
            int clutch = TelemetryListener.uint8ToInt(bb.get());
            int gear = bb.get();
            int rpm = TelemetryListener.uint16ToInt(bb.get(), bb.get());
            boolean hasDRS = TelemetryListener.uint8ToInt(bb.get())==1;
            int revLightsPercent = TelemetryListener.uint8ToInt(bb.get());
            int[] brakeTemps = new int[4];
            for(int j = 0; j < 4; j++){
                brakeTemps[j] = TelemetryListener.uint16ToInt(bb.get(), bb.get());
            }

            int[] tyreSurfaceTemps = new int[4];
            for(int j = 0; j < 4; j++){
                tyreSurfaceTemps[j] = TelemetryListener.uint16ToInt(bb.get(), bb.get());
            }

            int[] tyreCarcassTemps = new int[4];
            for(int j = 0; j < 4; j++){
                tyreCarcassTemps[j] = TelemetryListener.uint16ToInt(bb.get(), bb.get());
            }

            int engineTemp = TelemetryListener.uint16ToInt(bb.get(), bb.get());
            float[] tyrePressures = new float[4];
            for(int j = 0; j < 4; j++){
                tyrePressures[j] = bb.getFloat();
            }

            int[] surfaceTypes = new int[4];
            for(int j = 0; j < 4; j++){
                brakeTemps[j] = TelemetryListener.uint8ToInt(bb.get());
            }

            ctd[i] = new CarTelemetryData(speedKPH, throttle, steeringInput, brake, clutch, gear, rpm, hasDRS,
                    revLightsPercent, brakeTemps, tyreSurfaceTemps, tyreCarcassTemps, engineTemp, tyrePressures, surfaceTypes);
        }
        int buttonStatus = bb.getInt();
        return new CarTelemetryPacket(header, ctd, buttonStatus);
    }

    public PacketHeader getPacketHeader() {
        return packetHeader;
    }

    public CarTelemetryData[] getCarTelemetryData() {
        return carTelemetryData;
    }

    public int getButtonStatus() {
        return buttonStatus;
    }

    @Override
    public String toString() {
        return "CarTelemetryPacket{" +
                "packetHeader=" + packetHeader +
                ", carTelemetryData=" + Arrays.toString(carTelemetryData) +
                ", buttonStatus=" + buttonStatus +
                '}';
    }
}

class CarTelemetryData{
    private int speedKPH;
    private float throttle;
    private float steeringInput;
    private float brake;
    private int clutch;
    private int gear;
    private int rpm;
    private boolean hasDRS;
    private int revLightsPercent;
    private int[] brakeTemps;
    private int[] tyreSurfaceTemps;
    private int[] tyreCarcassTemps;
    private int engineTemp;
    private float[] tyrePressures;
    private int[] surfaceTypes;

    public CarTelemetryData(int speedKPH, float throttle, float steeringInput, float brake, int clutch, int gear,
                            int rpm, boolean hasDRS, int revLightsPercent, int[] brakeTemps, int[] tyreSurfaceTemps,
                            int[] tyreCarcassTemps, int engineTemp, float[] tyrePressures, int[] surfaceTypes) {
        this.speedKPH = speedKPH;
        this.throttle = throttle;
        this.steeringInput = steeringInput;
        this.brake = brake;
        this.clutch = clutch;
        this.gear = gear;
        this.rpm = rpm;
        this.hasDRS = hasDRS;
        this.revLightsPercent = revLightsPercent;
        this.brakeTemps = brakeTemps;
        this.tyreSurfaceTemps = tyreSurfaceTemps;
        this.tyreCarcassTemps = tyreCarcassTemps;
        this.engineTemp = engineTemp;
        this.tyrePressures = tyrePressures;
        this.surfaceTypes = surfaceTypes;
    }

    public int getSpeedKPH() {
        return speedKPH;
    }

    public float getThrottle() {
        return throttle;
    }

    public float getSteeringInput() {
        return steeringInput;
    }

    public float getBrake() {
        return brake;
    }

    public int getClutch() {
        return clutch;
    }

    public int getGear() {
        return gear;
    }

    public int getRpm() {
        return rpm;
    }

    public boolean isHasDRS() {
        return hasDRS;
    }

    public int getRevLightsPercent() {
        return revLightsPercent;
    }

    public int[] getBrakeTemps() {
        return brakeTemps;
    }

    public int[] getTyreSurfaceTemps() {
        return tyreSurfaceTemps;
    }

    public int[] getTyreCarcassTemps() {
        return tyreCarcassTemps;
    }

    public int getEngineTemp() {
        return engineTemp;
    }

    public float[] getTyrePressures() {
        return tyrePressures;
    }

    public int[] getSurfaceTypes() {
        return surfaceTypes;
    }

    @Override
    public String toString() {
        return "CarTelemetryData{" +
                "speedKPH=" + speedKPH +
                ", throttle=" + throttle +
                ", steeringInput=" + steeringInput +
                ", brake=" + brake +
                ", clutch=" + clutch +
                ", gear=" + gear +
                ", rpm=" + rpm +
                ", hasDRS=" + hasDRS +
                ", revLightsPercent=" + revLightsPercent +
                ", brakeTemps=" + Arrays.toString(brakeTemps) +
                ", tyreSurfaceTemps=" + Arrays.toString(tyreSurfaceTemps) +
                ", tyreCarcassTemps=" + Arrays.toString(tyreCarcassTemps) +
                ", engineTemp=" + engineTemp +
                ", tyrePressures=" + Arrays.toString(tyrePressures) +
                ", surfaceTypes=" + Arrays.toString(surfaceTypes) +
                '}';
    }
}

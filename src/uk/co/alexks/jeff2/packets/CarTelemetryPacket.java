package uk.co.alexks.jeff2.packets;

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

    public PacketHeader getPacketHeader() {
        return packetHeader;
    }

    public CarTelemetryData[] getCarTelemetryData() {
        return carTelemetryData;
    }

    public int getButtonStatus() {
        return buttonStatus;
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
}

package uk.co.alexks.jeff2;

import java.util.LinkedHashMap;
import java.util.Map;
import uk.co.alexks.jeff2.packets.*;

/**
 * Holds information about the session.
 *
 * @Author Alex Shaw
 */
public class FullSession {

    private Map<Integer, SessionPacket> sessionLog = new LinkedHashMap<Integer, SessionPacket>();
    private Map<Integer, LapDataPacket> lapDataLog = new LinkedHashMap<Integer, LapDataPacket>();
    private Map<Integer, CarTelemetryPacket> carTelemetryLog = new LinkedHashMap<Integer, CarTelemetryPacket>();
    private Map<Integer, CarStatusPacket> carStatusLog = new LinkedHashMap<Integer, CarStatusPacket>();

    public Map<Integer, SessionPacket> getSessionLog() {
        return sessionLog;
    }

    public Map<Integer, LapDataPacket> getLapDataLog() {
        return lapDataLog;
    }

    public Map<Integer, CarTelemetryPacket> getCarTelemetryLog() {
        return carTelemetryLog;
    }

    public Map<Integer, CarStatusPacket> getCarStatusLog() {
        return carStatusLog;
    }

    /**
     * Add a key and a value to the session log. If the key already exists, all entries after that will be removed
     * and the value will be added.
     * @param key
     * @param value
     */
    public void addToSessionLog(int key, SessionPacket value){
        if(sessionLog.containsKey(key)){
            //Key is already in the log, remove all entries since the key and replace it (i.e flashbacked)
            sessionLog.keySet().removeIf(e -> (e >= key));
        }
        sessionLog.put(key, value);
        System.out.println("Length: " + sessionLog.size());
        System.out.println("Added:\n "+ value.toString());
    }

    public void addToLapDataLog(int key, LapDataPacket value){
        if(lapDataLog.containsKey(key)){
            lapDataLog.keySet().removeIf(e -> (e >= key));
        }
        lapDataLog.put(key, value);
    }

    public void addToCarTelemetryLog(int key, CarTelemetryPacket value){
        if(carTelemetryLog.containsKey(key)){
            carTelemetryLog.keySet().removeIf(e -> (e >= key));
        }
        carTelemetryLog.put(key, value);
    }

    public void addToCarStatusLog(int key, CarStatusPacket value){
        if(carStatusLog.containsKey(key)){
            carStatusLog.keySet().removeIf(e -> (e >= key));
        }
        carStatusLog.put(key, value);
    }

}

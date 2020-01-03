package uk.co.alexks.jeff2;

import uk.co.alexks.jeff2.packets.CarStatusPacket;
import uk.co.alexks.jeff2.packets.CarTelemetryPacket;
import uk.co.alexks.jeff2.packets.LapDataPacket;
import uk.co.alexks.jeff2.packets.SessionPacket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        /*
         Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        System.out.println(map.toString());
        //Remove all entries with a key above 2
        map.keySet().removeIf(e -> e>2);
        System.out.println(map.toString());
         */

        FullSession fs = new FullSession();
        TelemetryListener tl = new TelemetryListener(fs);
        Thread tlThread = new Thread(tl);
        tlThread.start();
        Runtime.getRuntime().addShutdownHook(new Thread(new Shutdown(fs)));

        try{
            System.in.read();
        } catch (IOException e){
            e.printStackTrace();
        }

        System.exit(0);


        /*int[] test = new int[5];
        populateArray(test);
        for(Integer i : test){
            System.out.println(i);
        }*/
    }

    static private void populateArray(int[] a){
        for(int i=0; i < 5; i++){
            a[i]=i;
        }
    }

    static private int makeShort(byte b1, byte b0) {
        return ((b1 << 8) | (b0 & 0xff));
    }


    static private int uint16ToInt(byte[] arr){
        int n = 0xFF & arr[0];
        int m = 0xFF & arr[1];
        return n*256+m;
    }

    static private int uint8ToInt(byte b) {
        return 0xFF & b;
    }
}

class Shutdown implements Runnable{
    private FullSession fs;

    public Shutdown(FullSession fs){
        this.fs = fs;
    }

    @Override
    public void run(){
        Map<Integer, SessionPacket> sl = fs.getSessionLog();
        int slSize = sl.size();
        System.out.println("Session log size: " + slSize);
        System.out.println("Last entry:\n" + new ArrayList<SessionPacket>(sl.values()).get(slSize-1)+"\n");

        Map<Integer, LapDataPacket> ld = fs.getLapDataLog();
        int ldSize = ld.size();
        System.out.println("Lap data log size: " + ldSize);
        System.out.println("Last entry:\n" + new ArrayList<LapDataPacket>(ld.values()).get(ldSize-1)+"\n");

        Map<Integer, CarTelemetryPacket> ct = fs.getCarTelemetryLog();
        int ctSize = ct.size();
        System.out.println("Car telemetry size: " + ctSize);
        System.out.println("Last entry:\n" + new ArrayList<CarTelemetryPacket>(ct.values()).get(ctSize-1)+"\n");

        Map<Integer, CarStatusPacket> cs = fs.getCarStatusLog();
        int csSize = cs.size();
        System.out.println("Car status log size: " + csSize);
        System.out.println("Last entry:\n" + new ArrayList<CarStatusPacket>(cs.values()).get(csSize-1)+"\n");
    }
}
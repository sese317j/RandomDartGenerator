package com.jenns;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        final int punkte_initial = 501;
        final int multiplikator_out = 2;
        final boolean debug = true;
        final int anzahl_sets = 2;
        final int anzahl_legs = 3;

        List<List<Leg>> all_sets = new ArrayList<>();

        for(int i = 0; i < anzahl_sets; i++){
            List<Leg> legList = new ArrayList<>();
            for(int j = 0; j < anzahl_legs; j++){
                Leg einLeg = new Leg( debug, multiplikator_out, punkte_initial);
                legList.add(einLeg);
            }
            all_sets.add(legList);
        }



    }


}

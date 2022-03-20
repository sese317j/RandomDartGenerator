package com.jenns;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static String PathToDir = System.getProperty("user.dir") + File.separator + "out" + File.separator + "OutputData";


    public static void main(String[] args) {


        System.out.println(PathToDir);
        final int punkte_initial = 501;
        final int multiplikator_out = 2;
        final boolean debug = false;
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

        StringBuilder set_output = new StringBuilder();
        StringBuilder leg_output = new StringBuilder();
        StringBuilder zug_output = new StringBuilder();
        StringBuilder wurf_output = new StringBuilder();

        int set_nummer = 1;
        int leg_nummer = 1;
        int zug_nummer = 1;
        int wurf_nummer = 1;

        for(List<Leg> set : all_sets){
            //FÜr jedes set

            for(Leg leg : set){
                // Für jedes Leg

                for(List<Wurf> zug : leg.getZug_liste()){
                    // Für jeden Zug

                    for(Wurf wurf : zug){
                        // Für jeden Wurf
                        wurf_output.append("('" + wurf_nummer + "', '" + zug_nummer +"', '" + wurf + "')," + "\n");
                        wurf_nummer++;
                    }
                    zug_output.append("('" + zug_nummer + "', '" + leg_nummer + "')," + "\n");
                    zug_nummer++;
                }
                leg_output.append("('" + leg_nummer + "', '" + set_nummer + "')," + "\n");
                leg_nummer++;
            }
            set_output.append("('" + set_nummer + "', '" + "Spiel Nr 1" + "')," + "\n");
            set_nummer++;
        }

        wurf_output.setLength(wurf_output.length() - 2);
        zug_output.setLength(zug_output.length() - 2);
        leg_output.setLength(leg_output.length() - 2);
        set_output.setLength(set_output.length() - 2);

        wurf_output.append(";\n");
        zug_output.append(";\n");
        leg_output.append(";\n");
        set_output.append(";\n");


        writeStringToFile("wurfe.txt",wurf_output.toString());
        writeStringToFile("zuge.txt", zug_output.toString());
        writeStringToFile("legs.txt", leg_output.toString());
        writeStringToFile("sets.txt", set_output.toString());


    }

    static void writeStringToFile(String pFilename, String pFileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PathToDir + File.separator + pFilename, StandardCharsets.UTF_8))) {
            writer.write(pFileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

package com.jenns;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static String PathToDir = System.getProperty("user.dir") + File.separator + "out" + File.separator + "OutputData";

    final static int punkte_initial = 501;
    final static int multiplikator_out = 2;
    final static boolean debug = true;
    final static int[] spieler_ids = new int[]{1,2,3};
    final static int anzahl_sets = 2;
    final static int anzahl_legs = 3;
    final static String spiel_string = "Spiel 1";

    final static int wurf_offset = 0;
    final static int zug_offset = 0;
    final static int leg_offset = 0;
    final static int set_offset = 0;


    public static void main(String[] args) {
        System.out.println(PathToDir);

        if(!Files.exists(Paths.get(PathToDir))){
            try {
                Files.createDirectory(Paths.get(PathToDir));     //Create Output Dir
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        List<List<Leg>> all_sets = new ArrayList<>();

        int legID = leg_offset + 1;
        for(int i = 0; i < anzahl_sets; i++){
            List<Leg> legList = new ArrayList<>();
            for(int j = 0; j < anzahl_legs; j++){
                Leg einLeg = new Leg( debug, multiplikator_out, punkte_initial, legID, spieler_ids);
                legList.add(einLeg);
                legID++;
            }
            all_sets.add(legList);
        }

        StringBuilder set_output = new StringBuilder("INSERT INTO Satz (id,SpielId) VALUES\n");
        StringBuilder leg_output = new StringBuilder("INSERT INTO Leg (id,SatzId) VALUES\n");
        StringBuilder zug_output = new StringBuilder("INSERT INTO Zug (id,LegId,SpielerId,SummePunkte) VALUES\n");
        StringBuilder wurf_output = new StringBuilder("INSERT INTO Wurf (id,ZugId,Zahl,Feldart) VALUES\n");

        int set_nummer = set_offset + 1;
        int leg_nummer = leg_offset + 1;
        int zug_nummer = zug_offset + 1;
        int wurf_nummer = wurf_offset + 1;

        for(List<Leg> set : all_sets){
            //F??r jedes set

            for(Leg leg : set){
                // F??r jedes Leg

                for(Zug zug : leg.getZug_liste()){
                    // F??r jeden Zug

                    for(Wurf wurf : zug.getWurf_liste()){
                        // F??r jeden Wurf
                        wurf_output.append("('" + wurf_nummer + "', '" + zug_nummer +"', '" + wurf.getWurf() +"', '" + wurf.getMultiplikator() + "')," + "\n");
                        wurf_nummer++;
                    }
                    zug_output.append("('" + zug_nummer + "', '" + zug.getLegID() + "', '" + zug.getSpielerID() +"', '" + zug.get_summe_punkte() +"')," + "\n");
                    zug_nummer++;
                }
                leg_output.append("('" + leg_nummer + "', '" + set_nummer + "')," + "\n");
                leg_nummer++;
            }
            set_output.append("('" + set_nummer + "', '" + spiel_string + "')," + "\n");
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

package com.jenns;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        final int punkte_initial = 501;
        List<List<Wurf>> wurfe = new ArrayList<>();

        int aktuelle_punkte = punkte_initial;
        System.out.println("Punkte Initial: "  + aktuelle_punkte);
        while(aktuelle_punkte != 0){
            int punkte_zug = 0;
            List<Wurf> zug = new ArrayList<>();
            for(int i = 0; i < 3;i++){
                Wurf temp_wurf = new Wurf();
                zug.add(temp_wurf);
                punkte_zug += temp_wurf.getPunkte();
                if(aktuelle_punkte - punkte_zug == 0){
                    aktuelle_punkte -= punkte_zug;
                    break;
                }
            }
            for(Wurf wurf: zug){
                System.out.print(wurf + "   ");
            }

            System.out.print("  Summe: "+ punkte_zug);

            if(aktuelle_punkte - punkte_zug >= 0){
                aktuelle_punkte -= punkte_zug;
            }
            else if(aktuelle_punkte == 0){

            }
            else {
                System.out.print("  Überworfen");
            }

            System.out.print("  Punkte: " + aktuelle_punkte +"\n");
            wurfe.add(zug);
        }
        System.out.println(wurfe);

    }


}

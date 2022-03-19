package com.jenns;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        final int punkte_initial = 501;
        final int multiplikator_out = 3;
        final boolean debug = true;
        List<List<Wurf>> leg = new ArrayList<>();

        int aktuelle_punkte = punkte_initial;

        if(debug) {
            System.out.println("Punkte Initial: " + aktuelle_punkte);

            switch (multiplikator_out) {
                case 2:
                    System.out.println("Double Out");
                    break;
                case 3:
                    System.out.println("Tripple Out");
                    break;
                default:
                    break;
            }
        }

        while (aktuelle_punkte != 0) {
            int b = 0;
            while (aktuelle_punkte != 0 && b < 100) {
                b++;
                int punkte_zug = 0;
                List<Wurf> zug = new ArrayList<>();
                boolean uberworfen = false;

                for (int i = 0; i < 3; i++) {
                    Wurf temp_wurf = new Wurf();            // Neuen Wurf erzeugen
                    zug.add(temp_wurf);                     // Neuen Wurf dem Zug hinzufügen
                    punkte_zug += temp_wurf.getPunkte();    // Punkte für den aktuellen Zug berechnen

                    // Falls Punkte 0 und letzter wurf passt zu multiplikator
                    if (aktuelle_punkte - punkte_zug == 0 && temp_wurf.getMultiplikator() == multiplikator_out) {
                        aktuelle_punkte = 0;            //Punkte auf 0, Spiel zu ende
                        break;                          //verlasse aktuellen Zug
                    } else if (aktuelle_punkte - punkte_zug < 0 || aktuelle_punkte - punkte_zug < multiplikator_out) {        //Falls Überworfen
                        uberworfen = true;
                        break;                                        //Verlasse aktuellen Zug aber zieh nicht Punkte ab
                    }

                }

                if (aktuelle_punkte - punkte_zug > 0 && !uberworfen) {
                    aktuelle_punkte -= punkte_zug;
                }

                leg.add(zug);           // der Gesamte zug wird dem Leg hinzugefügt

                if (debug) {
                    StringBuilder str_zug = new StringBuilder();
                    for (Wurf wurf : zug) {
                        str_zug.append(String.format("%1$4s" + "  ", wurf));
                    }

                    String str_uberworfen = "";
                    if (uberworfen) {
                        str_uberworfen = "Überworfen  ";
                    }

                    String str_summme = Integer.toString(punkte_zug);
                    String str_aktuelle_punkte = Integer.toString(aktuelle_punkte);

                    String output_string = String.format("%1$-19s" + "Summe:" + "%2$4s" + "%3$14s" + "Punkte:" + "%4$4s", str_zug, str_summme, str_uberworfen, str_aktuelle_punkte);
                    System.out.println(output_string);
                }

            }
        }

        System.out.println(leg);

    }


}

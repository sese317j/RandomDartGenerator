package com.jenns;

import java.util.ArrayList;
import java.util.List;

public class Leg {

    private final List<Zug> zug_liste;
    private final int id;
    private final int[] SpielerIDs;
    private final int punkte_initial;
    private final int multiplikator_out;
    private final boolean debug;

    public Leg(boolean pDebug , int pMultiplikatorOut, int pPunkteInitial, int pId, int[] pSpielerIDs) {

        id = pId;
        SpielerIDs = pSpielerIDs;
        punkte_initial = pPunkteInitial;
        multiplikator_out = pMultiplikatorOut;
        debug = pDebug;
        zug_liste = new ArrayList<>();

        if (debug) {
            System.out.println("Punkte Initial: " + pPunkteInitial);

            switch (multiplikator_out) {
                case 1:
                    System.out.println("Straight Out");
                    break;
                case 2:
                    System.out.println("Double Out");
                    break;
                case 3:
                    System.out.println("Master Out");
                    break;
                default:
                    break;
            }
        }

        for (int spielerID : SpielerIDs) {

            int aktuelle_punkte = punkte_initial;

            List<Zug> zug_liste_temp = new ArrayList<>();

            StringBuilder output = new StringBuilder();

            while (aktuelle_punkte != 0) {
                output = new StringBuilder();
                aktuelle_punkte = punkte_initial;
                zug_liste_temp = new ArrayList<>();
                int b = 0;
                while (aktuelle_punkte != 0 && b < 30) {
                    b++;
                    int punkte_zug = 0;
                    Zug temp_zug = new Zug(spielerID, id);
                    boolean uberworfen = false;

                    for (int i = 0; i < 3; i++) {
                        Wurf temp_wurf = new Wurf();            // Neuen Wurf erzeugen
                        try {
                            temp_zug.addWurf(temp_wurf);                     // Neuen Wurf dem Zug hinzufügen
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        punkte_zug += temp_wurf.getPunkte();    // Punkte für den aktuellen Zug berechnen


                        if (aktuelle_punkte - punkte_zug == 0) {    // Falls Punkte 0 und letzter wurf passt zu multiplikator
                            if(multiplikator_out == 1){             // Straight out: letzter wurf egal
                                aktuelle_punkte = 0;                // Punkte auf 0, Spiel zu ende
                                break;                              // verlasse aktuellen Zug
                            }
                            else if(multiplikator_out == 2){            // double out
                                if(temp_wurf.getMultiplikator() == 2){  // Nur wenn letzter wurf double
                                    aktuelle_punkte = 0;                // Punkte auf 0, Spiel zu ende
                                    break;                              // verlasse aktuellen Zug
                                }
                            }
                            else if(multiplikator_out == 3){        // Master out
                                if(temp_wurf.getMultiplikator() == 2 || temp_wurf.getMultiplikator() == 3){
                                    aktuelle_punkte = 0;            //Punkte auf 0, Spiel zu ende
                                    break;                          //verlasse aktuellen Zug
                                }
                            }

                        }
                        else if (aktuelle_punkte - punkte_zug < 0 ) {                           //Falls Überworfen über null
                            uberworfen = true;
                            break;                                                              //Verlasse aktuellen Zug aber zieh nicht Punkte ab
                        }
                        else if(multiplikator_out == 3 && (aktuelle_punkte - punkte_zug < 2)){  //Falls Überworfen bei Master Out
                            uberworfen = true;
                            break;
                        }
                    else if(aktuelle_punkte - punkte_zug < multiplikator_out){                  //Falls überworfen bei Double out
                            uberworfen = true;
                            break;
                        }

                    }

                    if (aktuelle_punkte - punkte_zug > 0 && !uberworfen) {
                        aktuelle_punkte -= punkte_zug;
                    }

                    zug_liste_temp.add(temp_zug);           // der Gesamte zug wird dem Leg hinzugefügt

                    if (debug) {
                        StringBuilder str_zug = new StringBuilder();
                        for (Wurf wurf : temp_zug.getWurf_liste()) {
                            str_zug.append(String.format("%1$4s" + "  ", wurf));
                        }

                        String str_uberworfen = "";
                        if (uberworfen) {
                            str_uberworfen = "Überworfen  ";
                        }

                        String str_summme = Integer.toString(punkte_zug);
                        String str_aktuelle_punkte = Integer.toString(aktuelle_punkte);

                        String output_string = String.format("%1$-19s" + "Summe:" + "%2$4s" + "%3$14s" + "Punkte:" + "%4$4s", str_zug, str_summme, str_uberworfen, str_aktuelle_punkte);
                        output.append(output_string).append("\n");
                    }

                }

            }
            zug_liste.addAll(zug_liste_temp);

            if (debug) {
                System.out.println("Spieler ID: " + spielerID + "    LegID:" + id);
                System.out.print(output);
                System.out.println(zug_liste_temp);
            }
        }
    }

    public Leg(int pId){
        this(false,2,501, pId, new int[]{1});
    }

    public int getId() {
        return id;
    }

    public List<Zug> getZug_liste (){
        return zug_liste;
    }

}

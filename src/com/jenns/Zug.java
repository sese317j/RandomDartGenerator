package com.jenns;

import java.util.ArrayList;
import java.util.List;

public class Zug {
    private final int spielerID;
    private final int legID;
    private final List<Wurf> wurf_liste;

    public Zug (int pSpielerID,int pLegID ){
        wurf_liste = new ArrayList<>();
        spielerID = pSpielerID;
        legID = pLegID;
    }

    public void addWurf(Wurf pWurf) throws Exception {
        if(wurf_liste.size()<=3) {
            wurf_liste.add(pWurf);
        }
        else {
            throw new Exception("Zug kann nur aus maximal 3 würfen bestehen");
        }
    }

    public List<Wurf> getWurf_liste(){
        return wurf_liste;
    }

    public int get_summe_punkte() {
        int s = 0;
        for (Wurf wurf : wurf_liste) {
            s += wurf.getPunkte();
        }
        return s;
    }

    public int getSpielerID() {
        return spielerID;
    }

    public int getLegID() {
        return legID;
    }

    public String toString(){
        StringBuilder output = new StringBuilder("[");
        for(Wurf wurf : wurf_liste){
            output.append(wurf).append(", ");
        }
        output.setLength(output.length() - 2);
        output.append("]");
        return output.toString();
    }
}

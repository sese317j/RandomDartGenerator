package com.jenns;

import java.util.Random;

public class Wurf {
    private int wurf;
    private int multiplikator;

    public Wurf (){
        Random rand = new Random(); //instance of random class
        Random rand2 = new Random();

        int rand_wurf = rand.nextInt(22); // Zahlen von 0 bis 21

        if(rand_wurf == 21){ // Dart 0 bis 20 + 25
            rand_wurf = 25;
            multiplikator = rand2.nextInt(2) + 1; // Zahl von 1 bis 2
        }
        else if(rand_wurf == 0){
            multiplikator = 1;
        }
        else {
            multiplikator = rand2.nextInt(3) + 1; // Zahl von 1 bis 3
        }
        this.wurf = rand_wurf;
    }

    public Wurf (int pWurf, int pMultiplikator){
        this.wurf = pWurf;
        this.multiplikator = pMultiplikator;
    }

    public int getWurf() {
        return wurf;
    }

    public void setWurf(int wurf) {
        this.wurf = wurf;
    }

    public int getMultiplikator() {
        return multiplikator;
    }

    public void setMultiplikator(int multiplikator) {
        this.multiplikator = multiplikator;
    }

    public int getPunkte(){
        return wurf * multiplikator;
    }

    @Override
    public String toString(){
        String mult;
        switch (multiplikator){
            case 2:
                mult= "d_";
                break;
            case 3:
                mult="t_";
                break;
            default:
                mult="";
                break;
        }
        return mult + wurf;
    }
}


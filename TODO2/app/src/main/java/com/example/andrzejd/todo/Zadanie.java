package com.example.andrzejd.todo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by andrz on 08.06.2017.
 */

    class Zadanie {

    /******** Zmienne ******/
    private String zadanieGlowne,etap;
    private String  nagroda;
    private int hour;
    private int min;
    private int year;
    private int month;
    private int day;
    private int priorytet;
    private Boolean wykonano;
    ArrayList<Zadanie> etapy;
    /******** Zmienne ******/

    public  Zadanie()
    {
        priorytet = 1;
        etapy = new ArrayList<>();
        niewykonano();
    }


    /******** Getery i Setery ******/

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getZadanieGlowne() {
        return zadanieGlowne;
    }

    public void setZadanieGlowne(String zadanieGlowne) {
        this.zadanieGlowne = zadanieGlowne;
    }

    public String getNagroda() {
        return nagroda;
    }

    public void setNagroda(String nagroda) {
        this.nagroda = nagroda;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getTime() {
        return getHour()+":"+getMin();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Boolean czywykonano() {
        return wykonano;
    }

    public void wykonano() {
        wykonano = true;
    }
    public void niewykonano() {
        wykonano = false;
    }
    public String getEtap() {
        return etap;
    }

    public void setEtap(String etap) {
        this.etap = etap;
    }


    public int getPriorytet() {
        return priorytet;
    }

    public void setPriorytet(int priorytet) {
        this.priorytet = priorytet;
    }

    public void setZadanie(Zadanie zadanie) {
        etapy.add(zadanie);
    }

    public ArrayList<Zadanie> getEtapy() {
        return etapy;
    }
    /******** Getery i Setery ******/
    /******** Priorytet ******/
    public  int getObrazekPriorytet()
    {
        return getObrazekPriorytet(priorytet);
    }
    public static int getObrazekPriorytet(int priorytet)
    {
        switch (priorytet) {
            case 1:
                return R.drawable.p1;
            case 2:
                return R.drawable.p2;
            case 3:
                return R.drawable.p3;
            case 4:
                return R.drawable.p4;
            case 5:
                return R.drawable.p5;
            case 6:
                return R.drawable.p6;
            case 7:
                return R.drawable.p7;
            case 8:
                return R.drawable.p8;
            case 9:
                return R.drawable.p9;
            case 10:
                return R.drawable.p10;
            case 11:
                return R.drawable.p11;

        }
        return R.drawable.p1;
    }
    /******** Priorytet ******/
    /******** Zapis ******/
    public PrintWriter zapisz(PrintWriter out)
    {
        out.println("Zad");
        out.println(zadanieGlowne);
        out.println(etap);
        out.println("int");
        out.println(hour);
        out.println(min);
        out.println(day);
        out.println(month);
        out.println(year);
        out.println(priorytet);
        out.println(wykonano);
        out.println("/Zad");
        for(Zadanie zad:etapy)
        {
            zad.setZadanieGlowne(zadanieGlowne);
            zad.setDay(day);
            zad.setMonth(month);
            zad.setYear(year);
            out=zad.zapisz(out);
        }
        if(nagroda!="")
        {
            out.println("Ngr");
            out.println(zadanieGlowne);
            out.println(nagroda);
            out.println("int");
            out.println(11);
            out.println(wykonano);
            out.println("/Ngr");
        }

        return out;
    }

    /******** Zapis ******/
    /******** Odczyt ******/
    public Scanner wczytaj(Scanner in) {
        String pom = "";
        while (in.hasNext()&&!pom.equals("Zad") && !pom.equals("Ngr")) {
            pom = in.nextLine();
        }
        if (pom.equals("Zad")) {
            zadanieGlowne = in.nextLine();
            etap = in.nextLine();

            while (!pom.equals("int")) {
                pom = in.nextLine();
            }
            hour = in.nextInt();
            min = in.nextInt();
            day = in.nextInt();
            month = in.nextInt();
            year = in.nextInt();
            priorytet = in.nextInt();
            wykonano = in.nextBoolean();
        }
        if (pom.equals("Ngr")) {
            zadanieGlowne = in.nextLine();
            nagroda = in.nextLine();
            while (!pom.equals("int")) {
                pom = in.nextLine();
            }
            priorytet = in.nextInt();
            wykonano = in.nextBoolean();
        }
        return in;
    }

    public String getDedline() {
        return day+"-"+month+"-"+year;
    }

    /******** Odczyt ******/





}



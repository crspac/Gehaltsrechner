package com.example.gehaltsrechner;


import android.util.Log;

import java.util.Calendar;

public class CalculationSteuer {
    private int year;
    private int month;
    private int day;
    private double lohnsteuerbetrag;
    private boolean kinder;
    private double kinderfreibetrag;
    private String bundesland;
    private double bruttolohn;
    private String rentenversicherung;
    private String krankenversicherung;
    private String arbeitslosenversicherung;
    private double krankenversicherungszusatz;
    private double rentenversicherungprivat;
    private boolean kirchensteuer;
    private double endBetrag = 0;
    private double rentenversicherungsBetrag = 0;
    private double arbeitslosenversicherungsBetrag = 0;
    private double krankenversicherungsBetrag = 0;
    private double pflegeversicherungsBetrag = 0;
    private double kirchensteuerBetrag = 0;
    private double soliBetrag = 0;



    public CalculationSteuer(int year, int month, int day, double lohnsteuerbetrag, boolean kinder, double kinderfreibetrag, String bundesland, double bruttolohn, String rentenversicherung, String krankenversicherung, double krankenversicherungszusatz, double rentenversicherungprivat, boolean kirchensteuer, String arbeitslosenversicherung) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.lohnsteuerbetrag = lohnsteuerbetrag;
        this.kinder = kinder;
        this.kinderfreibetrag = kinderfreibetrag;
        this.bundesland = bundesland;
        this.bruttolohn = bruttolohn;
        this.rentenversicherung = rentenversicherung;
        this.krankenversicherung = krankenversicherung;
        this.arbeitslosenversicherung = arbeitslosenversicherung;
        this.krankenversicherungszusatz = krankenversicherungszusatz;
        this.rentenversicherungprivat = rentenversicherungprivat;
        this.kirchensteuer = kirchensteuer;
    }

    public double calculateSteuer(){


        if(rentenversicherung.equalsIgnoreCase("Gesetzlich Pflichtversichert")){
            rentenversicherungsBetrag = (bruttolohn/100) * 9.3;
        }else if(rentenversicherung.equalsIgnoreCase("Privatversichert")){
            rentenversicherungsBetrag =  rentenversicherungprivat;
        }else{
            rentenversicherungsBetrag = 0;
        }

        if(arbeitslosenversicherung.equalsIgnoreCase("Gesetzlich Pflichtversichert")){
            arbeitslosenversicherungsBetrag = (bruttolohn/100) * 1.25;
        }else{
            arbeitslosenversicherungsBetrag = 0;
        }

        if(krankenversicherung.equalsIgnoreCase("14.6 %")){
            krankenversicherungsBetrag = (bruttolohn/100) * (7.3 + krankenversicherungszusatz);
        }else if(krankenversicherung.equalsIgnoreCase("14 %")){
            krankenversicherungsBetrag = (bruttolohn/100) * (7 + krankenversicherungszusatz);
        }else{
            krankenversicherungsBetrag =  krankenversicherungszusatz;
        }

        if(checkAlter(year,month,day) > 23 && kinder == false){
                pflegeversicherungsBetrag = (bruttolohn/100) * 1.775;
        }else{
                pflegeversicherungsBetrag = (bruttolohn/100) * 1.525;
        }



        if(kirchensteuer){
            kirchensteuerBetrag = (lohnsteuerbetrag/100) * kirchensteuerSatz();
        }

        if(lohnsteuerbetrag > 81.0){
            soliBetrag = (lohnsteuerbetrag/100) * 5.5;
        }

        endBetrag = bruttolohn - rentenversicherungsBetrag - arbeitslosenversicherungsBetrag - krankenversicherungsBetrag - pflegeversicherungsBetrag - kirchensteuerBetrag - soliBetrag - lohnsteuerbetrag;

        return endBetrag;
    }

    private  int checkAlter(int year, int month, int day){
        Calendar cheute = Calendar.getInstance();
        Calendar cgeburtstag = Calendar.getInstance();
        int alter;

        cgeburtstag.set(year, month, day);
        alter = cheute.get(Calendar.YEAR) - cgeburtstag.get(Calendar.YEAR);

        return alter;
    }

    private double kirchensteuerSatz(){
        if(kirchensteuer){
            if(bundesland == "Bayern" || bundesland == "Baden-Württemberg"){
                return 8.0;
            }
            return 9.0;
        }
        return 0;
    }

    public double getLohnsteuerbetrag() {
        return lohnsteuerbetrag;
    }

    public boolean isKinder() {
        return kinder;
    }

    public double getKinderfreibetrag() {
        return kinderfreibetrag;
    }

    public String getBundesland() {
        return bundesland;
    }

    public String getRentenversicherung() {
        return rentenversicherung;
    }

    public String getKrankenversicherung() {
        return krankenversicherung;
    }

    public double getKrankenversicherungszusatz() {
        return krankenversicherungszusatz;
    }

    public double getRentenversicherungprivat() {
        return rentenversicherungprivat;
    }

    public boolean isKirchensteuer() {
        return kirchensteuer;
    }

    public double getEndBetrag() {
        return endBetrag;
    }

    public double getRentenversicherungsBetrag() {
        return rentenversicherungsBetrag;
    }

    public double getArbeitslosenversicherungsBetrag() {
        return arbeitslosenversicherungsBetrag;
    }

    public double getKrankenversicherungsBetrag() {
        return krankenversicherungsBetrag;
    }

    public double getPflegeversicherungsBetrag() {
        return pflegeversicherungsBetrag;
    }

    public double getKirchensteuerBetrag() {
        return kirchensteuerBetrag;
    }

    public double getSoliBetrag() {
        return soliBetrag;
    }


    public double getBruttolohn() {
        return bruttolohn;
    }

}

package com.example.gehaltsrechner;


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
    //private String arbeitslosenversicherung;
    private double krankenversicherungszusatz;
    private double rentenversicherungprivat;
    private boolean kirchensteuer;



    public CalculationSteuer(int year, int month, int day, double lohnsteuerbetrag, boolean kinder, double kinderfreibetrag, String bundesland, double bruttolohn, String rentenversicherung, String krankenversicherung, double krankenversicherungszusatz, double rentenversicherungprivat, boolean kirchensteuer) {
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

        this.krankenversicherungszusatz = krankenversicherungszusatz;
        this.rentenversicherungprivat = rentenversicherungprivat;
        this.kirchensteuer = kirchensteuer;
    }

    public double calculateSteuer(){
        double endBetrag = 0;
        double rentenversicherungsBetrag = 0;
        double arbeitslosenversicherungsBetrag = 0;
        double krankenversicherungsBetrag = 0;
        double pflegeversicherungsBetrag = 0;
        double kirchensteuerBetrag = 0;
        double soliBetrag = 0;

        if(rentenversicherung == "Gesetzlich Pflichtversichert"){
            rentenversicherungsBetrag = (bruttolohn/100) * 9.3;
        }else if(rentenversicherung == "Privatversichert"){
            rentenversicherungsBetrag =  rentenversicherungprivat;
        }else{
            rentenversicherungsBetrag = 0;
        }

//        if(arbeitslosenversicherung == ""){
//            arbeitslosenversicherungsBetrag = (bruttolohn/100) * 1.25;
//        }

        if(krankenversicherung == "14,6 %"){
            krankenversicherungsBetrag = (bruttolohn/100) * (7.3 + krankenversicherungszusatz);
        }else if(krankenversicherung == "14 %"){
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

        if(lohnsteuerbetrag < 81){
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


}

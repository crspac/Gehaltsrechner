package com.example.gehaltsrechner;

import java.time.LocalDateTime;

public class CalculationSteuer {
    private LocalDateTime geburtstag;
    private double lohnsteuerbetrag;
    private boolean kinder;
    private double kinderfreibetrag;
    private String bundesland;
    private double bruttolohn;
    private int rentenversicherung;
    private int krankenversicherung;
    private int pflegeversicherung;
    private int arbeitslosenversicherung;
    private double krankenversicherungszusatz;
    private boolean kirchensteuer;



    public CalculationSteuer(LocalDateTime geburtstag, float lohnsteuerbetrag, boolean kinder, float kinderfreibetrag, String bundesland, float bruttolohn, int rentenversicherung, int krankenversicherung, int pflegeversicherung, int arbeitslosenversicherung, float krankenversicherungszusatz, boolean kirchensteuer) {
        this.geburtstag = geburtstag;
        this.lohnsteuerbetrag = lohnsteuerbetrag;
        this.kinder = kinder;
        this.kinderfreibetrag = kinderfreibetrag;
        this.bundesland = bundesland;
        this.bruttolohn = bruttolohn;
        this.rentenversicherung = rentenversicherung;
        this.krankenversicherung = krankenversicherung;
        this.pflegeversicherung = pflegeversicherung;
        this.arbeitslosenversicherung = arbeitslosenversicherung;
        this.krankenversicherungszusatz = krankenversicherungszusatz;
        this.kirchensteuer = kirchensteuer;
    }

    private double calculateSteuer(){
        double endBetrag = 0;
        double rentenversicherungsBetrag = 0;
        double arbeitslosenversicherungsBetrag = 0;
        double krankenversicherungsBetrag = 0;
        double pflegeversicherungsBetrag = 0;
        double kirchensteuerBetrag = 0;
        double soliBetrag = 0;

        if(rentenversicherung == 1){
            rentenversicherungsBetrag = (bruttolohn/100) * 9.3;
        }

        if(arbeitslosenversicherung == 1){
            arbeitslosenversicherungsBetrag = (bruttolohn/100) * 1.25;
        }

        if(krankenversicherung == 1){
            krankenversicherungsBetrag = (bruttolohn/100) * (7.3 + krankenversicherungszusatz);
        }

        if(pflegeversicherung == 1) {
            if(checkAlter(geburtstag) > 23 && kinder == false){
                pflegeversicherungsBetrag = (bruttolohn/100) * 1.775;
            }else{
                pflegeversicherungsBetrag = (bruttolohn/100) * 1.525;
            }

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

    private  int checkAlter(LocalDateTime geburtstag){
        int alter;
        LocalDateTime aktuellesJahr = LocalDateTime.now();

        alter = aktuellesJahr.getYear() - geburtstag.getYear();
        return alter;
    }

    private double kirchensteuerSatz(){
        if(kirchensteuer){
            if(bundesland == "Bayern" || bundesland == "Baden Württemberg"){
                return 8.0;
            }
            return 9.0;
        }
        return 0;
    }


}

package com.example.gehaltsrechner;

import java.time.LocalDateTime;

public class CalculationLohnsteuer {
   private LocalDateTime geburtstag;
   private int steuerklasse;
   private float kinderfreibetrag;
   private String bundesland;
   private float bruttolohn;
   private int rentenversicherung;
   private int krankenversicherung;
   private float krankenversicherungszusatz;
   private boolean kirchensteuer;

    public CalculationLohnsteuer(LocalDateTime geburtstag, int steuerklasse, float kinderfreibetrag, String bundesland, float bruttolohn, int rentenversicherung, int krankenversicherung, float krankenversicherungszusatz, boolean kirchensteuer) {
        this.geburtstag = geburtstag;
        this.steuerklasse = steuerklasse;
        this.kinderfreibetrag = kinderfreibetrag;
        this.bundesland = bundesland;
        this.bruttolohn = bruttolohn;
        this.rentenversicherung = rentenversicherung;
        this.krankenversicherung = krankenversicherung;
        this.krankenversicherungszusatz = krankenversicherungszusatz;
        this.kirchensteuer = kirchensteuer;
    }

    private  boolean checkAlterUeber64(LocalDateTime geburtstag){
        LocalDateTime aktuellesJahr = LocalDateTime.now();
        if(aktuellesJahr.getYear() - geburtstag.getYear() >= 64){
            return true;
        }else {
            return false;
        }
    }
}

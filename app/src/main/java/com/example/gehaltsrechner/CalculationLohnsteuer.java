package com.example.gehaltsrechner;

import java.time.LocalDate;

public class Calculation_Lohnsteuer {
   private LocalDate geburtstag;
   private int steuerklasse;
   private float kinderfreibetrag;
   private int bundesland;
   private float bruttolohn;
   private int rentenversicherung;
   private int krankenversicherung;
   private int krankenversicherungszusatz;
   private boolean kirchensteuer;

    public Calculation_Lohnsteuer(LocalDate geburtstag, int steuerklasse, float kinderfreibetrag, int bundesland, float bruttolohn, int rentenversicherung, int krankenversicherung, int krankenversicherungszusatz, boolean kirchensteuer) {
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
}

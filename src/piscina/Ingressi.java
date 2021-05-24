package piscina;

import java.time.*;
import java.util.*;

/*CLASSE INGRESSI: Superclasse che gestisce gli ingressi (sia degli utenti abbonati che di quelli non abbonati)*/
public class Ingressi {
    /* VARIABIlI
        * data: data dell'ingresso
        * informazioni: variano a secoda della tipologia di utenti
            - utente abbonato: nome, cognome
            - utente non abbonato: prezzo del biglietto 
    */
    private static LocalDate data;
    //private String informazioni;

    public Ingressi(LocalDate data) {
        this.data = data;
        //this.informazioni = informazioni;
    }

    public static LocalDate getData() {
        return data;
    }
}



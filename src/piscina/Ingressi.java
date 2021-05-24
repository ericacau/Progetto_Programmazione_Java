package piscina;

import java.util.*;
/*CLASSE INGRESSI: Superclasse che gestisce gli ingressi (sia degli utenti abbonati che di quelli non abbonati)*/
public class Ingressi {
    /* VARIABIlI
        * data: data dell'ingresso
        * informazioni: variano a secoda della tipologia di utenti
            - utente abbonato: nome, cognome
            - utente non abbonato: prezzo del biglietto 
    */
    private static Calendar data;
    //private String informazioni;

    public Ingressi(Calendar d) {
        d = data;
        //this.informazioni = informazioni;
    }

    public static Calendar getData() {
        return data;
    }
}



package piscina;

import java.io.Serializable;
import java.time.LocalDate;


/*CLASSE INGRESSI: Superclasse che gestisce gli ingressi (sia degli utenti abbonati che di quelli non abbonati)*/
public class Ingressi implements Serializable {
    /* VARIABIlI
        * data: data dell'ingresso
        * informazioni: variano a secoda della tipologia di utenti
            - utente abbonato: nome, cognome
            - utente non abbonato: prezzo del biglietto 
    */
    private LocalDate data;
    private String informazioni;

    public Ingressi(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }


}



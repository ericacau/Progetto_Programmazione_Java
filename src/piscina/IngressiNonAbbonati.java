package piscina;

import java.time.*;

/*Estensione della superclasse Ingressi che gestisce gli ingressi degli utenti non abbonati contenenti
* data di ingresso
* utente -> variabile che contiene le informazioni della classe utenteAbbonato (nome e cognome)*/
public class IngressiNonAbbonati extends Ingressi{
    private double prezzo;
    private UtenteNonAbbonato utenteNA;

    public IngressiNonAbbonati(LocalDate data, UtenteNonAbbonato utenteNA, double prezzo) {
        super(data);
        this.prezzo = utenteNA.getPrezzoBiglietto();
        this.utenteNA = utenteNA;
    }

    public UtenteNonAbbonato getUtenteNA() {

        return utenteNA;
    }

    public String toString() {
        return "DATA INGRESSO: " + this.getData() + "\t" + "Biglietto " + this.prezzo + "\t" + "RIDUZIONE" ;

    }
}

package piscina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formattaData = DateTimeFormatter.ofPattern("dd/M/yyyy");
        String dataStampa = this.getData().format(formattaData);
        return "DATA INGRESSO: " + dataStampa + "\t" + "Biglietto " + this.prezzo + "\t" + "RIDUZIONE: "  + utenteNA.stampaTipoRiduzione();
    }
}

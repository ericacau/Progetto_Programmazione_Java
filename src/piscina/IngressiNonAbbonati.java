package piscina;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*Estensione della superclasse Ingressi che gestisce gli ingressi degli utenti non abbonati contenenti
 * data di ingresso
 * utente -> che contiene le informazioni della classe utenteNonAbbonato (nome e cognome)*/

public class IngressiNonAbbonati extends Ingressi implements Serializable {
    private double prezzo;
    private UtenteNonAbbonato utenteNA;
    static final long serialVersionUID = -4829345612921075523L;

    public IngressiNonAbbonati(LocalDate data, UtenteNonAbbonato utenteNA) {
        super(data);
        this.utenteNA = utenteNA;
        this.prezzo = 0;
    }

    public UtenteNonAbbonato getUtenteNA() {
        return utenteNA;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String toString() {
        DateTimeFormatter formattaData = DateTimeFormatter.ofPattern("dd/M/yyyy");
        String dataStampa = this.getData().format(formattaData);
        return "|DATA INGRESSO: " + dataStampa + "\t" + "|COSTO: " + this.prezzo + " euro \t"
                + "|TIPO RIDUZIONE: " + utenteNA.stampaTipoRiduzione() + "\t|";
    }
}

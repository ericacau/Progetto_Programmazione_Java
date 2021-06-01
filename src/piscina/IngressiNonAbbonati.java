package piscina;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*Estensione della superclasse Ingressi che gestisce gli ingressi degli utenti non abbonati contenenti
<<<<<<< Updated upstream
* data di ingresso
* utente -> variabile che contiene le informazioni della classe utenteAbbonato (nome e cognome)*/
public class IngressiNonAbbonati extends Ingressi implements Serializable {
=======
 * data di ingresso
 * utente -> variabile che contiene le informazioni della classe utenteAbbonato (nome e cognome)*/

public class IngressiNonAbbonati extends Ingressi implements Serializable {

    static final long serialVersionUID = 1;
>>>>>>> Stashed changes
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
        return "|DATA INGRESSO: " + dataStampa + "\t" + "|COSTO: " + this.prezzo + " euro \t" + "|RIDUZIONE: "  + utenteNA.stampaTipoRiduzione() +"\t|";
    }
}

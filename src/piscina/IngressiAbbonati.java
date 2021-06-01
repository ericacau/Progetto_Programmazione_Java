package piscina;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*CLASSE INGRESSIABBONATI 
    Estensione della superclasse Ingressi che gestisce gli ingressi degli utenti abbonati contenenti
    * data du ingresso
    * utente -> variabile che contiene le informazioni della classe utenteAbbonato (nome e cognome)  

*/
public class IngressiAbbonati extends Ingressi implements Serializable {
<<<<<<< Updated upstream
=======

    static final long serialVersionUID = 1;
>>>>>>> Stashed changes
    private UtenteAbbonato utenteA;

    public IngressiAbbonati(LocalDate data, UtenteAbbonato utenteA) {
        super(data);
        this.utenteA = utenteA;
    }

    public UtenteAbbonato getUtente() {
        return utenteA;
    }

    public void setUtente(UtenteAbbonato utenteA) {
        this.utenteA = utenteA;
    }

    public String toString() {
        DateTimeFormatter formattaData = DateTimeFormatter.ofPattern("dd/M/yyyy");
        String dataStampa = this.getData().format(formattaData);
        return "|DATA INGRESSO: " + dataStampa + "\t" + "|Nome: " + utenteA.getNome() + "  |Cognome: " +
                utenteA.getCognome() + "   |ID UTENTE: " + utenteA.getIdUtente();
    }

}



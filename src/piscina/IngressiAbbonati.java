package piscina;

import java.time.LocalDate;

public class IngressiAbbonati extends Ingressi{
    private UtenteAbbonato utente;

    public IngressiAbbonati(LocalDate data, UtenteAbbonato u) {
        super(data);
        this.utente = u;
    }

    public String toString() {
        return "DATA INGRESSO: " + this.getData() + "\t" + "Nome e Cognome utente: " + utente.getNome() + " " + utente.getCognome();
    }
}


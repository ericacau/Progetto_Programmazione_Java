package piscina;

import java.time.LocalDate;

public class IngressiNonAbbonati extends Ingressi{
    private double prezzo;
    private UtenteNonAbbonato u;

    public IngressiNonAbbonati(LocalDate data, UtenteNonAbbonato u, double prezzo) {
        super(data);
        this.prezzo = u.getPrezzoBiglietto();
        this.u = u;
    }

    public UtenteNonAbbonato getU() {
        return u;
    }

    public String toString() {
        return "DATA INGRESSO: " + this.getData() + "\t" + "Biglietto " + this.prezzo + "\t" + "RIDUZIONE" ;

    }
}

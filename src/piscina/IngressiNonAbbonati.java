package piscina;

import java.time.LocalDate;

public class IngressiNonAbbonati extends Ingressi{
    private double prezzo;

    public IngressiNonAbbonati(LocalDate data, UtenteNonAbbonato u, double prezzo) {
        super(data);
        this.prezzo = u.getPrezzoBiglietto();
    }


    public String toString() {
        return "DATA INGRESSO: " + this.getData() + "\t" + "Biglietto " + this.prezzo + ;

    }
}

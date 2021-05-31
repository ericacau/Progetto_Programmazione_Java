package piscina;

import java.io.Serializable;
import java.time.LocalDate;

// Superclasse per la gestione degli ingressi degli utenti abbonati e dei non abbonati)

public class Ingressi implements Serializable {
    static final long serialVersionUID = 1;
    private LocalDate data;


    public Ingressi(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

}



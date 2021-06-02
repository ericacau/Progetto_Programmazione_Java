package piscina;

import java.io.Serializable;
import java.time.LocalDate;


/*CLASSE INGRESSI: Superclasse che gestisce gli ingressi (sia degli utenti abbonati che di quelli non abbonati)*/

public class Ingressi implements Serializable {
    private LocalDate data;

    public Ingressi(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

}

package piscina;

import java.io.Serializable;
import java.time.LocalDate;


/*CLASSE INGRESSI: Superclasse che gestisce gli ingressi (sia degli utenti abbonati che di quelli non abbonati)
    contenente la data degli ingressi
*/

public class Ingressi implements Serializable {
    private LocalDate data;
    static final long serialVersionUID = -4829345612921075523L;

    public Ingressi(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

}

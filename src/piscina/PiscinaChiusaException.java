package piscina;

import java.time.LocalDate;

public class PiscinaChiusaException extends Exception {

    public PiscinaChiusaException() {
        super("La piscina è chiusa! Non puoi inserire un ingresso");
    }

    public PiscinaChiusaException(LocalDate d) {
        super("La piscina è chiusa in data " + d + ". Non puoi inserire l'ingresso!");
    }
}

package piscina;

import java.time.LocalDate;

public class DataErrataException extends Exception {

    public DataErrataException() {
        super("Inserisci una data in formato gg/MM/AAAA. Esempio: 10/02/2021");
    }

}



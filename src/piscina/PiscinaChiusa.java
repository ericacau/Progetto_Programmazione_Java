package piscina;

import java.time.LocalDate;

public class PiscinaChiusa extends Exception {

    public PiscinaChiusa(){
        super("La piscina è chiusa");
    }

    public PiscinaChiusa(LocalDate d){
        super("La piscina è chiusa in data " + d);
    }
}

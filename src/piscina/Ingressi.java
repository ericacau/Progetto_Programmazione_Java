package piscina;

import java.time.LocalDate;


/*CLASSE INGRESSI: non so ancora se fare un'unica classe ingressi o due distinte
	String nome;
	String cognome;
	*/
public class Ingressi {

    private LocalDate data;
    private String informazioni;

    public Ingressi(LocalDate data) {
        this.data = data;
        this.informazioni = informazioni;
    }

    public LocalDate getData() {
        return data;
    }
}



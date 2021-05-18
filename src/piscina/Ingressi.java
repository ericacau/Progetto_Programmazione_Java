package piscina;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/*CLASSE INGRESSI: non so ancora se fare un'unica classe ingressi o due distinte
	String nome;
	String cognome;
	*/
public class Ingressi {

    private LocalDate data;
    private String informazioni;

    public Ingressi(LocalDate data, String informazioni) {
        this.data = data;
        this.informazioni = informazioni;
    }

    public LocalDate getData() {
        return data;
    }



    public String toString() {
            return "Ingresso: " + data + "\n" + "info " + informazioni;
    }
}
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
public class Ingressi  {
	Date data;
	double prezzo;

	public Ingressi(Date data, double prezzo) {
		this.data = data;
		this.prezzo = prezzo;

	}

	public Date getData() {
		return data;
	}

	//setto la data
	public void setData(Date data) {
		this.data = data;
	}
	
}
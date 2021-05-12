package piscina;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Ingressi {
	private static Date data;
	private static Scanner input = new Scanner (System.in);

	public Ingressi() {
		this.data = data;
	}

	public static Date getData() {
		return data;
	}

	public static LocalDate setData() {

		System.out.println("Inserisci la data dell'accesso in formato DD/MM/YYYY");
		// l'utente inserisce la data;
		String d = input.nextLine();

		// inserire un controllo sulla correttezza della data (try catch)

		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate d1 = LocalDate.parse(d1, dt);
		
		return d1;
	}
	
	//setto la data
	
}
package piscina;

import java.util.*;
import java.io;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class GestionePiscina {
	static Scanner input = new Scanner(System.in);
	//VETTORE INGRESSI 	
	Vector <String> ingressi = new Vector<String>();

	public static void aggiungiIngresso() {
		
		System.out.println("Stai aggiungendo un nuovo ingresso");
		
		System.out.println("Inserisci la data dell'accesso in formato DD/MM/YYYY");
		//l'utente inserisce la data;
		String d = input.nextLine();
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate d1 = LocalDate.parse(d1, dt);		
		
		System.out.println("Premi A se l'ingresso è di un utente ABBONATO o N se non abbonato");
		char s; 
		do {
			s = input.next().charAt(0);
			if (s == 'A') {
				System.out.println("L'utente è abbonato");
			};
			if (s == 'N') {
				System.out.println("L'utente non è abbonato");
			};
		} while();
		//System.out.println("Aggiunto l'ingresso dell'utente" + );
	}
	
	//per utente abbonato: + nome utente
	
	//per non abbonato: + prezzo biglietto
	
	//visualizzare la lista degli ingressi di uno specifico giorno
	
	//visualizzare la lista degli ingressi di uno specifico mese in ORDINE
	
	//visualizzare l'elenco di tutti gli ingressi di uno specifico utente abbonato
	
	//visualizzare l’elenco degli incassi giornalieri di uno specifico mese
	
	
	//visualizzare l’elenco con il numero degli ingressi in abbonamento giornalieri di uno specifico mese
	
	
		
}

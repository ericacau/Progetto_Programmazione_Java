package piscina;


import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestionePiscina {
	static Scanner input = new Scanner(System.in);

	// VETTORE INGRESSI
	Vector<Ingressi> ingressi = new Vector<Ingressi>();

	public static void aggiungiIngresso() {
		
		System.out.println("Stai aggiungendo un nuovo ingresso");
		Date data = Ingressi.setData();

		System.out.println("Premi A se l'ingresso è di un utente ABBONATO o N se non è abbonato");
		char scelta;
		scelta = input.next().charAt(0);
		switch (scelta) {
		case 'A':
		case 'a': {
			// inserisco nome e cognome
			System.out.println("Inserisci il nome dell'utente");
			String nome = input.nextLine();
			System.out.println("Inserisci il cognome dell'utente");
			String cognome = input.nextLine();
			UtenteAbbonato utenteAbbonato = new UtenteAbbonato(nome, cognome);
			//aggiungere cod utente nel vettore - vedere come fare
		}
		case 'N':
		case 'n':
			System.out.println("L'utente non è abbonato.");
			System.out.println("Sono disponibili delle riduzioni sul prezzo giornaliero\nInserisci l'eta' dell'utente");
			int eta = input.nextInt();
			UtenteNonAbbonato utenteNonAbbonato = new UtenteNonAbbonato(eta); 
		}
		

		// System.out.println("Aggiunto l'ingresso dell'utente" + );
	}

	// per utente abbonato: + nome utente

	// per non abbonato: + prezzo biglietto

	// visualizzare la lista degli ingressi di uno specifico giorno

	// visualizzare la lista degli ingressi di uno specifico mese in ORDINE

	// visualizzare l'elenco di tutti gli ingressi di uno specifico utente abbonato

	// visualizzare l’elenco degli incassi giornalieri di uno specifico mese

	// visualizzare l’elenco con il numero degli ingressi in abbonamento giornalieri
	// di uno specifico mese

}

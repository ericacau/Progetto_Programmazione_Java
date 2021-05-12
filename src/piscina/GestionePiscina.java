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

		System.out.println("Premi A se l'ingresso � di un utente ABBONATO o N se non � abbonato");
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
			// aggiungere cod utente nel vettore - vedere come fare
		}
		case 'N':
		case 'n':
			System.out.println("L'utente non � abbonato.");
			System.out.println("Sono disponibili delle riduzioni sul prezzo giornaliero\nInserisci l'eta' dell'utente");
			int eta = input.nextInt();
			UtenteNonAbbonato utenteNonAbbonato = new UtenteNonAbbonato(eta);
		}

		// System.out.println("Aggiunto l'ingresso dell'utente" + );
	}

	// per utente abbonato: + nome utente

	// per non abbonato: + prezzo biglietto

	// visualizzare la lista degli ingressi di uno specifico giorno
	public static ListaIngressi() {
		// dobbiamo fare tutto quel discorso dei vettori da scorrere? 
	}
	// visualizzare la lista degli ingressi di uno specifico mese in ORDINE
	public static IngressiMensiliOrdinati() {
		//ordinare gli ingressi x data 
	}
	// visualizzare l'elenco di tutti gli ingressi di uno specifico utente abbonato
	public static IngressiUtenteAbbonato(){

	}
	// visualizzare l�elenco degli incassi giornalieri di uno specifico mese
	public static IngressiMensili() {

	}
	/* visualizzare l�elenco con il numero degli ingressi in abbonamento giornalieri di uno specifico mese*/

	//visualizzare il numero di ingressi ridotti (nuovo metodo che potremmo aggiungere)

}

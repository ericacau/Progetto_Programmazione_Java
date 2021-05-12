package piscina;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Piscina {
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		UtenteAbbonato u1 = new UtenteAbbonato("Mario", "Rossi", 0);
		Vector <String> v = new Vector<String>();

		//prove per la lettura del file txt
		
	
		System.out.println("Benvenuto nel pannello di controllo degli ingressi della piscina \"La Sirena\". Ecco le possibili operazioni");
		
		//MENU'
		char scelta;
		do {
			System.out.println("\n");
			System.out.println("A - Leggere un file di testo con gli ingressi");
			System.out.println("B - Aggiungere un nuovo ingresso");
			System.out.println("C - Visualizzare gli ingressi di un giorno specifico");
			System.out.println("D - Visualizzare gli ingressi di un mese specifico");
			System.out.println("E - Visualizzare gli ingressi di un utente abbonato");
			System.out.println("F - Visualizzare gli incassi giornalieri di uno specifico mese");
			System.out.println("G - Visualizzare l'elenco con il numero di ingressi in abbonamento giornalieri di uno specifico mese");
			System.out.println("U - Uscita");
			System.out.println("\nCosa vuoi fare?");
			scelta = input.next().charAt(0);
			if(scelta == 'U') {
				System.out.println("Uscita.");
			}
			//servirà qualcosa che permetta di aggiungere utenti?
			switch (scelta) {
			case 'A' :
			case 'a' :{
				System.out.println("Leggo il file ingressi...");
				letturaIngressi.leggiFile();
			}
			case 'B' :
			case 'b' : {
				GestionePiscina.aggiungiIngresso();
				break;
				
			}
			case 'C' :
			case 'c' : {
				break;
				
			}
			case 'D' :
			case 'd' : {
				break;
				
			}
			case 'E' :
			case 'e' : {
				break;
				
			}
			case 'F' :
			case 'f' : {
				break;
				
			}
			case 'G' :
			case 'g' :
			{
				break;
				
			}
			
			default:
				throw new IllegalArgumentException("Hai inserito un carattere errato");
			}
		} while(scelta != 'U'|| scelta != 'u');

	}
}

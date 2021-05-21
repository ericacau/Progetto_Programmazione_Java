package piscina;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Piscina {
    public static void main(String[] args) {
		/* Crea un vettore 'ingresso' contenente
				- la data di ingresso (oggetto Date)
				- la descrizione
		- utente abbonato = nome e cognome
		- utente non abbonato = prezzo
		*/
        Scanner input = new Scanner(System.in);
        Vector<Ingressi> ingressi = new Vector<Ingressi>();

        /*-------MENU--------*/
        /*  l'utente in base alla lettera scelta dall'utente (da A a H) potrà effettuare le varie operazioni 
            sugli ingressi richiamando i metodi della classe GestionePiscina 
        */
        System.out.println("Benvenuto nel pannello di controllo degli ingressi della piscina \"La Sirena\". Ecco le possibili operazioni");
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
            System.out.println("H - Visualizzare l'elenco degli ingressi ridotti");
            System.out.println("U - Uscita");
            System.out.println("\nCosa vuoi fare?");
            scelta = input.next().charAt(0);
            if (scelta == 'U') {
                System.out.println("Uscita.");
            }
            //servirà qualcosa che permetta di aggiungere utenti?
            switch (scelta) {
                case 'A':
                case 'a':
                    System.out.println("Leggo il file ingressi...");
                    letturaIngressi.leggiFile();
                    break;
                case 'B':
                case 'b':
                    GestionePiscina nuovoIngresso = new GestionePiscina(ingressi);
                    //invoco il metodo per aggiungere l'ingresso
                    nuovoIngresso.aggiungiIngresso();
                    nuovoIngresso.visualizzaIngresso();
                    break;

                case 'C':
                case 'c':
                //    GestionePiscina ridotto = IngressiTOT.IngressiGiornalieri();
                    break;


                case 'D':
                case 'd':
                    break;

                case 'E':
                case 'e':
                    break;


                case 'F':
                case 'f':
                    break;


                case 'G':
                case 'g':

                    break;

                case 'H':
                case 'h':
                    
                    //GestionePiscina ridotti = ridotti.IngressiRidotti();

                    break;
                default:
                    throw new IllegalArgumentException("Hai inserito un carattere errato");
            }
        }
        while (scelta != 'U' || scelta != 'u');
     input.close();
    }
}

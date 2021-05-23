package piscina;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Piscina {
    public static void main(String[] args) {
		//Crea un vettore 'ingresso' contenente data e utenteAbbonato o non abbonato

        Scanner input = new Scanner(System.in);
        Vector<Ingressi> ingressi = new Vector<Ingressi>();
        ObjectInputStream inputStream;
        String nomeFile = "ingressiPiscina";

        //leggo il file in input ingressiPiscina.dat
        try {
            inputStream = new ObjectInputStream(new FileInputStream(nomeFile));
            ingressi = (Vector<Ingressi>) inputStream.readObject(); //conversione di tipo anche qui
            System.out.println("Lettura del file in corso...");
            inputStream.close();
        } catch (FileNotFoundException e){
            System.out.println("Errore. File non trovato");
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file di input." + nomeFile);
        } catch (ClassNotFoundException e) {
            System.out.println("Errore nella lettura degli ingressi da file.");
        }
        if(ingressi.isEmpty()){
            System.out.println("Il file ingressi è vuoto. Inserisci degli ingressi per poter salvare un nuovo file.");
        }
        /*-------MENU--------*/
        //l'utente in base alla lettera scelta dall'utente (da A a H) potrà effettuare le varie operazioni
        //    sugli ingressi richiamando i metodi della classe GestionePiscina

        GestionePiscina nuovoIngresso = new GestionePiscina(ingressi);
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
                    //invoco il metodo per aggiungere l'ingresso
                    nuovoIngresso.aggiungiIngresso();
                    nuovoIngresso.visualizzaIngresso();
                    break;
                case 'B':
                case 'b':
                    nuovoIngresso.IngressiGiornalieri();
                    break;
                case 'C':
                case 'c':
                    break;

                case 'D':
                case 'd':
                    System.out.println("Stai visualizzando gli ingressi di uno specifico utente abbonato");
                    nuovoIngresso.IngressiUtenteAbbonato();
                    break;


                case 'E':
                case 'e':
                    break;


                case 'F':
                case 'f':

                    break;

                case 'G':
                case 'g':
                    nuovoIngresso.IngressiRidotti();

                    break;
                default:
                    throw new IllegalArgumentException("Hai inserito un carattere errato");
            }
        }
        while (scelta != 'U' || scelta != 'u');
        input.close();
    }
}

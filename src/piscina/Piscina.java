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

        System.out.println("Benvenuto nel pannello di controllo degli ingressi della piscina \"La Sirena\"");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Leggo il file di ingressi di default della piscina...\n");
        //creo un oggetto nuovoIngresso per usare i metodi sugli ingressi
        GestionePiscina nuovoIngresso = new GestionePiscina(ingressi);

        //leggo il file in input ingressiPiscina
        try {
            inputStream = new ObjectInputStream(new FileInputStream(nomeFile));
            ingressi = (Vector<Ingressi>) inputStream.readObject(); //conversione di tipo anche qui
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Non è presente un file di ingressi! \n");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Errore nella lettura del file di input: " + nomeFile);
        } catch (ClassNotFoundException e) {
            System.out.println("Errore nella lettura degli ingressi da file.");
        }

        //controllo se il vettore è vuoto. Se ha degli ingressi, accedo al menù
        //se non vi sono ingressi, procedo direttamente all'aggiunta di un nuovo ingresso
        if (ingressi.isEmpty()) {
            System.out.println("Procedi all'inserimento di un ingresso prima di accedere al menu'.");
            nuovoIngresso.aggiungiIngresso();
        } else
            System.out.println("Il file di ingressi nella Piscina precedentemente salvato è stato caricato correttamente. Che cosa vuoi fare?");

        System.out.println("\nEcco le possibili operazioni che puoi compiere:");

        /*-------MENU--------*/
        //l'utente in base alla lettera scelta (da A a G), potrà effettuare le varie operazioni
        //    sugli ingressi richiamando i metodi della classe GestionePiscina
        System.out.println("Ingressi già inseriti:");
        nuovoIngresso.visualizzaIngresso();
        char scelta;
        do {
            System.out.println("");
            System.out.println("A - Aggiungere un nuovo ingresso");
            System.out.println("B - Visualizzare gli ingressi di un giorno specifico");
            System.out.println("C - Visualizzare gli ingressi di un mese specifico");
            System.out.println("D - Visualizzare gli ingressi di un utente abbonato");
            System.out.println("E - Visualizzare gli incassi giornalieri di uno specifico mese");
            System.out.println("F - Visualizzare l'elenco con il numero di ingressi in abbonamento giornalieri di uno specifico mese");
            System.out.println("G - Visualizzare gli ingressi senza abbonamento e con riduzione di uno specifico mese");
            System.out.println("S - Salva gli ingressi inseriti su file");
            System.out.println("U - Uscita");
            System.out.println("\nCosa vuoi fare?");
            scelta = input.next().charAt(0);
            //servirà qualcosa che permetta di aggiungere utenti?
            try {
                switch (scelta) {
                    case 'A':
                    case 'a':
                        //invoco il metodo per aggiungere l'ingresso
                        //ok!
                        nuovoIngresso.aggiungiIngresso();
                        nuovoIngresso.visualizzaIngresso();
                        break;
                    case 'B':
                    case 'b':
                        //ingressi giorno specifico IN ORDINE
                        //ok!
                        nuovoIngresso.IngressiGiornalieri();
                        break;
                    case 'C':
                    case 'c':
                        //ingressi mese specifico IN ORDINE
                        //ok!
                        nuovoIngresso.IngressiMensiliOrdinati();
                        break;
                    case 'D':
                    case 'd':
                        //ingressi specifico ABBONATO
                        //ok!
                        nuovoIngresso.IngressiUtenteAbbonato();
                        break;
                    case 'E':
                    case 'e':
                        //elenco incassi giornalieri mese specifico
                        //da scrivere
                        nuovoIngresso.IncassiMensili();
                        break;
                    case 'F':
                    case 'f':
                        // elenco con il numero degli ingressi in abbonamento giornalieri di uno specifico mese
                        nuovoIngresso.IngressiAbbonatiMensili();
                        break;

                    case 'G':
                    case 'g':
                        //ingressi con riduzione di uno specifico mese
                        //da sistemare
                        nuovoIngresso.IngressiRidotti();
                        break;
                    case 'S':
                    case 's':
                        System.out.println("Stai salvando le informazioni sugli ingressi.");
                        try {
                            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeFile));
                            outputStream.writeObject(ingressi);
                            outputStream.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("File non trovato");
                        } catch (IOException e) {
                            System.out.println("Errore nella scrittura del file");
                            System.out.println(e);
                        }
                        System.out.println("Gli ingressi sono stati salvati nel file " + nomeFile);
                        break;
                    case 'U':
                    case 'u':
                        System.out.println("Uscita in corso.");
                        break;
                    default:
                        System.out.println("Hai inserito un carattere errato");

                }
            } catch (InputMismatchException e) {
                System.out.println("Hai inserito un carattere errato");
                input.nextLine();
            }
        } while (scelta != 'U' || scelta != 'u');
        input.close();
    }
}
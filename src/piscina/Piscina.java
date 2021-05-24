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
        } catch (FileNotFoundException e) {
            System.out.println("Errore. File non trovato");
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file di input." + nomeFile);
        } catch (ClassNotFoundException e) {
            System.out.println("Errore nella lettura degli ingressi da file.");
        }
        if (ingressi.isEmpty()) {
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
            System.out.println("A - Aggiungere un nuovo ingresso");
            System.out.println("B - Visualizzare gli ingressi di un giorno specifico");
            System.out.println("C - Visualizzare gli ingressi di un mese specifico");
            System.out.println("D - Visualizzare gli ingressi di un utente abbonato");
            System.out.println("E - Visualizzare gli incassi giornalieri di uno specifico mese");
            System.out.println("F - Visualizzare l'elenco con il numero di ingressi in abbonamento giornalieri di uno specifico mese");
            System.out.println("G - Visualizzare gli ingressi senza abbonamento e con riduzione di uno specifico mese");
            System.out.println("S - Salva gli ingressi inseriti su fileS");
            System.out.println("U - Uscita");
            System.out.println("\nCosa vuoi fare?");
            scelta = input.next().charAt(0);
            //servirà qualcosa che permetta di aggiungere utenti?
            try {
                switch (scelta) {
                    case 'A':
                    case 'a':
                        //invoco il metodo per aggiungere l'ingresso
                        nuovoIngresso.aggiungiIngresso();
                        nuovoIngresso.visualizzaIngresso();
                        break;
                    case 'B':
                    case 'b':
                        //ingressi giorno specifico IN ORDINE
                        nuovoIngresso.IngressiGiornalieri();
                        break;
                    case 'C':
                    case 'c':
                        //ingressi mese specifico IN ORDINE
                        nuovoIngresso.IngressiMensiliOrdinati();
                        break;
                    case 'D':
                    case 'd':
                        //ingressi specifico ABBONATO
                        nuovoIngresso.IngressiUtenteAbbonato();
                        break;
                    case 'E':
                    case 'e':
                        //elenco incassi giornalieri mese specifico
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
                        }
                        System.out.println("Gli ingressi sono stati salvati nel file ingressiPiscina.dat!");
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
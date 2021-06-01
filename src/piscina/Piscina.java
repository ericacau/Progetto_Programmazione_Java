package piscina;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.StreamSupport;

public class Piscina {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /*Creo un vettore che conterrà gli oggetti ingressi
            sarà manipolato dai metodi della classe GestionePiscina
            inizialmente riempito da un file preesistente; se non presente verrà notificato all'utente che il
            file non è presente e che potrà essere salvato in un secondo momento dopo aver aggiunto gli ingressi
        */

        Vector<Ingressi> vettoreIngressi = new Vector<Ingressi>();
        ObjectInputStream inputStream;
        String nomeFile = "ingressiPiscina";

        System.out.println("Benvenuto nel pannello di controllo degli ingressi della piscina \"La Sirena\"");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Leggo il file di ingressi di default della piscina...\n");
<<<<<<< Updated upstream

        //creo un oggetto nuovoIngresso per usare i metodi sugli ingressi
        GestionePiscina nuovoIngresso = new GestionePiscina(vettoreIngressi);

        //leggo il file in input ingressiPiscina
=======
        
         //leggo il file in input ingressiPiscina
>>>>>>> Stashed changes
        try {
            inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomeFile)));
            vettoreIngressi = (Vector<Ingressi>) inputStream.readObject(); //conversione di tipo anche qui
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Non è presente un file di ingressi!");
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file di input: " + nomeFile);
        } catch (ClassNotFoundException e) {
            System.out.println("Errore nella lettura degli ingressi da file.");
        }

        //creo un oggetto Ingresso per usare i metodi sugli ingressi
        GestionePiscina Ingresso = new GestionePiscina(vettoreIngressi);

        /*controllo se il vettore è vuoto. Se ha degli ingressi, accedo al menù
           se non vi sono ingressi, procedo direttamente all'aggiunta di un nuovo ingresso*/
        if (vettoreIngressi.isEmpty()) {
            System.out.println("Procedi all'inserimento di un ingresso prima di accedere al menu'.");
<<<<<<< Updated upstream
            nuovoIngresso.aggiungiIngresso();
        } else
            System.out.println("Il file di ingressi nella Piscina precedentemente salvato è stato caricato correttamente. Che cosa vuoi fare?");
=======
            Ingresso.aggiungiIngresso();
        } else {
            System.out.println("Il file di ingressi nella Piscina precedentemente salvato è stato caricato correttamente.");
        }
        System.out.println("Ecco le possibili operazioni che puoi compiere.");
        System.out.println("Che cosa vuoi fare?");
>>>>>>> Stashed changes

        System.out.println("\nEcco le possibili operazioni che puoi compiere:");

        /*-------MENU--------*/
<<<<<<< Updated upstream
        //l'utente in base alla lettera scelta (da A a G), potrà effettuare le varie operazioni
        //    sugli ingressi richiamando i metodi della classe GestionePiscina
        System.out.println("Ingressi già inseriti:");
        nuovoIngresso.visualizzaIngresso();
        char scelta;
=======
        /*l'utente in base alla lettera scelta (da A a G), potrà effettuare le varie operazioni
         sugli ingressi richiamando i metodi della classe GestionePiscina*/
        int scelta;
        boolean menuAttivo = true;
>>>>>>> Stashed changes
        do {
            System.out.println(""); //forse da sostituire con i numeri
            System.out.println("1 - Aggiungere un nuovo ingresso");
            System.out.println("2 - Visualizzare gli ingressi di un giorno specifico");
            System.out.println("3 - Visualizzare gli ingressi di un mese specifico");
            System.out.println("4 - Visualizzare gli ingressi di un utente abbonato");
            System.out.println("5 - Visualizzare gli incassi giornalieri di uno specifico mese");
            System.out.println("6 - Visualizzare l'elenco con il numero di ingressi in abbonamento giornalieri di uno specifico mese");
            System.out.println("7 - Visualizzare gli ingressi senza abbonamento e con riduzione di uno specifico mese");
            System.out.println("8 - Leggi gli ingressi inseriti su file");
            System.out.println("9 - Salva gli ingressi inseriti su file");
            System.out.println("10 - Uscita");
            System.out.println("\nCosa vuoi fare?");

            scelta = input.nextInt();

            try {
                switch (scelta) {
                    case 1:
                        //invoco il metodo per aggiungere l'ingresso
                        Ingresso.aggiungiIngresso();
                        Ingresso.visualizzaIngresso();
                        break;
                    case 2:
                        //ingressi giorno specifico IN ORDINE
                        Ingresso.IngressiGiornalieri();
                        break;
                    case 3:
                        //ingressi mese specifico IN ORDINE
                        Ingresso.IngressiMensiliOrdinati();
                        break;
                    case 4:
                        //ingressi specifico ABBONATO
                        Ingresso.IngressiUtenteAbbonato();
                        break;
                    case 5:
                        //elenco incassi giornalieri mese specifico
                        Ingresso.IncassiMensili();
                        break;
                    case 6:
                        // elenco con il numero degli ingressi in abbonamento giornalieri di uno specifico mese
                        Ingresso.IngressiAbbonatiMensili();
                        break;
                    case 7:
                        //ingressi con riduzione di uno specifico mese
                        Ingresso.IngressiRidotti();
                        break;
<<<<<<< Updated upstream
                    case 'H':
                    case 'h':
                        try {
                            inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomeFile)));
                            vettoreIngressi = (Vector<Ingressi>) inputStream.readObject(); //conversione di tipo anche qui
                            inputStream.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("Non è presente un file di ingressi!");
                        } catch (IOException e) {
                            System.out.println(e);
                            System.out.println("Errore nella lettura del file di input: " + nomeFile);
                        } catch (ClassNotFoundException e) {
                            System.out.println("Errore nella lettura degli ingressi da file.");
                        }
                        System.out.println(vettoreIngressi.toString());
=======
                    case 8:
                        Ingresso.visualizzaIngresso();
                        System.out.println("");
>>>>>>> Stashed changes
                        break;
                    case 9:
                        System.out.println("Stai salvando le informazioni sugli ingressi...");
                        try {
                            ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeFile)));
                            outputStream.writeObject(vettoreIngressi);
                            outputStream.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("File non trovato");
                        } catch (IOException e) {
                            System.out.println("Errore nella scrittura del file");
                        }
                        System.out.println("Gli ingressi sono stati salvati nel file " + nomeFile);
                        break;
<<<<<<< Updated upstream
                    case 'U':
                    case 'u':
                        System.out.println("Uscita in corso.");
                        break;
                    default:
                        System.out.println("Hai inserito un carattere errato");

=======
                    case 10:
                        System.out.println("Uscita in corso...");
                        System.out.println("Salvo le informazioni");
                        menuAttivo = false;
                        break;
                    default:
                        System.out.println("Hai inserito un numero errato");
>>>>>>> Stashed changes
                }
            } catch (InputMismatchException e) {
                System.out.println("Hai inserito un numero errato");
                input.nextInt();
            }
        }
        //non esce dal programma anche se preme U
        while (scelta != 'U' || scelta != 'u');
        input.close();
    }
}
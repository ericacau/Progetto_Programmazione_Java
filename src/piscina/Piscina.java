package piscina;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.StreamSupport;

/*Main in cui viene:
    * Inizializzato il vettore di ingressi che verranno poi salvati in un file (ingressiPiscina)
    * Visualizzato il menu per la gestione degli ingressi con le varie opzioni da compiere su di essi
*/
public class Piscina {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
            /*Creo un vettore che conterrà gli oggetti ingressi
                * sarà manipolato dai metodi della classe GestionePiscina
                * inizialmente riempito da un file preesistente; se non presente verrà notificato all'utente che il
                    file non è presente e che potrà essere salvato in un secondo momento dopo aver aggiunto gli ingressi.*/
        Vector<Ingressi> vettoreIngressi = new Vector<Ingressi>();
        ObjectInputStream inputStream;

        String nomeFile = "ingressiPiscina";

        System.out.println("Benvenuto nel pannello di controllo degli ingressi della piscina \"La Sirena\"");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Leggo il file di ingressi di default della piscina...\n");

        //leggo il file in input ingressiPiscina
        try {
            inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomeFile)));
            vettoreIngressi = (Vector<Ingressi>) inputStream.readObject(); //conversione di tipo
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Non è presente un file di ingressi!");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Errore nella lettura del file di input: " + nomeFile);
        } catch (ClassNotFoundException e) {
            System.out.println("Errore nella lettura degli ingressi da file.");
        }
        //creo un oggetto Ingresso per usare i metodi sugli ingressi

        GestionePiscina Ingresso = new GestionePiscina(vettoreIngressi);

        //controllo se il vettore è vuoto. Se ha degli ingressi, accedo al menù
        //se non vi sono ingressi, procedo direttamente all'aggiunta di un nuovo ingresso
        if (vettoreIngressi.isEmpty()) {
            System.out.println("Procedi all'inserimento di un ingresso prima di accedere al menu'.");
            Ingresso.aggiungiIngresso();
        } else {
            System.out.println("Il file di ingressi nella Piscina precedentemente salvato è stato caricato correttamente.");
        }
        System.out.println("Ecco le possibili operazioni che puoi compiere:");


        /*-------MENU--------*/
        //l'utente in base al numero scelto, potrà effettuare le varie operazioni
        //sugli ingressi richiamando i metodi della classe GestionePiscina
        int scelta;
        boolean menuAttivo = true;
        do {
            System.out.println("");
            System.out.println("1 - Aggiungere un nuovo ingresso");
            System.out.println("2 - Visualizzare gli ingressi di un giorno specifico");
            System.out.println("3 - Visualizzare gli ingressi di un mese specifico");
            System.out.println("4 - Visualizzare gli ingressi di un utente abbonato");
            System.out.println("5 - Visualizzare gli incassi giornalieri di uno specifico mese");
            System.out.println("6 - Visualizzare l'elenco con il numero di ingressi in abbonamento giornalieri di uno specifico mese");
            System.out.println("7 - Leggi gli ingressi inseriti su file");
            System.out.println("8 - Salva gli ingressi inseriti su file");
            System.out.println("9 - Uscita");
            System.out.println("\nCosa vuoi fare?");
            try {
                scelta = input.nextInt();
                switch (scelta) {
                    case 1:
                        //invoco il metodo per aggiungere l'ingresso
                        System.out.println("Stai aggiungendo un nuovo ingresso\nInserisci le informazioni che ti verranno richieste.");
                        Ingresso.aggiungiIngresso();
                       // Ingresso.visualizzaIngresso();
                        break;
                    case 2:
                        System.out.println("Stai visualizzando gli ingressi di uno specifico giorno");
                        //ingressi giorno specifico IN ORDINE
                        Ingresso.IngressiGiornalieri();
                        break;
                    case 3:
                        //ingressi mese specifico IN ORDINE
                        System.out.println("Stai visualizzando gli ingressi di un mese specifico");
                        Ingresso.IngressiMensiliOrdinati();
                        break;
                    case 4:
                        //ingressi specifico ABBONATO
                        System.out.println("Stai visualizzando gli ingressi di uno specifico utente abbonato");
                        Ingresso.IngressiUtenteAbbonato();
                        break;
                    case 5:
                        //elenco incassi giornalieri mese specifico
                        System.out.println("Stai visualizzando gli incassi dei biglietti venduti in uno specifico mese");
                        Ingresso.IncassiMensili();
                        break;
                    case 6:
                        // elenco con il numero degli ingressi in abbonamento giornalieri di uno specifico mese
                        System.out.println("Stai visualizzando il numero di ingressi di persone abbonate effettuati in uno specifico mese");
                        Ingresso.IngressiAbbonatiMensili();
                        break;
                    case 7:
                        // Visualizzare tutti gli ingressi
                        System.out.println("Stai visualizzando gli ingressi salvati.\n");
                        Ingresso.visualizzaIngresso();
                        break;
                    case 8:
                        System.out.println("Stai salvando le informazioni sugli ingressi...\n");
                        salva(vettoreIngressi);
                        System.out.println("Gli ingressi sono stati salvati nel file " + nomeFile + "!");
                        break;
                    case 9:
                        System.out.println("Salvo le informazioni");
                        salva(vettoreIngressi);
                        menuAttivo = false;
                        System.out.println("Informazioni salvate.");
                        System.out.println("Uscita effettuata.");
                        break;
                    default:
                        System.out.println("Errore nella digitazione");
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Riprova ad inserire un numero da 1 a 10!");
                input.next();
            } catch (Exception e){
                System.out.println("Errore!");
            }
        } while (menuAttivo);
        input.close();
    }

//metodo ausiliario per effettuare il salvataggio sul vettore
    private static void salva(Vector v){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ingressiPiscina")));
            outputStream.writeObject(v);
            outputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file");
            System.out.println(e);
        }
    }
}
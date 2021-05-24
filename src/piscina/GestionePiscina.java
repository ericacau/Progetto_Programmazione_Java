package piscina;

import java.io.Serializable;
import java.sql.Array;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.lang.*;

public class GestionePiscina implements Serializable {
    /* inizializzo le seguenti variabili:
        - input: il nostro scanner per interagire con l'utente;
        - vettoreAbbonamenti: per aggiungere/rimuovere gli ingressi;
        - info: stringa per costruire le informazioni sull'ingresso (conterrà
                il prezzo o il nome/cognome dell'utente)
     */
    private Scanner input = new Scanner(System.in);
    private Vector<Ingressi> IngressiTOT;
    /*vettore IngressiTOT: contiene tutti gli ingressi definiti
    all'interno della classe Ingressi e delle sue estensioni*/

    // Costruttore del GestorePiscina che prende in input il vettore IngressiTOT definito sopra
    public GestionePiscina(Vector v1) {
        this.IngressiTOT = v1;
    }

    /*---------METODI--------*/

    /*Metodo AggiungiIngresso()
        * chiede all'utente di inserire una data richiamando il metodo chiediData
        * Chiedo all'utente di scegliere tra utente abbonato (opzione A/a) o non abbonato (N/n)
           e in base alla sua scelta aggiundo un ingressi abbonato o non abbonato richiamando
           i metodi delle classi utenteAbbonnato e utenteNonAbbonato
    */
    public void aggiungiIngresso() {
        //chiedo all'utente la data
        System.out.println("Stai aggiungendo un nuovo ingresso");
        LocalDate dataIngresso = chiediData();
        boolean controlloData = controllaFestivi(dataIngresso);
        if (controlloData) {
            System.out.println("Non puoi inserire un ingresso quando è chiusa la piscina.");
            System.out.println("Inserisci un'altra data");
            chiediData();
        }
        String info = "";
        System.out.println("Premi A se l'ingresso e' di un utente ABBONATO o N se non e' abbonato");

        char scelta;
        scelta = input.next().charAt(0);
        switch (scelta) {
            case 'A':
            case 'a':
                // inserisco nome e cognome
                System.out.println("Inserisci il nome dell'utente");
                String nome = input.nextLine();
                nome = input.nextLine();
                System.out.println("Inserisci il cognome dell'utente");
                //inserisco il cognome
                String cognome = input.nextLine();
                // creo un nuovo utente abbonato e un nuovo ingressi
                UtenteAbbonato utenteAbbonato = new UtenteAbbonato(nome, cognome);
                IngressiAbbonati nuovoIngressoAbbonati = new IngressiAbbonati(dataIngresso, utenteAbbonato);
                //inserisco l'ingresso appena creato nel vettore IngressiTOT
                IngressiTOT.add(nuovoIngressoAbbonati);
                System.out.println("Ingresso inserito");
                break;
            case 'N':
            case 'n':
                System.out.println("Hai selezionato \"utente non abbonato.\"");
                System.out.println("Sono disponibili delle riduzioni sul prezzo giornaliero\nInserisci l'eta' dell'utente");
                //controllare anche la data per vedere se è feriale o festivo
                int eta = input.nextInt();
                UtenteNonAbbonato utenteNonAbbonato = new UtenteNonAbbonato(eta);
                double prezzo = utenteNonAbbonato.getPrezzoBiglietto();
                //casto a string
                IngressiNonAbbonati nuovoIngressoNonAbbonati = new IngressiNonAbbonati(dataIngresso, utenteNonAbbonato, prezzo);
                IngressiTOT.add(nuovoIngressoNonAbbonati);
                System.out.println("Ingresso inserito");
                break;
        }
    }


    // visualizzare la lista degli ingressi di uno specifico mese in ORDINE di data
    Comparator<Ingressi> OrdinaIngressi = new Comparator<Ingressi>() {
        @Override
        public int compare(Ingressi i1, Ingressi i2) {
            return i1.getData().compareTo(i2.getData());
        }
    };

    public void IngressiMensiliOrdinati() {
        System.out.println("Inserisci il mese di cui vuoi sapere gli ingressi");
        int mese = input.nextInt();
        System.out.println("Inserisci l'anno di cui vuoi sapere gli ingressi");
        int anno = input.nextInt();
        Collections.sort(IngressiTOT, OrdinaIngressi);
        for (Ingressi ingresso : IngressiTOT) {
            ingresso.getData();
        }
        visualizzaIngresso();
    }


    // visualizzare la lista degli ingressi di uno specifico giorno (da finire di sistemare, non sono molto convinta )
    public void IngressiGiornalieri() {
        System.out.println("Inserisci la data di cui vuoi sapere gli ingressi");
        LocalDate d = chiediData();
        for (Ingressi i : IngressiTOT) {
            LocalDate dataCercata = i.getData();
            if (d == dataCercata) {
                //mi sa che va salvato tutto in un array e poi stampato. O qualcosa del genere
                Ingressi giornaliero = new Ingressi(d);
                System.out.println("Elenco ingressi di uno specifico giorno: " + giornaliero.toString());
            }
        }
    }

    //visualizza gli ingressi di uno specifico utente abbonato
    public void IngressiUtenteAbbonato() {
        System.out.println("Stai visualizzando gli ingressi di un utente abbonato.");
        System.out.println("Inserisci il nome dell'utente");
        String nomeUtente = input.nextLine();
        System.out.println("Inserisci il cognome dell'utente");
        String cognomeUtente = input.nextLine();
        IngressiAbbonati iA = null;
        for (Ingressi i : IngressiTOT) {
            if (i instanceof IngressiAbbonati) {
                iA = (IngressiAbbonati) i;
                UtenteAbbonato utente = iA.getUtente();
                String nomeUtenteAbbonato = utente.getNome();
                String cognomeUtenteAbbonato = utente.getCognome();
                //controllo il nome dell'utente e il cognome
                boolean controllo = utente.equals(nomeUtente, cognomeUtente);
                if (controllo) {
                    System.out.println(iA.toString());
                }
            }
        }
    }


    // visualizzare l'elenco degli incassi giornalieri di uno specifico mese
    public void IncassiMensili() {


    }

    /* visualizzare l'elenco con il numero degli ingressi in abbonamento giornalieri di uno specifico mese*/
    public void IngressiAbbonatiMensili() {

    }

    //visualizzare il numero di ingressi ridotti
    public void IngressiRidotti() {
        for (Ingressi i : IngressiTOT) {
            if (i instanceof IngressiNonAbbonati) {
                IngressiNonAbbonati nonabb = (IngressiNonAbbonati) i; //down cast
                UtenteNonAbbonato u2 = nonabb.getUtenteNA();
                if (u2.getStudente() || (u2.getRidottoBambiniEAnziani())) {
                    System.out.println("Ingresso ridotto studenti: " + u2.toString());
                    System.out.println("Ingresso ridotto bambini e anziani: " + u2.toString());
                }
            }
        }
    }


    // creo un metodo ausiliario per chiedere la data all'utente


    public void visualizzaIngresso() {
        System.out.println("----------------Elenco totale ingressi-----------------");
        for (Object ingresso : IngressiTOT) {
            System.out.println(ingresso);
        }
    }

    private boolean controllaFestivi(LocalDate data) {
        boolean weekend = false;
        if ((data.getDayOfWeek().equals(DayOfWeek.SUNDAY) ||
                data.getDayOfWeek().equals(DayOfWeek.MONDAY))) {
            weekend = true;
        }
        return weekend;
    }

    private LocalDate chiediData() {
        LocalDate data = null;
        boolean ok = true;
        do {
            System.out.println("Vuoi inserire un ingresso nel giorno attuale? [S] [N]");
            try {
                char scelta = input.next().charAt(0);
                if (scelta == 'S' || scelta == 's') {
                    data = LocalDate.now();
                }
                if (scelta == 'N' || scelta == 'n') {
                    System.out.println("Inserisci una data in formato DD/MM/YYYY");
                    input.nextLine();
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Inserisci S o N");
                ok = false;
            }

            try {
                String d1 = input.nextLine();
                // inserire un controllo sulla correttezza della data (try catch)
                DateTimeFormatter formattaData = DateTimeFormatter.ofPattern("dd/M/yyyy");
                data = LocalDate.parse(d1, formattaData);
            } catch (DateTimeException e) {
                System.out.println("Hai inserito una data errata. Controlla");
            }
        } while (!ok);
        return data;
    }
}
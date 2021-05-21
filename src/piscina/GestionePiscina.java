package piscina;

import java.time.DayOfWeek;
import java.util.*;

import javax.xml.crypto.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestionePiscina {
    /* inizializzo le seguenti variabili:
        - input: il nostro scanner per interagire con l'utente;
        - vettoreAbbonamenti: per aggiungere/rimuovere gli ingressi;
        - info: stringa per costruire le informazioni sull'ingresso (conterrà
                il prezzo o il nome/cognome dell'utente)
     */
    private Scanner input = new Scanner(System.in);
    protected Vector<Ingressi> IngressiTOT; 
    /*vettore IngressiTOT: contiene tutti gli ingressi definiti 
    all'interno della classe Ingressi e delle sue estensioni*/

    // Costruttore del GestorePiscina che prende in input il vettore IngressiTOT definito sopra
    public GestionePiscina(Vector v1) {
        this.IngressiTOT = v1;
    }

    /*---------METODI--------*/

    /*Metodo AggiungiIngresso()
        * chiede all'utente di inserire una data richiamando il metodo chiediData
        * Chiedo all'utente di scegliere tra utente abbonato (opizione A/a) o non abbonato (N/n)
           e in base alla sua scelta aggiundo un ingressi abbonato o non abbonato richiamando 
           i metodi delle classi utenteAbbonnato e utenteNonAbbonato

    */
    public void aggiungiIngresso() {
        //chiedo all'utente la data
        System.out.println("Stai aggiungendo un nuovo ingresso");
        LocalDate dataIngresso = chiediData();
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
                utenteNonAbbonato.setPrezzoBiglietto();
                double prezzo = utenteNonAbbonato.getPrezzoBiglietto();
                //casto a string
                IngressiNonAbbonati nuovoIngressoNonAbbonati = new IngressiNonAbbonati(dataIngresso, utenteNonAbbonato, prezzo);
                IngressiTOT.add(nuovoIngressoNonAbbonati);
                System.out.println("Ingresso inserito");
        }
    }


    
    // visualizzare la lista degli ingressi di uno specifico mese in ORDINE di data

    public void IngressiMensiliOrdinati() {

    }

    // visualizzare la lista degli ingressi di uno specifico giorno (da finire di sistemare, non sono molto convinta )
    private void IngressiGiornalieri() {
        System.out.println("Inserisci la data di cui vuoi sapere gli ingressi");
        LocalDate d = chiediData();
        
        LocalDate data = Ingressi.getData();
        if (data  == d) {
            Ingressi giornaliero = new Ingressi(d);
            System.out.println("Elenco ingressi di uno specifico giorno: " +giornaliero.toString());
        }
    }

    // visualizzare l'elenco di tutti gli ingressi di uno specifico utente abbonato
    public void IngressiUtenteAbbonato(String nomeInserito, String cognomeInserito) {


    }

    // visualizzare l'elenco degli incassi giornalieri di uno specifico mese
    public void IncassiMensili() {


    }

    /* visualizzare l'elenco con il numero degli ingressi in abbonamento giornalieri di uno specifico mese*/
    public void IngressiAbbonatiMensili() {

    }

    //visualizzare il numero di ingressi ridotti
    public  void  IngressiRidotti() {
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
    private LocalDate chiediData() {
        LocalDate data;
        System.out.println("Vuoi inserire un ingresso nel giorno attuale? [S] [N]");
        char scelta = input.next().charAt(0);
        if (scelta == 'S' || scelta == 's') {
            data = LocalDate.now();
        } else{ System.out.println("Inserisci una data in formato DD/MM/YYYY");
        String d1 = input.nextLine();
        // inserire un controllo sulla correttezza della data (try catch)
        DateTimeFormatter formattaData = DateTimeFormatter.ofPattern("d/MM/yyyy");
        data = LocalDate.parse(d1, formattaData);
    }
        boolean controlloData = controllaFestivi(data);
        if(!controlloData)

    {
        System.out.println("Non puoi inserire un ingresso quando è chiusa la piscina");
    }
    //controllare anche la data per vedere se è feriale o festivo
        return data;
}

    private boolean controllaFestivi(LocalDate data) {
        boolean weekend = false;

        switch (data.getDayOfWeek()) {
            case SUNDAY:
                weekend = true;
                break;
            case MONDAY:
                //ECCEZIONE2
                weekend = true;
                break;
        }
        if data
        return weekend;
    }

    public void visualizzaIngresso() {
        System.out.println("----------------Elenco totale ingressi-----------------");

        for (Object ingresso : IngressiTOT) {
            System.out.println(ingresso);
        }
    }


}

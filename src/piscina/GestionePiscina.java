package piscina;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestionePiscina {
	/* inizializzo le seguenti variabili:
	 		- input: il nostro scanner per interagire con l'utente;
	 		- vettoreAbbonamenti: per aggiungere/rimuovere gli ingressi;
	 		- info: stringa per costruire le informazioni sull'ingresso (conterrà
	 				il prezzo o il nome/cognome dell'utente.
	 */

    private Scanner input = new Scanner(System.in);
    private Vector <Ingressi> IngressiTOT;

    // Costruttore del GestorePiscina
    public GestionePiscina(Vector v1) {
        this.IngressiTOT = v1;
    }

    public void aggiungiIngresso() {
        //chiedo all'utente la data
        System.out.println("Stai aggiungendo un nuovo ingresso");
        System.out.println("Inserisci una data in formato DD/MM/YYYY");
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
                String cognome = input.nextLine();
                UtenteAbbonato utenteAbbonato = new UtenteAbbonato(nome, cognome);
                IngressiAbbonati nuovoIngressoAbbonati = new IngressiAbbonati(dataIngresso, utenteAbbonato);
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


    /***METODI***/
       // visualizzare la lista degli ingressi di uno specifico mese in ORDINE
    public void IngressiMensiliOrdinati() {

    }

    // visualizzare la lista degli ingressi di uno specifico giorno
    public void IngressiGiornalieri() {

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

    //visualizzare il numero di ingressi ridotti (nuovo metodo che potremmo aggiungere)
    public void IngressiRidotti() {

    }


    // creo un metodo ausiliario per chiedere la data all'utente
    private LocalDate chiediData() {
        String d1 = input.nextLine();
        // inserire un controllo sulla correttezza della data (try catch)
        DateTimeFormatter formattaData = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate data = LocalDate.parse(d1, formattaData);
        //controllare anche la data per vedere se è feriale o festivo
        return data;
    }

    public void visualizzaIngresso() {
        System.out.println("----------------Elenco totale ingressi-----------------");
        for (Object ingresso : IngressiTOT) {
            System.out.println(ingresso);
        }
    }
}

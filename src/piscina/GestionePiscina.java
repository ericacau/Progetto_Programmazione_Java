package piscina;

import java.io.Serializable;
import java.sql.Array;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.lang.*;

public class GestionePiscina {

    //variabili d'istanza
    private Scanner input = new Scanner(System.in);
    private Vector<Ingressi> IngressiTOT;
    private DateTimeFormatter formattaData = DateTimeFormatter.ofPattern("d/MM/yyyy");


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
    public void aggiungiIngresso()  {
        LocalDate dataIngresso = chiediData();
        boolean controlloData = controllaData(dataIngresso);
        //throw exception Piscina chiusa

        try  {
            if (controlloData) {
                System.out.println("Non puoi inserire un ingresso quando è chiusa la piscina.");
                System.out.println("Inserisci un'altra data");
                throw new PiscinaChiusa();

            }
        } catch (PiscinaChiusa e ) {

        System.out.println(e.getMessage());
        }


    boolean temperaturaok = true;
        if((dataIngresso.getYear()==2020)&&((dataIngresso.getMonthValue()>=06))||
                ((dataIngresso.getYear()==2021)&&((dataIngresso.getMonthValue()>=05))))
    {
        System.out.print("Prima di inserire l'ingresso e' necessario controllare la temperatura dell'utente.\nInserisci la temperatura\n");
        double temperatura = input.nextDouble();
        temperaturaok = controllaTemperatura(temperatura);
    }
        if(temperaturaok)

    {
        System.out.println("Premi A se l'ingresso e' di un utente ABBONATO o N se non e' abbonato");
        char scelta;
        scelta = input.next().charAt(0);
        switch (scelta) {
            case 'A':
            case 'a':
                System.out.println("Inserisci il nome dell'utente");
                String nome = input.nextLine();
                //rimuovo lo spazio dopo l'inserimento del nome
                nome = input.nextLine();
                System.out.println("Inserisci il cognome dell'utente");
                String cognome = input.nextLine();
                // creo un nuovo utente abbonato e un nuovo ingresso
                UtenteAbbonato utenteAbbonato = new UtenteAbbonato(nome, cognome);
                IngressiAbbonati nuovoIngressoAbbonati = new IngressiAbbonati(dataIngresso, utenteAbbonato);                    //inserisco l'ingresso appena creato nel vettore IngressiTOT
                IngressiTOT.add(nuovoIngressoAbbonati);
                System.out.println("Ingresso inserito!");
                break;
            case 'N':
            case 'n':
                System.out.println("Hai selezionato \"utente non abbonato.\"");
                System.out.println("\nSono disponibili delle riduzioni sul prezzo giornaliero\nInserisci l'eta' dell'utente");
                int eta = input.nextInt();
                UtenteNonAbbonato utenteNonAbbonato = new UtenteNonAbbonato(eta);
                double prezzo = utenteNonAbbonato.getPrezzoBiglietto();
                IngressiNonAbbonati nuovoIngressoNonAbbonati = new IngressiNonAbbonati(dataIngresso, utenteNonAbbonato, prezzo);
                IngressiTOT.add(nuovoIngressoNonAbbonati);
                System.out.println("Ingresso inserito");
                break;
        }
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
        LocalDate ingressiMeseSpecifico = inserisciMese();
        //ordino il vettore
        Collections.sort(IngressiTOT, OrdinaIngressi);
        //mi calcolo quanti giorni ha il mese passato come intero
        for (Ingressi ingresso : IngressiTOT) {
            LocalDate dataIngresso = ingresso.getData();
            if (dataIngresso.equals(ingressiMeseSpecifico)) {
                System.out.println(ingresso);
            }
        }
    }


    public void IngressiGiornalieri() {
        System.out.println("Inserisci il giorno di cui vuoi sapere gli ingressi");
        //casto come string per leggere lo 0 iniziale
        String giornoInserito = input.next();
        System.out.println("Inserisci il mese di cui vuoi sapere gli ingressi");
        String meseInserito = input.next();
        System.out.println("Inserisci l'anno di cui vuoi sapere gli ingressi");
        int annoInserito = input.nextInt();
        String ingrMese = giornoInserito + "/" + meseInserito + "/" + annoInserito;
        LocalDate ingressiGiornalieri = LocalDate.parse(ingrMese, formattaData);
        Collections.sort(IngressiTOT, OrdinaIngressi);
        for (Ingressi i : IngressiTOT) {
            LocalDate dataNegliIngressi = i.getData();
            if (dataNegliIngressi.equals(ingressiGiornalieri)) {
                System.out.println(i);
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
        Collections.sort(IngressiTOT, OrdinaIngressi);
        for (Ingressi i : IngressiTOT) {
            if (i instanceof IngressiAbbonati) {
                iA = (IngressiAbbonati) i;
                UtenteAbbonato utente = iA.getUtente();
                String nomeUtenteAbbonato = utente.getNome();
                String cognomeUtenteAbbonato = utente.getCognome();
                //controllo il nome dell'utente e il cognome
                boolean controllo = utente.equals(nomeUtente, cognomeUtente);
                if (controllo) {
                    System.out.println(iA);
                }
            }
        }
    }


    // visualizzare l'elenco degli incassi giornalieri di uno specifico mese
    public void IncassiMensili() {
//ordino prima di tutto il vettore
        Collections.sort(IngressiTOT, OrdinaIngressi);
        LocalDate meseSpecifico = inserisciMese();
        //forse conviene un metodo?
        Month mese = meseSpecifico.getMonth();
        int anno = meseSpecifico.getYear();

        YearMonth annoEMese = YearMonth.of(anno, mese);
        int giornidelMese = annoEMese.lengthOfMonth();

        String stampaTitolo = mese.getDisplayName(TextStyle.FULL, Locale.ITALIAN) + " " + anno;
        System.out.println("Incassi del mese " + stampaTitolo);
        //j = 1 perché useremo j per stampare il numero del giorno del mese
        double incassoGiornaliero = 0;
        for (int j = 1; j < giornidelMese + 1; j++) {
            incassoGiornaliero = 0;
            for (Ingressi i : IngressiTOT) {
                if (i.getData().equals(meseSpecifico)) {
                    if (i instanceof IngressiNonAbbonati) {
                        IngressiNonAbbonati ingressoNonAbbonato = (IngressiNonAbbonati) i;
                        UtenteNonAbbonato uNonAbbonato = ingressoNonAbbonato.getUtenteNA();
                        double bigliettoUtente = uNonAbbonato.getPrezzoBiglietto();
                        incassoGiornaliero += bigliettoUtente;
                    }
                }
            }
            meseSpecifico = meseSpecifico.plusDays(1);
            System.out.println("Giorno " + j + ":\t\t" + incassoGiornaliero);
        }
    }

    /* visualizzare l'elenco con il numero degli ingressi in abbonamento giornalieri di uno specifico mese*/
    public void IngressiAbbonatiMensili() {
        Collections.sort(IngressiTOT, OrdinaIngressi);
        LocalDate meseSpecifico = inserisciMese();
        //forse conviene un metodo?
        Month mese = meseSpecifico.getMonth();
        int anno = meseSpecifico.getYear();
        YearMonth annoEMese = YearMonth.of(anno, mese);
        int giornidelMese = annoEMese.lengthOfMonth();
        String stampaTitolo = mese.getDisplayName(TextStyle.FULL, Locale.ITALIAN) + " " + anno;
        System.out.println("ELENCO DEI SOLI INGRESSI ABBONATI DEL MESE " + stampaTitolo.toUpperCase());
        //j = 1 perché useremo j per stampare il numero del giorno del mese
        int numeroIngressi = 0;
        for (int j = 1; j < giornidelMese + 1; j++) {
            numeroIngressi = 0;
            for (Ingressi i : IngressiTOT) {
                if (i.getData().equals(meseSpecifico)) {
                    if (i instanceof IngressiAbbonati) {
                        IngressiAbbonati ingressoAbbonati = (IngressiAbbonati) i;
                        numeroIngressi++;
                    }
                }
            }
            meseSpecifico = meseSpecifico.plusDays(1);
            System.out.println("Giorno " + j + ":\t\t" + numeroIngressi);
        }
    }

    //visualizzare il numero di ingressi ridotti
    public void IngressiRidotti() {
        for (Ingressi i : IngressiTOT) {
            if (i instanceof IngressiNonAbbonati) {
                IngressiNonAbbonati nonabb = (IngressiNonAbbonati) i; //downcast
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

    private boolean controllaData(LocalDate data) {
        boolean chiusura = false;
        if ((data.getDayOfWeek().equals(DayOfWeek.SUNDAY) ||
                data.getDayOfWeek().equals(DayOfWeek.MONDAY))) {
            chiusura = true;
        }
        if ((data.getYear() == 2020) && ((data.getMonthValue() == 03) || (data.getMonthValue() == 04) || (data.getMonthValue() == 05))) {
            chiusura = true;
        }
        return chiusura;
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
                    String d1 = input.nextLine();
                    data = LocalDate.parse(d1, formattaData);
                }
            } catch (InputMismatchException e) {
                //input.nextLine();
                System.out.println("Inserisci S o N");
                ok = false;
            }
        } while (!ok);
        return data;
    }

    /* metodo che controlla la temperatura dell'utente prima di entrare in piscina (introdotta per l'emergenza Covid-19)*/
    private boolean controllaTemperatura(double temperatura) {
        boolean temperaturaOK = true;
        boolean ok = true;
        do {
            try {
                if ((temperatura >= 37.0) && (temperatura <= 41)) {
                    //temperaturaOK = false;
                    System.out.println("Siamo spiacenti, ma la sua temperatura e' superiore a 37 gradi e, come descritto nel protocollo anti-covid, l'accesso non e' consentito");
                    temperaturaOK = false;
                    break;
                }
                if ((temperatura <= 34) || (temperatura > 41)) {
                    System.out.println("Il valore della temperatura non e' corretto, misurare nuovamente la temperatura");
                    temperatura = input.nextDouble();
                    ok = false;
                } else {
                    System.out.println("La sua temperatura e' inferiore a 37 gradi, accesso consentito");
                    ok = true;
                    temperaturaOK = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Riprova con un altro carattere.");
            }
        } while (!ok);
        return temperaturaOK;
    }

    private LocalDate inserisciMese() {
        System.out.println("Inserisci il mese di cui vuoi sapere gli ingressi");
        //casto come string per leggere lo 0
        String meseInserito = input.next();
        System.out.println("Inserisci l'anno di cui vuoi sapere gli ingressi");
        int annoInserito = input.nextInt();
        String ingrMese = "01/" + meseInserito + "/" + annoInserito;
        LocalDate ingressiDelMese = LocalDate.parse(ingrMese, formattaData);
        return ingressiDelMese;
    }
}
package piscina;

import Exeption.PiscinaChiusaException;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;



public class GestionePiscina {

    //variabili d'istanza
    private Scanner input = new Scanner(System.in);
    private Vector<Ingressi> IngressiTOT;
    private DateTimeFormatter formattaData = DateTimeFormatter.ofPattern("d/MM/yyyy");

    private LocalDate chiusura1 = LocalDate.parse("2020-03-10");
    private LocalDate apertura1 = LocalDate.parse("2020-07-01");
    private LocalDate chiusura2 = LocalDate.parse("2020-10-20");
    private LocalDate apertura2 = LocalDate.parse("2021-05-25");
    public int ingressiPrenotati = 0; // var contatore che salva gli ingressi prenotati

    // Costruttore del GestorePiscina che prende in input il vettore IngressiTOT definito sopra
    public GestionePiscina(Vector v1) {
        this.IngressiTOT = v1;
    }

    // Creo un comparator per ordinare gli ingressi
    Comparator<Ingressi> OrdinaIngressi = new Comparator<Ingressi>() {
        @Override
        public int compare(Ingressi i1, Ingressi i2) {
            return i1.getData().compareTo(i2.getData());
        }
    };
    /*---------METODI--------*/
    /*Metodo AggiungiIngresso()
        * chiede all'utente di inserire una data richiamando il metodo chiediData
        * Chiedo all'utente di scegliere tra utente abbonato (opzione A/a) o non abbonato (N/n)
           e in base alla sua scelta aggiundo un ingressi abbonato o non abbonato richiamando
           i metodi delle classi utenteAbbonnato e utenteNonAbbonato
    */

    public void aggiungiIngresso() {
        //chiedo di inserire la data e ne controllo la validità
        LocalDate dataIngresso = chiediData();
        boolean DataOK= true;
        boolean temperaturaOk = true;
        do {
            try {
                //verifico l'apertura della piscina. Se la piscina è chiusa, richiamo l'eccezione apposita
                boolean piscinaChiusa = ChiusuraPiscina(dataIngresso);
                if (piscinaChiusa) {
                    DataOK = false;
                    throw new PiscinaChiusaException(dataIngresso);
                } else {
                    System.out.println("La piscina è aperta! Puoi procedere all'inserimento dell'ingresso!");
                    DataOK = false;
                    if ((dataIngresso.isAfter(apertura1) && dataIngresso.isBefore(chiusura1)) ||
                            (dataIngresso.isAfter(apertura2) && dataIngresso.isBefore(chiusura2))) {
                        System.out.print("Prima di inserire l'ingresso e' necessario controllare la temperatura dell'utente.\nInserisci la temperatura\n");
                        double temperatura = input.nextDouble();
                        temperaturaOk = controllaTemperatura(temperatura);
                    }
                    if (temperaturaOk) {
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
                                IngressiAbbonati nuovoIngressoAbbonati = new IngressiAbbonati(dataIngresso, utenteAbbonato);
                                //inserisco l'ingresso appena creato nel vettore IngressiTOT
                                IngressiTOT.add(nuovoIngressoAbbonati);
                                System.out.println("Ingresso inserito!");
                                break;
                            case 'N':
                            case 'n':
                                System.out.println("Hai selezionato \"utente non abbonato.\"");
                                System.out.println("\nSono disponibili delle riduzioni sul prezzo giornaliero\nInserisci l'eta' dell'utente");
                                int eta = input.nextInt();
                                UtenteNonAbbonato utenteNonAbbonato = new UtenteNonAbbonato(eta);
                                controlloEta(eta);
                                double prezzo = utenteNonAbbonato.getPrezzoBiglietto();
                                IngressiNonAbbonati nuovoIngressoNonAbbonati = new IngressiNonAbbonati(dataIngresso, utenteNonAbbonato, prezzo);
                                IngressiTOT.add(nuovoIngressoNonAbbonati);
                                System.out.println("Ingresso inserito");
                                break;
                        }
                    } else
                        DataOK = false;
                }
            } catch (PiscinaChiusaException e) {
                System.out.println(e.getMessage());
            }
        } while (DataOK);

    }

    //metodo per visualizzare tutti gli ingressi di uno specifico mese
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

    //metodo per visualizzare tutti gli ingressi di uno specifico giorno
    public void IngressiGiornalieri() {
        Collections.sort(IngressiTOT, OrdinaIngressi);
        System.out.println("Inserisci il giorno di cui vuoi sapere gli ingressi");
        //casto come string per leggere lo 0 iniziale
        int giornoInserito = input.nextInt();
        System.out.println("Inserisci il mese di cui vuoi sapere gli ingressi");
        int meseInserito = input.nextInt();
        System.out.println("Inserisci l'anno di cui vuoi sapere gli ingressi");
        int annoInserito = input.nextInt();
        //controllo che il mese abbia lo 0 iniziale se compreso tra GEN e SEPT e costruisco la stringa
        String ingrMese = controllaGiornoMese(giornoInserito, meseInserito, annoInserito);
        LocalDate ingressiGiornalieri = LocalDate.parse(ingrMese, formattaData);
        System.out.println("INGRESSI DEL GIORNO " + ingressiGiornalieri);
        for (Ingressi i : IngressiTOT) {
            LocalDate dataNegliIngressi = i.getData();
            if (dataNegliIngressi.equals(ingressiGiornalieri)) {
                System.out.println(i);
            }
        }
    }

    //metodo per visualizzare tutti gli ingressi di uno specifico utente abbonato
    public void IngressiUtenteAbbonato() {
        Collections.sort(IngressiTOT, OrdinaIngressi);
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
                    System.out.println(iA);
                }
            }
        }
    }

    // metodo per visualizzare l'elenco degli incassi giornalieri di uno specifico mese
    public void IncassiMensili() {
        //ordino il vettore
        Collections.sort(IngressiTOT, OrdinaIngressi);
        LocalDate meseSpecifico = inserisciMese();
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

    //metodo per visualizzare l'elenco con il numero degli ingressi in abbonamento giornalieri di uno specifico mese
    public void IngressiAbbonatiMensili() {
        Collections.sort(IngressiTOT, OrdinaIngressi);
        LocalDate meseSpecifico = inserisciMese();
        //forse conviene un metodo?
        Month mese = meseSpecifico.getMonth();
        int anno = meseSpecifico.getYear();
        YearMonth annoEMese = YearMonth.of(anno, mese);
        int giornidelMese = annoEMese.lengthOfMonth();
        //stampo l'intestazione
        String stampaTitolo = mese.getDisplayName(TextStyle.FULL, Locale.ITALIAN) + " " + anno;
        System.out.println("ELENCO DEI SOLI INGRESSI DI ABBONATI DEL MESE " + stampaTitolo.toUpperCase());
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

    //metodo per visualizzare il numero di ingressi ridotti
    public void IngressiRidotti() {
        int contaRidotti = 0;
        for (Ingressi i : IngressiTOT) {
            if (i instanceof IngressiNonAbbonati) {
                IngressiNonAbbonati nonabb = (IngressiNonAbbonati) i; //downcast
                UtenteNonAbbonato u2 = nonabb.getUtenteNA();
                if (u2.getStudente() || (u2.getRidottoBambiniEAnziani())) {
                    System.out.println("Ingresso ridotto studenti: " + u2.toString());
                    System.out.println("Ingresso ridotto bambini e anziani: " + u2.toString());
                    contaRidotti++;
                }
            }
        }
        System.out.println("Totale ingressi ridotti: " + contaRidotti);
    }


    // creo un metodo ausiliario per chiedere la data all'utente
    private LocalDate chiediData() {
        LocalDate data = null;
        boolean ok = true;
        do {
            System.out.println("Vuoi inserire un ingresso nel giorno attuale? [S] [N]");
            try {
                char scelta = input.next().charAt(0);
                if (scelta == 'S' || scelta == 's') {
                    data = LocalDate.now();
                    ok = false;
                }
                if (scelta == 'N' || scelta == 'n') {
                    System.out.println("Inserisci una data in formato DD/MM/YYYY");
                    input.nextLine();
                    String d1 = input.nextLine();
                    data = LocalDate.parse(d1, formattaData);
                    ok = false;
                }
            } catch (InputMismatchException e) {
                //input.nextLine();
                System.out.println("Inserisci S o N");
                ok = true;
            } catch (DateTimeParseException e) {
                System.out.println("Inserisci la data in formato gg/mm/aaaa. Esempio: 10/02/2020");
            }
        } while (ok);
        return data;
    }


    private boolean ChiusuraPiscina(LocalDate data) {
        boolean chiusura = false;
        //definisco i periodi di chiusura

        try {
            //controllo che la data inserita non cada di domenica o di lunedi'
            //giorni di chiusura della piscina
            if ((data.getDayOfWeek().equals(DayOfWeek.SUNDAY) ||
                    data.getDayOfWeek().equals(DayOfWeek.MONDAY))) {
                chiusura = true;
                throw new PiscinaChiusaException();
            }
            //date di chiusura piscina per covid
            if (((data.isAfter(chiusura1) && data.isBefore(apertura1)) ||
                    (data.isAfter(chiusura2) && data.isBefore(apertura2)))) {
                chiusura = true;
                throw new PiscinaChiusaException();
            }
            //si assume che la piscina abbia aperto nel 2015
            if ((data.getYear() < 2015)) {
                chiusura = true;
                throw new PiscinaChiusaException();
            }
        } catch (PiscinaChiusaException e) {
            System.out.println("Inserire un ingresso in un'altra data.");
        }
        return chiusura;
    }


    /* metodo ausiliario che controlla la temperatura dell'utente prima di entrare in piscina (introdotta per l'emergenza Covid-19)*/
    private boolean controllaTemperatura(double temperatura) {
        boolean temperaturaOK = true;
        boolean ok = true;
        do {
            try {
                if ((temperatura >= 37) && (temperatura <= 41)) {
                    //temperaturaOK = false;
                    System.out.println("Siamo spiacenti, ma la sua temperatura e' superiore a 37 gradi e, " +
                            "come descritto nel protocollo anti-covid, l'accesso non e' consentito");
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
        LocalDate ingressiDelMese = null;
        System.out.println("Inserisci il mese di cui vuoi sapere gli ingressi in formato mm (es. 01 per gennaio, 02 per febbraio)");
        //casto come string per leggere lo 0
        int meseInserito = input.nextInt();
        System.out.println("Inserisci l'anno di cui vuoi sapere gli ingressi");
        int annoInserito = input.nextInt();
        String ingrMese = controllaMese(meseInserito, annoInserito);
        ingressiDelMese = LocalDate.parse(ingrMese, formattaData);
        return ingressiDelMese;
    }


    // Metodo ausiliario che controlla che l'utente abbia inserito lo 0
    // nei mesi compresi tra gennaio e settembre
    // se non lo ha messo, viene aggiunto in automatico
    private String controllaMese(int meseInserito, int annoInserito) {
        String ingrMese = "";
        if (meseInserito >= 1 && meseInserito <= 9) {
            ingrMese = "01/0" + meseInserito + "/" + annoInserito;
        } else {
            ingrMese = "01/" + meseInserito + "/" + annoInserito;
        }
        return ingrMese;
    }

    private String controllaGiornoMese(int giornoInserito, int meseInserito, int annoInserito) {
        String ingrMese = "";
        if ((meseInserito >= 1 && meseInserito <= 9) || ((giornoInserito>=1) &&
                giornoInserito<=9)){
            ingrMese = "0"+giornoInserito+"/0" + meseInserito + "/" + annoInserito;
        } else {
            ingrMese = giornoInserito + meseInserito + "/" + annoInserito;
        }
        return ingrMese;
    }

    //Metodo che controlla la correttezza dell'eta'
    public boolean controlloEta(int eta) {
        boolean etaOK = true;
        if ((eta <= 0) || (eta >= 112)) {
            System.out.println("Il valore inserito non e' corretto, inserire nuovamente l'eta' ");
            etaOK = false;
        }
        return etaOK;
    }

    //metodi ausiliari per la stampa
    public void visualizzaIngresso() {
        System.out.println("----------------Elenco totale ingressi-----------------");
        for (Object ingresso : IngressiTOT) {
            System.out.println(ingresso);
        }
    }

}
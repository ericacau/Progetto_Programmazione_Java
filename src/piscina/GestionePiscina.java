package piscina;

import Exception.DataErrataException;
import Exception.PiscinaChiusaException;
import Exception.EtaErrataException;

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


    public GestionePiscina(Vector v1) {
        this.IngressiTOT = v1;
    }


    // creo un'interfaccia Comparator per gestire l'ordinamento delle date

    // 1 nello switch
    public void aggiungiIngresso() {
        //chiedo di inserire la data e ne controllo la validità
        LocalDate dataIngresso = chiediData();
        boolean DataOK = true;
        char scelta;
        boolean temperaturaOk = true;
        do {
            try {
                //verifico l'apertura della piscina. Se la piscina è chiusa, richiamo l'eccezione apposita
                boolean piscinaChiusa = ChiusuraPiscina(dataIngresso);
                if (piscinaChiusa) {
                    //imposto a false per bloccare il do while
                    DataOK = false;
                    throw new PiscinaChiusaException(dataIngresso);
                } else {
                    System.out.println("La piscina è aperta! Puoi procedere all'inserimento dell'ingresso!");
                    //blocco il do-while
                    DataOK = false;
                    if ((dataIngresso.isAfter(apertura1) && dataIngresso.isBefore(chiusura2)) ||
                            (dataIngresso.isAfter(apertura2))) {
                        System.out.print("Prima di inserire l'ingresso e' necessario controllare la temperatura dell'utente.\nInserisci la temperatura\n");
                        double temperatura = input.nextDouble();
                        temperaturaOk = controllaTemperatura(temperatura);
                    }
                    if (temperaturaOk) {
                        System.out.println("Premi A se l'ingresso e' di un utente ABBONATO o N se non e' abbonato");
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
                                if (!controlloEta(eta)) {
                                    System.out.println("Hai inserito una età sbagliata!");
                                    break;
                                } else {
                                    //creo l'utente non abbonato con prezzo biglietto pari a 0 e lo setto
                                    UtenteNonAbbonato utenteNonAbbonato = new UtenteNonAbbonato(eta);
                                    double prezzo = utenteNonAbbonato.impostaPrezzoBiglietto();
                                    IngressiNonAbbonati nuovoIngressoNonAbbonati = new IngressiNonAbbonati(dataIngresso, utenteNonAbbonato);
                                    nuovoIngressoNonAbbonati.setPrezzo(prezzo);
                                    IngressiTOT.add(nuovoIngressoNonAbbonati);
                                    System.out.println("Ingresso inserito");
                                }
                                break;
                            default:
                                throw new InputMismatchException();
                        }
                    } else
                        DataOK = false;
                }
            } catch (PiscinaChiusaException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Hai inserito il carattere sbagliato! Inserisci A o N");
                scelta = input.next().charAt(0);
            }
        } while (DataOK);

    }


    // metodo per visualizzare tutti gli ingressi di uno specifico giorno
    // 2 nello switch
    public void IngressiGiornalieri() {
        //var c permette di controllare se NON sono presenti ingressi nel giorno inserito dall'utente
        int c = 0;
        Collections.sort(IngressiTOT, OrdinaIngressi);
        //chiedo la data
        System.out.println("Inserisci il giorno di cui vuoi sapere gli ingressi in formato gg (es. 01)");
        int giornoInserito = input.nextInt();
        System.out.println("Inserisci il mese di cui vuoi sapere gli ingressi in formato MM (es. 03)");
        int meseInserito = input.nextInt();
        System.out.println("Inserisci l'anno di cui vuoi sapere gli ingressi");
        int annoInserito = input.nextInt();
        //controllo che il mese abbia lo 0 iniziale se compreso tra gennaio
        // e settembre e costruisco la data parsando la stringa ingrMese
        String ingrMese = controllaGiornoMese(giornoInserito, meseInserito, annoInserito);
        LocalDate ingressiGiornalieri = LocalDate.parse(ingrMese, formattaData);
        System.out.println("INGRESSI DEL GIORNO " + ingressiGiornalieri);
        //scorro il vettore di ingressi e controllo le date degli ingressi
        for (Ingressi i : IngressiTOT) {
            LocalDate dataNegliIngressi = i.getData();
            if (dataNegliIngressi.equals(ingressiGiornalieri)) {
                System.out.println(i);
                c++;
            }
        }
        //stampo un messaggio nel caso in cui non ci siano ingressi inseriti in quella giornata
        if (c == 0) {
            System.out.println("Non sono presenti ingressi nel giorno inserito!");
        }
    }


    //metodo per visualizzare tutti gli ingressi di uno specifico mese
    // 3 nello switch
    public void IngressiMensiliOrdinati() {
        int c = 0;
        LocalDate ingressiMeseSpecifico = inserisciMese();
        //ordino il vettore
        Collections.sort(IngressiTOT, OrdinaIngressi);
        for (Ingressi ingresso : IngressiTOT) {
            LocalDate dataIngresso = ingresso.getData();
            if (dataIngresso.getYear() == ingressiMeseSpecifico.getYear()) {
                if (dataIngresso.getMonthValue() == ingressiMeseSpecifico.getMonthValue()) {
                    System.out.println(ingresso);
                    c++;
                }
            }
            ingressiMeseSpecifico = ingressiMeseSpecifico.plusDays(1);
        }
        if (c == 0) {
            System.out.println("Non sono presenti ingressi nel mese inserito");
        }
    }


    Comparator<Ingressi> OrdinaIngressi = new Comparator<Ingressi>() {
        @Override
        public int compare(Ingressi i1, Ingressi i2) {
            return i1.getData().compareTo(i2.getData());
        }
    };


    // metodo per visualizzare tutti gli ingressi di uno specifico utente abbonato
    // 4 nello switch
    public void IngressiUtenteAbbonato() {
        Collections.sort(IngressiTOT, OrdinaIngressi);
        System.out.println("Inserisci il nome dell'utente");
        // cerco di risolvere il bug dello spazio usando nextLine()
        // altrimenti stampa "inserisci nome e inserisci cognome" in una sola riga
        String nomeUtente = input.next() + input.nextLine();
        System.out.println("Inserisci il cognome dell'utente");
        String cognomeUtente = input.nextLine();
        IngressiAbbonati iA = null;
        System.out.println("Ingressi effettuati dall'utente " + nomeUtente + " " + cognomeUtente + " nella piscina:");
        for (Ingressi i : IngressiTOT) {
            //controllo se un ingresso è stato effettuato da un utente ABBONATO
            if (i instanceof IngressiAbbonati) {
                iA = (IngressiAbbonati) i;
                UtenteAbbonato utente = iA.getUtente();
                //recupero con le apposite get il nome e il cognome dell'utente
                String nomeUtenteAbbonato = utente.getNome();
                String cognomeUtenteAbbonato = utente.getCognome();
                //controllo se il nome e il cognome dell'utente inseriti corrispondono
                //con il nome e il cognome dell'utente di cui è registrato l'ingresso
                boolean controllo = utente.equals(nomeUtente, cognomeUtente);
                if (controllo) {
                    System.out.println(iA);
                }
            }
        }
    }


    // metodo per visualizzare l'elenco degli incassi giornalieri di uno specifico mese
    // 5 nello switch
    public void IncassiMensili() {
        //ordino il vettore
        Collections.sort(IngressiTOT, OrdinaIngressi);
        //interi per contare il numero di ingressi ridotti e gli ingressi complessivi
        //double per contare l'incasso di ciascun giorno
        int contaRidotti = 0;
        int contaIngressiMese = 0;
        double incassoGiornaliero = 0;
        int incassoTOT = 0;
        //richiedo mese/anno all'utente
        //meseSpecifico contiene una data composta da 01/mese inserito/anno inserito
        LocalDate meseSpecifico = inserisciMese();
        Month mese = meseSpecifico.getMonth();
        int anno = meseSpecifico.getYear();
        //controllo il numero di giorni del mese, mi serviranno per il ciclo for
        YearMonth annoEMese = YearMonth.of(anno, mese);
        int giornidelMese = annoEMese.lengthOfMonth();
        //stampe
        String stampaTitolo = mese.getDisplayName(TextStyle.FULL, Locale.ITALIAN) + " " + anno;
        System.out.println("Incassi del mese " + stampaTitolo);
        //j = 1 perché useremo j per stampare il numero del giorno del mese
        for (int j = 1; j < giornidelMese + 1; j++) {
            incassoGiornaliero = 0;
            for (Ingressi i : IngressiTOT) {
                //controllo se la data dell'ingresso i corrisponde a quella del mese
                if (i.getData().equals(meseSpecifico)) {
                    //controllo che l'ingresso sia di un NON ABBONATO
                    if (i instanceof IngressiNonAbbonati) {
                        contaIngressiMese++;
                        //downcast per usare le get della classe UtenteNonAbbonato
                        //e contare il numero ridotti
                        IngressiNonAbbonati ingressoNonAbbonato = (IngressiNonAbbonati) i;
                        UtenteNonAbbonato uNonAbbonato = ingressoNonAbbonato.getUtenteNA();
                        if (uNonAbbonato.getRidottoBambiniEAnziani() || uNonAbbonato.getStudente()) {
                            contaRidotti++;
                        }
                        //uso la getPrezzoBiglietto per sapere il prezzo del biglietto di quello
                        //specifico ingresso
                        double bigliettoUtente = uNonAbbonato.getPrezzoBiglietto();
                        incassoGiornaliero += bigliettoUtente;
                        incassoTOT += bigliettoUtente;
                    }
                }
            }
            //scorro i giorni del mese aggiungendo con il metodo plusDays(1) un giorno alla data meseSpecifico
            //e stampo gli incassi del giorno preso in considerazione alla i-esima iterazione
            meseSpecifico = meseSpecifico.plusDays(1);
            System.out.println("Giorno " + j + ":\t\t Incasso: " + incassoGiornaliero + " euro");
        }
        //stampo infine gli ingressi totali del mese e gli incassi complessivi
        System.out.println("\nNumero totale di ingressi: " + contaIngressiMese + " di cui " + contaRidotti + " con prezzo ridotto\n" +
                "Incassi totali del mese: " + incassoTOT);
    }

    // metodo per visualizzare l'elenco con il numero degli ingressi in abbonamento giornalieri di uno specifico mese
    // 6 nello switch
    public void IngressiAbbonatiMensili() {
        Collections.sort(IngressiTOT, OrdinaIngressi);
        int numeroIngressi = 0;
        int contaIngressi = 0;
        //creo una data "fittizia" con giorno impostato a 01, mese inserito dall'utente, anno
        LocalDate meseSpecifico = inserisciMese();
        //salvo mese e anno in variabili apposite
        Month mese = meseSpecifico.getMonth();
        int anno = meseSpecifico.getYear();
        YearMonth annoEMese = YearMonth.of(anno, mese);
        //recupero il numero di giorni del mese
        int giornidelMese = annoEMese.lengthOfMonth();

        //stampo l'intestazione
        String stampaTitolo = mese.getDisplayName(TextStyle.FULL, Locale.ITALIAN) + " " + anno;
        System.out.println("ELENCO DEI SOLI INGRESSI DI ABBONATI DEL MESE " + stampaTitolo.toUpperCase());

        //j = 1 perché useremo j per stampare il numero del giorno del mese
        for (int j = 1; j < giornidelMese + 1; j++) {
            numeroIngressi = 0;
            for (Ingressi i : IngressiTOT) {
                if (i.getData().equals(meseSpecifico)) {
                    //controllo se la data inserita matcha quella dell'ingresso salvato e incremento i counter
                    if (i instanceof IngressiAbbonati) {
                        IngressiAbbonati ingressoAbbonati = (IngressiAbbonati) i;
                        numeroIngressi++;
                        contaIngressi++;
                    }
                }
            }
            meseSpecifico = meseSpecifico.plusDays(1);
            System.out.println("Giorno " + j + ":\t\t" + numeroIngressi);
        }
        if (contaIngressi == 0) {
            System.out.println("\nNon sono presenti ingressi nel mese inserito");
        } else {
            System.out.println("\nSono entrate " + contaIngressi + " persone abbonate nel mese di "
                    + mese.getDisplayName(TextStyle.FULL, Locale.ITALIAN) + " " + anno);
        }
    }


    // stampa degli elementi nel vettore
    public void visualizzaIngresso() {
        System.out.println("----------------Elenco totale ingressi-----------------");
        for (Object ingresso : IngressiTOT) {
            System.out.println(ingresso);
        }
    }


    /* -------------- da qui METODI AUSILIARI -------------------*/

    // metodo ausiliario per chiedere la data all'utente
    // controlla la correttezza della data e che la data inserita
    // non sia posteriore al giorno attuale
    private LocalDate chiediData() {
        LocalDate data = null;
        boolean ok = true;
        char scelta;
        System.out.println("Vuoi inserire un ingresso nel giorno attuale? [S] [N]");
        scelta = input.next().charAt(0);
        do {
            try {
                if (scelta == 'S' || scelta == 's') {
                    data = LocalDate.now();
                    ok = false;
                }
                if (scelta == 'N' || scelta == 'n') {
                    System.out.println("Inserisci una data in formato DD/MM/YYYY");
                    String d1 = input.next();
                    data = LocalDate.parse(d1, formattaData);
                    input.nextLine();
                    if (d1 == null) {
                        throw new DataErrataException();
                    }
                    if (data.isAfter(LocalDate.now())) {
                        System.out.println("Data posteriore al giorno attuale.");
                        throw new DataErrataException();
                    } else {
                        ok = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserisci S o N");
            } catch (DateTimeParseException e) {
                System.out.println("Data errata!");
            } catch (DataErrataException e) {
                System.out.println("Inserisci una data corretta!");
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
        int meseInserito = input.nextInt();
        System.out.println("Inserisci l'anno di cui vuoi sapere gli ingressi");
        int annoInserito = input.nextInt();
        //input.nextLine per evitare che nello scanner rimanga in buffer il \n
        input.nextLine();
        //costruisco una Localdate ad hoc con 01 + mese + anno
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

    // Metodo ausiliario che controlla che l'utente abbia inserito lo 0
    // nei mesi compresi tra gennaio e settembre
    // se non lo ha messo, viene aggiunto in automatico
    private String controllaGiornoMese(int giornoInserito, int meseInserito, int annoInserito) {
        String ingrMese = "";
        if ((meseInserito >= 1 && meseInserito <= 9) || ((giornoInserito >= 1) &&
                giornoInserito <= 9)) {
            ingrMese = "0" + giornoInserito + "/0" + meseInserito + "/" + annoInserito;
        } else {
            ingrMese = giornoInserito + meseInserito + "/" + annoInserito;
        }
        return ingrMese;
    }

    //Metodo che controlla la correttezza dell'eta'
    //vengono scartate età inferiori a 0 o troppo elevate
    private boolean controlloEta(int eta) {
        boolean etaOK = true;
        if ((eta <= 0) || (eta >= 112)) {
            etaOK = false;
        }
        return etaOK;
    }
}
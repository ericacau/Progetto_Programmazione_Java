package piscina;

import java.io.Serializable;
import java.util.Scanner;

// asdfghjk
public class UtenteNonAbbonato implements Serializable{
    /* UTENTE NON ABBONATO
		gestisce gli oggetti utenti abbonati composti dal prezzo del biglietto che può essere di due tipi
            * INTERO (3 euro)
            * RIDOTTO -> in base a due criteri
                -  età (2 euro) : riuzione per i bambini al di sotto dei 12 anni e per gli anziani (a partire dai 65 anni)
                -  studente (2,50 euro): ridotto per gli studenti (età inferiore ai 24 anni)
	*/

    //i prezzi dei biglietti senza abbonamento vengono inizializzati come costanti
    private static final double BIGLIETTO_INTERO = 3.0;
    private static final double BIGLIETTO_RIDOTTO_ETA = 2;
    private static final double BIGLIETTO_RIDOTTO_STUDENTI = 2.5;
    private boolean studente = false;
    private boolean ridottoBambiniEAnziani = false;
    private double prezzoBiglietto;
    private int eta;

    //imposto a static per renderlo serializzabile
    private transient Scanner input = new Scanner(System.in);


    //costruttore dell'utente non abbonato
    public UtenteNonAbbonato(int eta) {
        this.eta = eta;
        this.prezzoBiglietto = setPrezzoBiglietto();
    }

    public double getPrezzoBiglietto() {
        return prezzoBiglietto;
    }

    public int getEta() {
        return eta;
    }

    public boolean getStudente() {
        return studente;
    }

    public boolean getRidottoBambiniEAnziani() {
        return ridottoBambiniEAnziani;
    }

    public String stampaTipoRiduzione() {
        String riduzioneAttuale = "Nessuna riduzione prevista";
        if (studente) {
            riduzioneAttuale = "ridotto studenti";
        }
        if (ridottoBambiniEAnziani) {
            riduzioneAttuale = "ridotto eta'";
        }
        return riduzioneAttuale;
    }


    public double setPrezzoBiglietto() {
        //verifica la tipologia di utente e stabilisce il prezzo
        if (isRidottoBambiniEAnziani()) {
            prezzoBiglietto = BIGLIETTO_RIDOTTO_ETA;
        }
        else if (eta <= 24 && isStudente()) {
            prezzoBiglietto = BIGLIETTO_RIDOTTO_STUDENTI;
        }
        else
            prezzoBiglietto = BIGLIETTO_INTERO;
        return prezzoBiglietto;
    }



    /* METODI AUSILIARI CHE CONTROLLANO LA TIPOLOGIA DI UTENTE*/
    private boolean isRidottoBambiniEAnziani() {
        if ((eta <= 12) || (eta >= 65))
            ridottoBambiniEAnziani = true;
        return ridottoBambiniEAnziani;
    }

    private boolean isStudente() {
        System.out.println("Sei uno studente?");
        char s = input.next().charAt(0);
        switch (s) {
            case 'S':
            case 's':
                System.out.println("Studente");
                studente = true;
                break;
            case 'N':
            case 'n':
                System.out.println("Non studente");
                break;
            default:
                System.out.println("Non sono previste riduzione");
        }
        return studente;
    }

    //Metodo che controlla la correttezza dell'eta'
    public boolean controlloEta() {
        boolean etaOK = true;
        if ((eta <= 0) || (eta >= 112)) {
            etaOK = false;
            //System.out.println("Il valore inserito non e' corretto, inserire nuovamente l'eta' ");
        }
        return etaOK;
    }

}
package piscina;

import java.io.Serializable;
import java.util.Scanner;
 /* UTENTE NON ABBONATO
		gestisce gli oggetti utenti abbonati composti dal prezzo del biglietto che può essere di due tipi
            * INTERO (3 euro)
            * RIDOTTO -> in base a due criteri
                -  età (2 euro) : riuzione per i bambini al di sotto dei 12 anni e per gli anziani (a partire dai 65 anni)
                -  studente (2,50 euro): ridotto per gli studenti (età inferiore ai 24 anni)
	*/


public class UtenteNonAbbonato implements Serializable {
    static final long serialVersionUID = -4829345612921075523L;
    private boolean studente = false;
    private boolean ridottoBambiniEAnziani = false;
    private double prezzoBiglietto;
    private int eta;
    
    //i prezzi dei biglietti senza abbonamento vengono inizializzati come costanti
    private static final double BIGLIETTO_INTERO = 3.0;
    private static final double BIGLIETTO_RIDOTTO_ETA = 2;
    private static final double BIGLIETTO_RIDOTTO_STUDENTI = 2.5;
    
    private static Scanner input = new Scanner(System.in);

    //costruttore 
    public UtenteNonAbbonato(int eta) {
        this.eta = eta;
        //lo iniziallizzo a 0, poi aggiornerò con prezzo giusto
        this.prezzoBiglietto = 0;
    }

    /*---METODI---*/

    //metodo che restituisce l'eta'
    public int getEta() {
        return eta;
    }

    // metodo per ottenere il prezzo del biglietto
    public double getPrezzoBiglietto() {
        return prezzoBiglietto;
    }

    /*metodi per ottenere le riduzioni e applicarle*/
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

    public double impostaPrezzoBiglietto() {
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
                System.out.println("Prezzo ridotto per studenti");
                studente = true;
                break;
            case 'N':
            case 'n':
                System.out.println("Non studente");
                break;
            default:
                System.out.println("Non sono previste riduzioni di prezzo");
        }
        return studente;
    }
}

package piscina;

import java.util.Scanner;

// asdfghjk
public class UtenteNonAbbonato {

	//i prezzi dei biglietti senza abbonamento vengono inizializzati come costanti
	private static final double BIGLIETTO_INTERO = 3.0;
	private static final double BIGLIETTO_RIDOTTO_ETA = 2;
	private static final double BIGLIETTO_RIDOTTO_STUDENTI = 2.5;
	private boolean studente = false;
	private boolean ridottoBambiniEAnziani = false;
    private Scanner input = new Scanner(System.in);
	private double prezzoBiglietto;
	private int eta;


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

	public double setPrezzoBiglietto(){
		//controllare gli utenti e stabilire il prezzo
		//si fa uno switch?

		if((eta <= 12) || (eta >= 65)) {
			ridottoBambiniEAnziani = true;
			prezzoBiglietto = BIGLIETTO_RIDOTTO_ETA;
		}
		if(isStudente()){
		    prezzoBiglietto = BIGLIETTO_RIDOTTO_STUDENTI;
        }
		else prezzoBiglietto = BIGLIETTO_INTERO;

		return prezzoBiglietto;
	}

    /* METODI AUSILIARI CHE CONTROLLANO LA TIPOLOGIA DI UTENTE*/

	private boolean isRidottoBambiniEAnziani() {
		return ridottoBambiniEAnziani;
	}

    private boolean isStudente() {
        System.out.println("Sei uno studente minore di 24 anni?");
        char s = input.next().charAt(0);
        if (s=='S' || s=='s') {
            System.out.println("Studente");
            studente = true;}
        if(s=='N'|| s == 'n') {
            System.out.println("Non studente");
        }
        else System.out.println("Inserisci S o N");
        return studente;
    }

}



package piscina;
// asdfghjk
public class UtenteNonAbbonato {
	
	//i prezzi dei biglietti senza abbonamento vengono inizializzati come costanti
	private static final double BIGLIETTO_INTERO = 3.0;
	private static final double BIGLIETTO_RIDOTTO_ETA = 2;
	//private static final double BIGLIETTO_RIDOTTO_STUDENTI = 2.5;
	private boolean studente = false;
	private boolean ridotto_bambini = false;
	protected double prezzoBiglietto;
	protected static int eta;
	
	//costruttore dell'utente non abbonato
	public UtenteNonAbbonato(int eta) {
		this.eta = eta;
		this.prezzoBiglietto = setPrezzoBiglietto();
	}

	public double getPrezzoBiglietto() {
		return prezzoBiglietto;
	}
	
	public static int getEta() {
		return eta;
	}
	
	public double setPrezzoBiglietto(){
		//controllare gli utenti e stabilire il prezzo
		//si fa uno switch?
		if((UtenteNonAbbonato.getEta() < 12) || (UtenteNonAbbonato.getEta()> 65)) {
			prezzoBiglietto = BIGLIETTO_RIDOTTO_ETA;
		}
		else prezzoBiglietto = BIGLIETTO_INTERO;
			
		return prezzoBiglietto;
	}
	
	
	
}



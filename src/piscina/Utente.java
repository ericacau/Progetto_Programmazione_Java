package piscina;

public class Utente {
	private String nome; 
	private String cognome;
	private static int eta;
	
	//controllare se l'et� dell'utente � > 0
	public Utente(int eta){
		
		this.eta = eta;
	}

	public static int getEta() {
		return eta;
	}

	
}
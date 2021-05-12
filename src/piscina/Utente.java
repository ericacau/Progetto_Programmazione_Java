package piscina;

public class Utente {
	private String nome; 
	private String cognome;
	private int eta;
	
	public Utente(String nome, String cognome, int eta){
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
	}

	public String getNomeCognome() {
		String s = nome + cognome;
		return s;
	}

	public String getCognome() {
		return cognome;
	}

	public int getEta() {
		return eta;
	}

	//deve controllare l'età!
}
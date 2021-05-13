package piscina;

public class UtenteAbbonato {
	
	String nome;
	String cognome;
	
	//COSTANTE
	private static int codiceUtenteAbbonato = 0;

	//costruttore
		public UtenteAbbonato(String nome, String cognome) {
			this.nome = nome;
			this.cognome = cognome;
			UtenteAbbonato.codiceUtenteAbbonato = codiceUtenteAbbonato+1;
	}

		//metodo per visualizzare il codice utente abbonato
		public static int getCodiceUtenteAbbonato() {
			return codiceUtenteAbbonato;
		}

		public String getNome() {
			return nome;
		}

		public String getCognome() {
			return cognome;
		}
	
	
}
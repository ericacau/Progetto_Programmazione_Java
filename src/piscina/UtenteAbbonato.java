package piscina;

public class UtenteAbbonato extends Utente {
	
	//COSTANTE
	private static int codiceUtenteAbbonato = 0;

	//costruttore
		public UtenteAbbonato(String nome, String cognome, int eta) {
			super(nome, cognome, eta);
			this.codiceUtenteAbbonato = codiceUtenteAbbonato+1;
	}

		//metodo per visualizzare il codice utente abbonato
		public static int getCodiceUtenteAbbonato() {
			return codiceUtenteAbbonato;
		}
	
	
}

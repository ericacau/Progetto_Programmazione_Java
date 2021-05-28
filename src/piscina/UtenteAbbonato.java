package piscina;

import java.io.*;

public class UtenteAbbonato implements Serializable {
	/* UTENTE ABBONATO
		* gestisce gli oggetti utenti abbonati composti da nome e cognome e codice utente 
			(che varia per ogni utente e serve per gestire casi di omonimia)
	*/

    private String nome;
    private String cognome;
    private static int codiceUtenteAbbonato = 1;
    private int idUtente;

    //costruttore
    public UtenteAbbonato(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        this.idUtente = codiceUtenteAbbonato + 1;
        codiceUtenteAbbonato++;
    }


    //metodo per visualizzare il codice utente abbonato
    public int getIdUtente() {
        return idUtente;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public boolean equals(String nomeInserito, String cognomeInserito) {
        return (nome.equals(nomeInserito) && cognome.equals(cognomeInserito));
    }

}

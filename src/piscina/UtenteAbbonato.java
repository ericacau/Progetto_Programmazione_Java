package piscina;

import java.io.*;

public class UtenteAbbonato implements Serializable {
    /* UTENTE ABBONATO
        * gestisce gli oggetti utenti abbonati composti da nome e cognome e codice utente
            (che varia per ogni utente e serve per gestire casi di omonimia)
    */
    static final long serialVersionUID = -4829345612921075523L;

    private String nome;
    private String cognome;

    //costruttore
    public UtenteAbbonato(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }


    //metodo per visualizzare il nome dell' utente abbonato
    public String getNome() {
        return nome;
    }

     //metodo per visualizzare il cognome dell' utente abbonato
    public String getCognome() {
        return cognome;
    }


    public boolean equals(String nomeInserito, String cognomeInserito) {
        return (nome.equals(nomeInserito) && cognome.equals(cognomeInserito));
    }

}
package piscina;

import java.util.*;

/*CLASSE INGRESSIABBONATI 
    Estensione della superclasse Ingressi che gestisce gli ingressi degli utenti abbonati contenenti
    * data du ingresso
    * utente -> variabile che contiene le informazioni della classe utenteAbbonato (nome e cognome)  

*/
public class IngressiAbbonati extends Ingressi{
    private UtenteAbbonato utenteA;

    public IngressiAbbonati(Date data, UtenteAbbonato utenteA) {
        super(data);
        this.utenteA = utenteA;
    }

    public UtenteAbbonato getUtenteA() {
        return utenteA;
    }

    public void setUtente(UtenteAbbonato utenteA) {
        this.utenteA = utenteA;
    }

    public String toString() {
        return "DATA INGRESSO: " + this.getData() + "\t" + "Nome e Cognome utente: " + utenteA.getNome() + " " + utenteA.getCognome() + "\t" + utenteA.getCodiceUtenteAbbonato();
    }

    public UtenteAbbonato getUtente() {
        return utenteA;
    }
}



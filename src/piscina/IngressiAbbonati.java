package piscina;

import java.util.*;
public class IngressiAbbonati extends Ingressi {
	Date data;
	String nome;
	String cognome;
	
	public IngressiAbbonati(Date data, String nome, String cognome) {
		super(data); 
		this.nome = nome;
		this.cognome = cognome;
	}



	
	

}

package piscina;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class IngressiAbbonati extends Ingressi {
	Date data;
	int codiceUtente;
	
	public IngressiAbbonati(Date data, int codiceUtente) {
		super(data);
		this.codiceUtente = codiceUtente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(int codiceUtente) {
		this.codiceUtente = codiceUtente;
	}
	
	

}

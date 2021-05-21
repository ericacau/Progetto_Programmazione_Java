package piscina;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ingressi {
	private  Date data;
	private  Scanner input = new Scanner (System.in);

	public Ingressi(Date data) {
		this.data = data;
	}

	public Date getData() {
		return data;
	}
}
package piscina;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class letturaIngressi {
	
	public static void leggiFile() {
	
	File f1 = new File("C:\\Users\\Erica\\Desktop\\piscina.txt");
	String text = "";
	try {
		Scanner leggiFile = new Scanner(f1);
		System.out.println("leggo il file degli ingressi in input");
		text += leggiFile.nextLine() + "\n";
		System.out.println(text);
	} catch (FileNotFoundException e) {
		System.out.println("Controlla il percorso. Il file non esiste");
	}
}
	}
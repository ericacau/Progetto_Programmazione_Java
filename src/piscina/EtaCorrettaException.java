package piscina;

public class EtaCorrettaException extends Exception{

    public EtaCorrettaException() {
        super("L'eta' inserita non è corretta! ");
    }
}

package Exeption;

public class PiscinaPienaException extends Exception {

    public PiscinaPienaException() {
        super("La piscina ha raggiunto la capienza massima! \n");
    }
}

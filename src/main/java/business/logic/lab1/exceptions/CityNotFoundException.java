package business.logic.lab1.exceptions;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String msg) {
        super(msg);
    }
}

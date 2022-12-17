package exceptions;

public class TooHighValue extends Exception{
    @Override
    public String toString() {
        return "Detected " + getClass() + " [Program get too high value]";
    }
}

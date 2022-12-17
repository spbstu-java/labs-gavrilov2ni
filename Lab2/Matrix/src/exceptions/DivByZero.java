package exceptions;

public class DivByZero extends RuntimeException {
    @Override
    public String toString() {
        return "\nDetected " + getClass() + " [Dividing by zero]";
    }
}

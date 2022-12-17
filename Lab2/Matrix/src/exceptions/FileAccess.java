package exceptions;

import java.io.IOException;

public class FileAccess extends IOException {
    @Override
    public String toString() {
        return "\nDetected " + getClass() + " [Failed to access file]";
    }
}

package digital.hakaton.dobro.errorHandling;

import java.util.List;

public class InvalidDataException extends RuntimeException {
    private final List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public InvalidDataException(List<String> messages) {
        this.messages = messages;
    }
}

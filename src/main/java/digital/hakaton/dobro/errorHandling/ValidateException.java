package digital.hakaton.dobro.errorHandling;

import java.util.List;

public class ValidateException extends RuntimeException {

    private final List<String> messages;

    public ValidateException(List<String> message) {
        this.messages = message;
    }

    public List<String> getMessages () {
        return this.messages;
    }
}

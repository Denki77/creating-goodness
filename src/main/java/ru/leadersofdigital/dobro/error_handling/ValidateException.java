package ru.leadersofdigital.dobro.error_handling;

import java.util.List;

public class ValidateException extends RuntimeException {

    private List<String> messages;

    public ValidateException(List<String> message) {
        this.messages = message;
    }

    public List<String> getMessages () {
        return this.messages;
    }
}

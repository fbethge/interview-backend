package de.bringmeister.errorpage;

/**
 * Created by fabi on 19.11.17.
 */
abstract class ApiSubError {

}

class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private String message;
    private Object rejectedValue;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
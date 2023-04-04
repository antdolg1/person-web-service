package lv.ergo.ergowebservice.exception;

import java.util.function.Supplier;

public class PersonLookupException extends RuntimeException {

    private static final String BY_ID = "Person record with id %s not found";

    public PersonLookupException(final String message) {
        super(message);
    }

    public static Supplier<PersonLookupException> byId(final Long id) {
        return () -> new PersonLookupException(String.format(BY_ID, id));
    }
}

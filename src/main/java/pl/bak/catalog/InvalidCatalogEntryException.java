package pl.bak.catalog;

public class InvalidCatalogEntryException extends RuntimeException {

    public InvalidCatalogEntryException(String message) {
        super(message);
    }
}

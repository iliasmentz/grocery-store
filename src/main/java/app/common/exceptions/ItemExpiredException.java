package app.common.exceptions;

public class ItemExpiredException extends AppException {
    public ItemExpiredException() {
        super("Item is expired");
    }
}

package hr.java.restaurant.exception;

public class NegativOrUnrealPrice extends RuntimeException
{
    public NegativOrUnrealPrice(String message) {
        super(message);
    }

    public NegativOrUnrealPrice(String message, Throwable cause) {
      super(message, cause);
    }

    public NegativOrUnrealPrice(Throwable cause) {
      super(cause);
    }

    public NegativOrUnrealPrice(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
    }

    public NegativOrUnrealPrice() {
    }
}

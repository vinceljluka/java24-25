package hr.java.restaurant.exception;

public class DuplicateEntityException extends Exception {
  public DuplicateEntityException() {}

  public DuplicateEntityException(String message)
  {
    super(message);
  }

  public DuplicateEntityException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public DuplicateEntityException(Throwable cause)
  {
    super(cause);
  }

  public DuplicateEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
  {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}

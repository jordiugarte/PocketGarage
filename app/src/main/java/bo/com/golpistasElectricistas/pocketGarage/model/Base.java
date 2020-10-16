package bo.com.golpistasElectricistas.pocketGarage.model;

public class Base<T> {
    private boolean success;
    private int error;
    private Exception exception;
    private T data;

    public Base(T data) {
        this.success = true;
        this.data = data;
        this.error = 0;
        this.exception = null;
    }

    public Base(int error, Exception exception) {
        this.success = false;
        this.data = null;
        this.error = error;
        this.exception = exception;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getError() {
        return error;
    }

    public Exception getException() {
        return exception;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return getMessage();
    }
}

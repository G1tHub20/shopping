package exception;

public class SystemException extends Exception {
	private int code;
    public SystemException(String message){
        super(message);
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
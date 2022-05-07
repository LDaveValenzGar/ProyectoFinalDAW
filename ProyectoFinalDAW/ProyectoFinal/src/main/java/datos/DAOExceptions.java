package datos;

public class DAOExceptions extends Exception{
	private static final long serialVersionUID = 1L;

	public DAOExceptions(String message) {
        super(message);
    }
	
	public DAOExceptions(String message, Throwable cause) {
        super(message, cause);
    }
	
	public DAOExceptions(Throwable cause) {
        super(cause);
    }
}

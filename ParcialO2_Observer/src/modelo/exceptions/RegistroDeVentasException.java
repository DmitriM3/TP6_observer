package modelo.exceptions;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class RegistroDeVentasException extends Exception {

	private static final long serialVersionUID = 4815084059057874875L;

	public RegistroDeVentasException(String string, ClassNotFoundException e) {
		super(string);
	}

	public RegistroDeVentasException(String string, SQLException e) {
		super(string);
	}

	public RegistroDeVentasException(String string, FileNotFoundException e) {
		super(string);
	}

	public RegistroDeVentasException(String string, Exception e) {
		super(string);
	}

}

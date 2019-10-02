import java.util.*;
import java.io.*;

public class ComplexException extends Exception{
	public static final String SIZE_ERROR="Error en los tamaños de matriz o vector";
	public static final String DIVISION_BY_0="Division por 0";
	
	/**
	 * Constructor de la clase ComplexException
	 * @param message el String de la excepcion
	 */
	public ComplexException(String message) {
		super(message);
	}
}

package domainModel;



/**
 * Classe de exceção SemTurnoAtivoException. Extende a classe Exception.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 *
 */
public class SemTurnoAtivoException extends Exception
{
	
	/**
	 * Construtor da exceção SemTurnoAtivoException.
	 * 
	 * @param message Recebe a mensagem que será exibida.
	 */
	public SemTurnoAtivoException (final String message) {
		super(message);
	}
}
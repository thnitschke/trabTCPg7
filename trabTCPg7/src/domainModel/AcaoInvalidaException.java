package domainModel;

/**
 * Classe de exceção AcaoInvalidaException. Extende a classe Exception.
 * 
 * @author Rodrigo Okido (trabTCPg7)
 * @version 1.0
 */
public class AcaoInvalidaException extends Exception
{

	/**
	 * Construtor da exceção AcaoInvalidaException.
	 * 
	 * @param message
	 *            Recebe a mensagem que será exibida.
	 */
	public AcaoInvalidaException (final String message)
	{
		super (message);
	}
}
